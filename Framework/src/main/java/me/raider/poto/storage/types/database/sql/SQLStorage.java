package me.raider.poto.storage.types.database.sql;

import com.google.common.util.concurrent.ListeningExecutorService;
import me.raider.poto.serializer.SerializedObject;
import me.raider.poto.serializer.Serializer;
import me.raider.poto.storage.AbstractStorage;
import me.raider.poto.storage.types.Storable;
import me.raider.poto.storage.StorageType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public abstract class SQLStorage<T extends Storable> extends AbstractStorage<T> {

    private final SimpleSQLDatabase sqlDatabase;
    private final String table;
    private final Class<? extends T> clazz;

    public SQLStorage(String name, Serializer<T> serializer, SimpleSQLDatabase sqlDatabase, String table, Class<? extends T> clazz, ListeningExecutorService executorService) {
        super(name, StorageType.MYSQL, serializer, executorService);

        this.sqlDatabase=sqlDatabase;
        this.table=table;
        this.clazz=clazz;
    }

    public SimpleSQLDatabase getAbstractSqlDatabase() {
        return sqlDatabase;
    }

    @Override
    public T load(String key) {

        String[] paths = getSerializer().getAllAnnotatedFields(clazz).getOrganizedPaths();

        if (!sqlDatabase.dataExist(table, paths[0], key)) {
            T newObject = createIfAbsent(key);
            get().put(key, newObject);
            return newObject;
        }

        Map<String, Object> dataMap = new HashMap<>();

        try (PreparedStatement statement = sqlDatabase.getConnection().prepareStatement("SELECT * FROM " + table + " WHERE (" + paths[0] + "=?)")) {

            statement.setString(1, key);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                for (String column : paths) {
                    dataMap.put(column, resultSet.getObject(column));
                }
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        SerializedObject<T> serializedObject = getSerializer().deserialize(dataMap);

        T object = serializedObject.createWithData();

        get().put(key, object);

        return object;
    }

    @Override
    public void save(String key) {

        T toSerialize = get().get(key);

        if (toSerialize==null) {
            return;
        }

        Map<String, Object> serializeMap = getSerializer().serialize(toSerialize).getLinkedMap();
        String[] paths = getSerializer().getAllAnnotatedFields(clazz).getOrganizedPaths();

        try (Connection connection = sqlDatabase.getConnection()) {

            if (sqlDatabase.dataExist(table, paths[0], key)) {

                PreparedStatement update = connection.prepareStatement(buildQuery("update"));

                int index = 0;

                for (int i = 1 ; i <= paths.length ; i++) {

                    index++;

                    String actualObj = paths[i!=paths.length ? i : i-1];

                    if (serializeMap.get(actualObj) instanceof Map) {
                        update.setObject(index, saveMap(serializeMap, actualObj));
                        continue;
                    }

                    update.setObject(index, serializeMap.get(actualObj));
                }

                update.setObject(paths.length, serializeMap.get(paths[0]));

                sqlDatabase.executeStatement(update);

            } else {

                PreparedStatement insert = connection.prepareStatement(buildQuery("insert"));

                for (int i = 1 ; i <= paths.length ; i++) {

                    if (serializeMap.get(paths[i-1]) instanceof Map) {
                        insert.setObject(i, saveMap(serializeMap, paths[i-1]));
                        continue;
                    }
                    insert.setObject(i, serializeMap.get(paths[i-1]));
                }
                sqlDatabase.executeStatement(insert);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        get().remove(key);
    }

    private String saveMap(Map<String, Object> serializeMap, String actual) {

        Map<Object, Object> toSerializeMap = (Map<Object, Object>) serializeMap.get(actual);

        StringBuilder builder = new StringBuilder();
        int index = 0;

        for (Object keyMap : toSerializeMap.keySet()) {

            index++;

            builder.append(keyMap.toString())
                    .append(",@,")
                    .append(toSerializeMap.get(keyMap).toString());

            if (index!=toSerializeMap.keySet().size()) {
                builder.append(";@;");
            }

        }
        return builder.toString();
    }

    private String buildQuery(String type) {

        String[] paths = getSerializer().getAllAnnotatedFields(clazz).getOrganizedPaths();

        switch (type.toLowerCase()) {

            case "update":

                StringBuilder updateBuilder = new StringBuilder();

                updateBuilder.append("UPDATE ").append(table).append(" SET ");

                for (int i = 1 ; i < paths.length ; i++) {
                    updateBuilder.append(paths[i]).append("=?");
                    if (i!=paths.length-1) {
                        updateBuilder.append(",");
                    }
                }

                updateBuilder.append(" WHERE(").append(paths[0]).append("=?)");
                return updateBuilder.toString();

            case "insert":

                StringBuilder insertBuilder = new StringBuilder();

                insertBuilder.append("INSERT INTO ").append(table).append("(");

                for (int i = 0 ; i < paths.length ; i++) {
                    insertBuilder.append(paths[i]);
                    if (i!=paths.length-1) {
                        insertBuilder.append(",");
                    }
                }

                insertBuilder.append(") VALUES(");

                for (int i = 0 ; i < paths.length ; i++) {
                    insertBuilder.append("?");
                    if (i!=paths.length-1) {
                        insertBuilder.append(",");
                    }
                }


                insertBuilder.append(")");
                return insertBuilder.toString();

            default:
        }
        return "";
    }


}

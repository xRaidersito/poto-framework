package me.raider.poto.storage.types.database.sql;

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

public abstract class SqlStorage<T extends Storable> extends AbstractStorage<T> {

    private final AbstractSqlDatabase sqlDatabase;
    private final String table;
    private final String[] sqlColumns;

    public SqlStorage(String name, Serializer<T> serializer, AbstractSqlDatabase sqlDatabase, String table, String[] sqlColumns) {
        super(name, StorageType.MYSQL, serializer);

        this.sqlDatabase=sqlDatabase;
        this.table=table;
        this.sqlColumns=sqlColumns;
    }

    public AbstractSqlDatabase getAbstractSqlDatabase() {
        return sqlDatabase;
    }

    @Override
    public T load(String key) {

        if (!sqlDatabase.dataExist(table, sqlColumns[0], key)) {
            return createIfAbsent(key);
        }

        Map<String, Object> dataMap = new HashMap<>();

        try (PreparedStatement statement = sqlDatabase.prepareStatement("SELECT * FROM " + table + " WHERE (" + sqlColumns[0] + "=?)", key)) {

            ResultSet resultSet = sqlDatabase.executeResult(statement);

            if (resultSet.next()) {
                for (String column : sqlColumns) {
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

        try (Connection connection = sqlDatabase.getConnection()) {

            if (sqlDatabase.dataExist(table, sqlColumns[0], key)) {

                PreparedStatement update = connection.prepareStatement(buildQuery("update"));

                for (int i = 0 ; i < sqlColumns.length ; i++) {
                    update.setObject(i, serializeMap.get(sqlColumns[i]));
                }

                sqlDatabase.executeStatement(update);

            } else {

                PreparedStatement insert = connection.prepareStatement(buildQuery("insert"));

                for (int i = 0 ; i < sqlColumns.length ; i++) {
                    insert.setObject(i, serializeMap.get(sqlColumns[i]));
                }

                sqlDatabase.executeStatement(insert);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        get().remove(key);
    }

    private String buildQuery(String type) {

        switch (type.toLowerCase()) {

            case "update":

                StringBuilder updateBuilder = new StringBuilder();

                updateBuilder.append("UPDATE ").append(table).append(" SET ");

                for (int i = 1 ; i < sqlColumns.length ; i++) {
                    updateBuilder.append(sqlColumns[i]).append("=?");
                    if (i!=sqlColumns.length-1) {
                        updateBuilder.append(",");
                    }
                }

                updateBuilder.append(" WHERE(").append(sqlColumns[0]).append("=?)");
                return updateBuilder.toString();

            case "insert":

                StringBuilder insertBuilder = new StringBuilder();

                insertBuilder.append("INSERT INTO ").append(table).append(" VALUE(");

                for (int i = 0 ; i < sqlColumns.length ; i++) {
                    insertBuilder.append(sqlColumns[i]).append("?");
                    if (i!=sqlColumns.length-1) {
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

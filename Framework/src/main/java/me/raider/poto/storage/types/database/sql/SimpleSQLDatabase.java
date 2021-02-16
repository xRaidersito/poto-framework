package me.raider.poto.storage.types.database.sql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.raider.poto.file.YamlFile;
import me.raider.poto.storage.types.database.DatabaseDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SimpleSQLDatabase implements SQLDatabase {

    private final HikariDataSource dataSource;

    public SimpleSQLDatabase(YamlFile configFile) {

        DatabaseDetails databaseDetails = new DatabaseDetails(configFile);

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://" + databaseDetails.getHostname() + ":" + databaseDetails.getPort() + "/" + databaseDetails.getDatabase());
        config.setDriverClassName("com.mysql.jdbc.Driver");

        config.setUsername(databaseDetails.getUsername());
        config.setPassword(databaseDetails.getPassword());
        config.setMaximumPoolSize(databaseDetails.getMaximumConnections());
        config.setConnectionTimeout(databaseDetails.getConnectionTimeout());

        dataSource=new HikariDataSource(config);

    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }

    @Override
    public boolean dataExist(String table, String column, String key) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + table + " WHERE (" + column +"=?)")) {

            statement.setString(1, key);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            resultSet.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

    @Override
    public void createTables(String table, String[] columns, String... dataTypes) {

        System.out.println(buildTablesQuery(table, columns, dataTypes));

        try (PreparedStatement statement = getConnection().prepareStatement(buildTablesQuery(table, columns, dataTypes))) {

            executeStatement(statement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String buildTablesQuery(String table, String[] columns, String... dataTypes) {

        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE IF NOT EXISTS ").append(table).append(" (");

        for (int i = 0 ; i < columns.length ; i++) {

            builder.append(columns[i]).append(" ").append(dataTypes[i]);

            if (i+1!=columns.length) {
                builder.append(", ");
            }

        }
        builder.append(")");
        return builder.toString();

    }
}

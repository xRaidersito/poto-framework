package me.raider.poto.storage.types.database.sql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.raider.poto.file.YamlFile;
import me.raider.poto.storage.types.database.DatabaseDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractSqlDatabase implements SqlDatabase {

    private final HikariDataSource dataSource;

    public AbstractSqlDatabase(YamlFile configFile) {

        DatabaseDetails databaseDetails = new DatabaseDetails(configFile);

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://" + databaseDetails.getHostname() + ":" + databaseDetails.getPort() + "/" + databaseDetails.getDatabase());
        config.setDriverClassName("com.mysql.jdbc.Driver");

        config.setUsername(databaseDetails.getUsername());
        config.setPassword(databaseDetails.getPassword());
        config.setMaximumPoolSize(databaseDetails.getMaximumConnections());
        config.setConnectionTimeout(databaseDetails.getConnectionTimeout());

        dataSource=new HikariDataSource(config);

        createTables();
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
}

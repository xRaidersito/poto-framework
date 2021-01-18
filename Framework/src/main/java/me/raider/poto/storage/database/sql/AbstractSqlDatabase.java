package me.raider.poto.storage.database.sql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.raider.poto.file.YamlFile;
import me.raider.poto.storage.database.DatabaseDetails;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractSqlDatabase implements SqlDatabase {

    private HikariDataSource dataSource;

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
}

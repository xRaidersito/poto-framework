package me.raider.poto.storage.types.database;

import me.raider.poto.file.YamlFile;

public class DatabaseDetails {

    private final String hostname;
    private final String port;
    private final String database;
    private final String username;
    private final String password;

    private final int maximumConnections;

    private final int connectionTimeout;

    public DatabaseDetails(YamlFile configFile) {

        this.hostname=configFile.getString("database.host");
        this.port=configFile.getString("database.port");
        this.password=configFile.getString("database.password");
        this.username=configFile.getString("database.username");
        this.database=configFile.getString("database.database");
        this.maximumConnections=configFile.getInt("database.pool-settings.maximum-connections");
        this.connectionTimeout=configFile.getInt("database.pool-settings.connection-timeout");

    }

    public String getHostname() {
        return hostname;
    }

    public String getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getMaximumConnections() {
        return maximumConnections;
    }

    public long getConnectionTimeout() {
        return connectionTimeout;
    }

}
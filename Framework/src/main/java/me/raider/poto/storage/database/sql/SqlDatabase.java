package me.raider.poto.storage.database.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface SqlDatabase {

    Connection getConnection() throws SQLException;

    void closePool();

    default ResultSet executeResult(PreparedStatement statement) {

        try (ResultSet result = statement.executeQuery()) {

            return result;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    default void executeStatement(PreparedStatement statement) {

        try {
            statement.execute();
            statement.getConnection().close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    default PreparedStatement prepareStatement(String query, String... vars) {

        try (Connection connection = getConnection() ;
             PreparedStatement preparedStatement = connection.prepareStatement(query)){

            int x = 0;

            if (query.contains("?") && vars.length != 0) {
                for (String var : vars) {
                    x++;
                    preparedStatement.setString(x, var);
                }
            }

            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    void createTables();

}

package com.jcrm.dnio.project2.jcrm_dnio_project_2.databaseSetup;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseLink {
    Connection connection = null;

    public DatabaseLink(String url, String user, String password) {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.format("[SQL]:[ERROR]:[CONSTRUCTOR]: %s\n", e.getMessage());
        }
    }

    private PreparedStatement getStatement(String sqlCommand) throws SQLException {
        return this.connection.prepareStatement(sqlCommand);
    }

    public String query(String sqlQuery, DatabaseCallback onResult) {
        try {

            PreparedStatement ps = getStatement(sqlQuery);

            ResultSet result = ps.executeQuery();

            while (result.next()) {
                if(!onResult.equals(null)) {
                    onResult.call(result);
                }
            }

            return null;
        } catch (Exception e) {
            System.out.format("[SQL]:[ERROR]:[QUERY]: %s\n", e.getMessage());
            return "";
        }
    }

    public int update(String sqlCommand) {
        try {
            PreparedStatement ps = getStatement(sqlCommand);

            int result = ps.executeUpdate();

            return result;
        } catch (Exception e) {
            System.out.format("UPDATE ERROR: %s\n", e.getMessage());
            return 1;
        }
    }
}


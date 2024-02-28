package com.seatrade.util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtility {

    private static Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/seatrade";
    private static final String USER = "justin";
    private static final String PASSWORD = "bishal";

    public DatabaseUtility() {
        try{
            this.connection=DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    // Method to close a database connection
    public static void closeConnection() {
        if ( connection != null) {
            try {
                 connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

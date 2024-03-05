package com.seatrade.util.database;

import org.hibernate.annotations.processing.SQL;

import java.sql.*;

public class DatabaseUtility {

    private static final String URL = "jdbc:mysql://localhost:3306/seatrade";
    private static final String USER = "justin";
    private static final String PASSWORD = "bishal";

    // Static block to load the JDBC driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method to get a database connection
    // where is connection opened?
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Method to close a database connection
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        if(connection != null){
            try{
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(preparedStatement != null){
            try{
                preparedStatement.close();
            } catch (SQLException ex){
                ex.getMessage();
            }
        }
        if(resultSet != null){
            try {
                resultSet.close();

            } catch (SQLException ex){
                throw  new RuntimeException();
            }
        }

    }
}

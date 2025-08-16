package com.hotel.hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBmanager {
     public final Connection connection;
     public final Statement statement;
     private static final String hdbURL = "jdbc:mysql://localhost:3306/homebookingapp";
     private static final String username = "root";
     private static final String password = "uhmono";

     public DBmanager(Connection connection, Statement statement){
         this.connection = connection;
         this.statement = statement;
     }

    public static DBmanager createConnection() throws SQLException {
         Connection connection1;
         connection1 = DriverManager.getConnection(hdbURL, username, password);
         Statement statement = connection1.createStatement();
         DBmanager dBmanager = new DBmanager(connection1, statement);
         System.out.println("Connected to the database!");
         return dBmanager;
    }

    public static void closeConnections(DBmanager dBmanager) {
        try {
            if (dBmanager.statement != null && !dBmanager.statement.isClosed()){
                dBmanager.statement.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        try {
            if (dBmanager.connection != null && !dBmanager.connection.isClosed()){
                dBmanager.connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

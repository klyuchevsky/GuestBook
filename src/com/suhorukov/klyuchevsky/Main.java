package com.suhorukov.klyuchevsky;

import com.suhorukov.klyuchevsky.database.GuestBookDB;
import com.suhorukov.klyuchevsky.database.H2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        String driver = "jdbc:h2:mem";
        String database = "mydatabase";
        String user = "user1";
        String pass = "password1";
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(driver + ":" + database, user, pass);
            GuestBookDB db = new H2(connection);
            db.createTable();
//            new UIController(new ConsoleReader(), new ConsoleDisplay(), new BusinessController(db));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
            }
        }
    }

}

package com.suhorukov.klyuchevsky.database;

import com.suhorukov.klyuchevsky.Record;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class H2 implements GuestBookDB {
    private Connection connection;
    private static int identifier;

    public H2(Connection connection) {
        this.connection = connection;

    }

    @Override
    public void createTable() throws SQLException {
        Statement statement = null;
        String query = "create table if not exists POSTS (ID integer, POSTDATE date, MESSAGE varchar(255))";
        try {
            statement = connection.createStatement();
            statement.execute(query);
            identifier = getMaxId();
        } finally {
            if (statement != null) statement.close();
        }
    }

    @Override
    public void addRecord(String message) throws SQLException {
        PreparedStatement statement = null;
        String query = "insert into POSTS (ID,POSTDATE, MESSAGE) values(?, ?, ?)";
        Date curDate = new Date(new GregorianCalendar().getTimeInMillis());
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, identifier++);
            statement.setDate(2, curDate);
            statement.setString(3, message);
            int res = statement.executeUpdate();
        } finally {
            if (statement != null) statement.close();
        }
    }

    private int getMaxId() throws SQLException {
        Statement statement = null;
        String query = "select max(ID) as MID from POSTS";
        int id = 1;
        try {
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            if (res.next()) {
                id = res.getInt("MID");
            }
        } finally {
            if (statement != null) statement.close();
        }
        return id != 0 ? id : 1;
    }

    @Override
    public List<Record> getRecords() throws SQLException {
        Statement statement = null;
        List<Record> result = new ArrayList<Record>();
        String query = "select * from POSTS";
        try {
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                result.add(new Record(res.getInt("ID"), res.getDate("POSTDATE"), res.getString("MESSAGE")));
            }

        } finally {
            if (statement != null) statement.close();
        }
        return result;
    }
}

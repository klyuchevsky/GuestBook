package com.suhorukov.klyuchevsky;

import com.suhorukov.klyuchevsky.database.GuestBookDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessController implements GuestBookController {

    private GuestBookDB db;

    public BusinessController(GuestBookDB db) {
        if (db == null)
            throw new IllegalArgumentException("Не инициализирован контроллер базы данных");

        this.db = db;
    }

    public void addRecord(String message) {
        try {
            db.addRecord(message);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Record> getRecords() {
        List<Record> list = new ArrayList<>();
        try {
            list = db.getRecords();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
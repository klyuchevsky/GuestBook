package com.suhorukov.klyuchevsky;

import java.util.List;

public interface GuestBookController {
    void addRecord(String message);

    List<Record> getRecords();
}

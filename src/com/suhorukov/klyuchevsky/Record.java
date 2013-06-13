package com.suhorukov.klyuchevsky;

import java.util.Date;

public class Record {
    private int identifier;
    private Date postDate;
    private String postMessage;

    public Record(int identifier, Date postDate, String postMessage) {
        this.identifier = identifier;
        this.postDate = postDate;
        this.postMessage = postMessage;
    }

    public int getIdentifier() {
        return identifier;
    }

    public Date getPostDate() {
        return postDate;
    }

    public String getPostMessage() {
        return postMessage;
    }

}

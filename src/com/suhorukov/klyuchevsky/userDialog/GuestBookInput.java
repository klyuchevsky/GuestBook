package com.suhorukov.klyuchevsky.userDialog;

import java.io.Closeable;

public interface GuestBookInput extends Closeable {
    public String getCommand();
}

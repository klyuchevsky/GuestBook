package com.suhorukov.klyuchevsky.userDialog;

import java.io.Closeable;
import java.io.IOException;

public interface GuestBookOutput extends Closeable {
    public void sendResult(String message) throws IOException;
}

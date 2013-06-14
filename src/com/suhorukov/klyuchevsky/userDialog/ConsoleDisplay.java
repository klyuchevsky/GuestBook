package com.suhorukov.klyuchevsky.userDialog;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class ConsoleDisplay implements GuestBookOutput {
    private OutputStream out = System.out;
    Writer writer = new OutputStreamWriter(out);

    public void sendResult(String message) throws IOException {
        writer.write(message + "\n\r");
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }

}

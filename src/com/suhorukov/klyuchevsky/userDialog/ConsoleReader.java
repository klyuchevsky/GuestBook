package com.suhorukov.klyuchevsky.userDialog;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ConsoleReader implements GuestBookInput {
    private InputStream in = System.in;
    private Scanner scanner;

    public ConsoleReader() {
        scanner = new Scanner(in);
    }

    public String getCommand() {
        return scanner.nextLine();
    }

    @Override
    public void close() throws IOException {
        scanner.close();
    }

}

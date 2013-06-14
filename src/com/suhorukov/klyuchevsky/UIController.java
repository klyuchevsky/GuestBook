package com.suhorukov.klyuchevsky;

import com.suhorukov.klyuchevsky.userDialog.GuestBookInput;
import com.suhorukov.klyuchevsky.userDialog.GuestBookOutput;

import java.io.IOException;
import java.util.List;

public class UIController {
    private GuestBookInput input;
    private GuestBookOutput output;
    private GuestBookController guestBook;

    public UIController(GuestBookInput input, GuestBookOutput output, BusinessController businessController) throws IOException {
        if (input == null || output == null || businessController == null)
            throw new IllegalArgumentException("Инициализированы не все параметры!");

        this.input = input;
        this.output = output;
        this.guestBook = businessController;

        dialog();
    }

    private void dialog() throws IOException {
        String userString;
        try {
            while (true) {
                userString = input.getCommand();
                userString = userString.toLowerCase().trim();
                if (!userString.isEmpty()) {
                    if ("exit".equals(userString)) break;
                    if ("list".equals(userString)) {
                        List<Record> list = guestBook.getRecords();
                        for (Record post : list) {
                            output.sendResult(post.getIdentifier() + " " + post.getPostDate() + " " + post.getPostMessage());
                        }
                        output.sendResult("Всего записей " + list.size());
                        continue;
                    }
                    if (userString.startsWith("add ")) {
                        String[] userCommand = userString.split("\\s", 2);
                        if (userCommand.length == 2) {
                            guestBook.addRecord(userCommand[1]);
                            continue;
                        }
                    }
                    output.sendResult("Не верный формат команды");
                } else {
                    output.sendResult("Введите команду:");
                }

            }
        } finally {
            input.close();
            output.close();
        }
    }
}
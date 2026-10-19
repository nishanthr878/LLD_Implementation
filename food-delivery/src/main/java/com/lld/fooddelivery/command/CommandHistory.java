package com.lld.fooddelivery.command;

import java.util.ArrayDeque;
import java.util.Deque;

public class CommandHistory {
    private final Deque<OrderCommand> history = new ArrayDeque<>();

    public void executeCommand(OrderCommand command) {
        command.execute();
        history.push(command);
    }

    public void undoLast() {
        if (!history.isEmpty()) {
            OrderCommand last = history.pop();
            last.undo();
        } else {
            System.out.println("[HISTORY] NOTHING TO UNDO");
        }

    }
}

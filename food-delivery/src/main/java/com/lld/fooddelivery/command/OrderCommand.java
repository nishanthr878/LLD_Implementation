package com.lld.fooddelivery.command;

public interface OrderCommand {
    void execute();
    void undo();
}

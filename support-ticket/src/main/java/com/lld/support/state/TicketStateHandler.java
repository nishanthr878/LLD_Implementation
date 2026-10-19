package com.lld.support.state;

import com.lld.support.model.Ticket;

public interface TicketStateHandler {
    void assign(Ticket ticket);
    void waitForInput(Ticket ticket);
    void resolve(Ticket ticket);
    void close(Ticket ticket);
}

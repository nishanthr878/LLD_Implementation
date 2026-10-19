package com.lld.support.state;

import com.lld.support.model.Ticket;

public class ClosedState implements TicketStateHandler {

    @Override
    public void assign(Ticket ticket) {
        System.out.println("[CLOSED] Ticket " + ticket.getId() + " is closed. No actions allowed.");
    }

    @Override
    public void waitForInput(Ticket ticket) {
        System.out.println("[CLOSED] Ticket " + ticket.getId() + " is closed. No actions allowed.");
    }

    @Override
    public void resolve(Ticket ticket) {
        System.out.println("[CLOSED] Ticket " + ticket.getId() + " is closed. No actions allowed.");
    }

    @Override
    public void close(Ticket ticket) {
        System.out.println("[CLOSED] Already closed.");
    }
}

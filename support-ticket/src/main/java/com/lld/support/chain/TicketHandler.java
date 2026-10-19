package com.lld.support.chain;

import com.lld.support.model.Ticket;

public abstract class TicketHandler {

    protected TicketHandler next;

    public TicketHandler setNext(TicketHandler next) {
        this.next = next;
        return next;
    }

    public abstract void handle(Ticket ticket);

    protected void passToNext(Ticket ticket) {
        if (next != null) {
            next.handle(ticket);
        } else {
            System.out.println("[CHAIN] No handler could process ticket: " + ticket.getId());
        }
    }
}

package com.lld.support.state;

import com.lld.support.model.Ticket;
import com.lld.support.model.TicketState;

public class OpenState implements TicketStateHandler {

    @Override
    public void assign(Ticket ticket) {
        System.out.println("[OPEN] Assigning ticket " + ticket.getId() + " to agent.");
        ticket.setState(TicketState.IN_PROGRESS);
    }

    @Override
    public void waitForInput(Ticket ticket) {
        System.out.println("[OPEN] Cannot wait for input — ticket not yet assigned.");
    }

    @Override
    public void resolve(Ticket ticket) {
        System.out.println("[OPEN] Cannot resolve — ticket not yet assigned.");
    }

    @Override
    public void close(Ticket ticket) {
        System.out.println("[OPEN] Cannot close — ticket not yet resolved.");
    }
}

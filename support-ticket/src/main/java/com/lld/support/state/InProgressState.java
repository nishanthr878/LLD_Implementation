package com.lld.support.state;

import com.lld.support.model.Ticket;
import com.lld.support.model.TicketState;

public class InProgressState implements TicketStateHandler {

    @Override
    public void assign(Ticket ticket) {
        System.out.println("[IN_PROGRESS] Already assigned.");
    }

    @Override
    public void waitForInput(Ticket ticket) {
        System.out.println("[IN_PROGRESS] Waiting for user input on ticket " + ticket.getId());
        ticket.setState(TicketState.WAITING_FOR_INPUT);
    }

    @Override
    public void resolve(Ticket ticket) {
        System.out.println("[IN_PROGRESS] Resolving ticket " + ticket.getId());
        ticket.setState(TicketState.RESOLVED);
    }

    @Override
    public void close(Ticket ticket) {
        System.out.println("[IN_PROGRESS] Cannot close — ticket not yet resolved.");
    }
}

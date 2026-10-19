package com.lld.support.state;

import com.lld.support.model.Ticket;
import com.lld.support.model.TicketState;

public class ResolvedState implements TicketStateHandler {

    @Override
    public void assign(Ticket ticket) {
        System.out.println("[RESOLVED] Cannot reassign — already resolved.");
    }

    @Override
    public void waitForInput(Ticket ticket) {
        System.out.println("[RESOLVED] Cannot wait for input — already resolved.");
    }

    @Override
    public void resolve(Ticket ticket) {
        System.out.println("[RESOLVED] Already resolved.");
    }

    @Override
    public void close(Ticket ticket) {
        System.out.println("[RESOLVED] Closing ticket " + ticket.getId() + ". Done.");
        ticket.setState(TicketState.CLOSED);
    }
}

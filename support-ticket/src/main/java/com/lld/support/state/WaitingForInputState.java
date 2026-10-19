package com.lld.support.state;

import com.lld.support.model.Ticket;
import com.lld.support.model.TicketState;

public class WaitingForInputState implements TicketStateHandler {

    @Override
    public void assign(Ticket ticket) {
        System.out.println("[WAITING] User responded. Resuming ticket " + ticket.getId());
        ticket.setState(TicketState.IN_PROGRESS);
    }

    @Override
    public void waitForInput(Ticket ticket) {
        System.out.println("[WAITING] Already waiting for input.");
    }

    @Override
    public void resolve(Ticket ticket) {
        System.out.println("[WAITING] Cannot resolve — still waiting for user response.");
    }

    @Override
    public void close(Ticket ticket) {
        System.out.println("[WAITING] Cannot close — ticket not yet resolved.");
    }
}

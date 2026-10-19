package com.lld.support.chain;

import com.lld.support.model.Ticket;
import com.lld.support.model.TicketPriority;
import com.lld.support.model.TicketState;

public class L2SpecialistHandler extends TicketHandler {

    @Override
    public void handle(Ticket ticket) {
        if (ticket.getPriority() == TicketPriority.HIGH) {
            System.out.println("[L2 SPECIALIST] Handling HIGH priority ticket: " + ticket.getId() + " — " + ticket.getTitle());
            ticket.setState(TicketState.IN_PROGRESS);
        } else {
            System.out.println("[L2 SPECIALIST] Cannot handle priority " + ticket.getPriority() + ". Escalating.");
            passToNext(ticket);
        }
    }
}

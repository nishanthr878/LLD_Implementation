package com.lld.support.chain;

import com.lld.support.model.Ticket;
import com.lld.support.model.TicketPriority;
import com.lld.support.model.TicketState;

public class L1AgentHandler extends TicketHandler {

    @Override
    public void handle(Ticket ticket) {
        if (ticket.getPriority() == TicketPriority.MEDIUM) {
            System.out.println("[L1 AGENT] Handling MEDIUM priority ticket: " + ticket.getId() + " — " + ticket.getTitle());
            ticket.setState(TicketState.IN_PROGRESS);
    } else {
            System.out.println("[L1 AGENT] Cannot handle priority " + ticket.getPriority() + ". Escalating.");
            passToNext(ticket);
        }
    }
}

package com.lld.support.chain;

import com.lld.support.model.Ticket;
import com.lld.support.model.TicketPriority;
import com.lld.support.model.TicketState;

public class BotHandler extends TicketHandler {

    @Override
    public void handle(Ticket ticket) {
        if (ticket.getPriority() == TicketPriority.LOW) {
            System.out.println("[BOT] Handling LOW priority ticket: " + ticket.getId() + " — " + ticket.getTitle());
            ticket.setState(TicketState.IN_PROGRESS);
        } else {
            System.out.println("[BOT] Cannot handle priority " + ticket.getPriority() + ". Escalating.");
            passToNext(ticket);
        }
    }
}

package com.lld.support.chain;

import com.lld.support.model.Ticket;
import com.lld.support.model.TicketState;

public class ManagerHandler extends TicketHandler {
    @Override
    public void handle(Ticket ticket) {
        System.out.println("[MANAGER] Taking ownership of ticket: " + ticket.getId() + " — " + ticket.getTitle() + " (Priority: " + ticket.getPriority() + ")");
        ticket.setState(TicketState.IN_PROGRESS);
    }
}

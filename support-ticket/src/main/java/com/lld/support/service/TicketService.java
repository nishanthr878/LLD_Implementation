package com.lld.support.service;

import com.lld.support.chain.*;
import com.lld.support.model.Ticket;
import com.lld.support.model.TicketPriority;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TicketService {

    private final TicketHandler handlerChain;
    private final Map<String, Ticket> tickets = new HashMap<>();

    public TicketService() {

        BotHandler bot = new BotHandler();
        L1AgentHandler l1 = new L1AgentHandler();
        L2SpecialistHandler l2 = new L2SpecialistHandler();
        ManagerHandler manager = new ManagerHandler();

        bot.setNext(l1).setNext(l2).setNext(manager);
        this.handlerChain = bot;
    }

    public Ticket createTicket(String title, String description, String priority) {
        Ticket ticket = new Ticket(title, description, TicketPriority.valueOf(priority.toUpperCase()));
        tickets.put(ticket.getId(), ticket);
        System.out.println("[SERVICE] Ticket created: " + ticket.getId() + " | Priority: " + ticket.getPriority());
        handlerChain.handle(ticket);  // immediately route through chain
        return ticket;
    }

    public Ticket assign(String ticketId) {
        Ticket ticket = getTicket(ticketId);
        ticket.assign();
        return ticket;
    }

    public Ticket waitForInput(String ticketId) {
        Ticket ticket = getTicket(ticketId);
        ticket.waitForInput();
        return ticket;
    }

    public Ticket resolve(String ticketId) {
        Ticket ticket = getTicket(ticketId);
        ticket.resolve();
        return ticket;
    }

    public Ticket close(String ticketId) {
        Ticket ticket = getTicket(ticketId);
        ticket.close();
        return ticket;
    }

    public Ticket getTicket(String ticketId) {
        Ticket ticket = tickets.get(ticketId);
        if (ticket == null) throw new RuntimeException("Ticket not found: " + ticketId);
        return ticket;
    }



}

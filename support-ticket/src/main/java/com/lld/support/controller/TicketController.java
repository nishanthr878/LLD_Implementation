package com.lld.support.controller;

import com.lld.support.model.Ticket;
import com.lld.support.service.TicketService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/create")
    public Ticket create(@RequestParam String title,
                         @RequestParam String description,
                         @RequestParam String priority) {
        return ticketService.createTicket(title, description, priority);
    }

    @PostMapping("/{id}/assign")
    public Ticket assign(@PathVariable String id) {
        return ticketService.assign(id);
    }


    support-ticket




    @PostMapping("/{id}/wait")
    public Ticket waitForInput(@PathVariable String id) {
        return ticketService.waitForInput(id);
    }

    @PostMapping("/{id}/resolve")
    public Ticket resolve(@PathVariable String id) {
        return ticketService.resolve(id);
    }

    @PostMapping("/{id}/close")
    public Ticket close(@PathVariable String id) {
        return ticketService.close(id);
    }

    @GetMapping("/{id}")
    public Ticket get(@PathVariable String id) {
        return ticketService.getTicket(id);
    }
}
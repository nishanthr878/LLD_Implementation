package com.lld.support.model;

import com.lld.support.state.ClosedState;
import com.lld.support.state.OpenState;

import com.lld.support.state.ResolvedState;
import com.lld.support.state.InProgressState;
import com.lld.support.state.WaitingForInputState;
import com.lld.support.state.TicketStateHandler;
import lombok.Data;

import java.util.UUID;

@Data
public class Ticket {
    private final String id;
    private final String title;
    private final String description;
    private final TicketPriority priority;
    private TicketState state;
    private TicketStateHandler stateHandler; // holds the behaviour

    public Ticket(String title, String description, TicketPriority priority) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.state = TicketState.OPEN;
        this.stateHandler = new OpenState();
    }

    // When enum changes, swap the handler too
    public void setState(TicketState state) {
        this.state = state;
        this.stateHandler = switch (state) {
            case OPEN -> new OpenState();
            case IN_PROGRESS -> new InProgressState();
            case WAITING_FOR_INPUT -> new WaitingForInputState();
            case RESOLVED -> new ResolvedState();
            case CLOSED -> new ClosedState();
        };
    }

    // delegation methods — ticket asks its current handler
    public void assign()   {
        stateHandler.assign(this);
    }

    public void waitForInput() {
        stateHandler.waitForInput(this);
    }

    public void resolve()      {
        stateHandler.resolve(this);
    }

    public void close() {
        stateHandler.close(this);
    }
}

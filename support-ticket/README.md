# Support Ticket System — LLD Phase 4

A Spring Boot project demonstrating **Chain of Responsibility** and **State** patterns through a support ticket lifecycle. Built as part of an LLD learning series.

## Patterns Covered

| Pattern | Where | What it solves |
|---|---|---|
| Chain of Responsibility | `chain/` | Routes tickets to the right handler based on priority |
| State | `state/` | Controls what operations are valid at each lifecycle stage |

## Project Structure

```
src/main/java/com/lld/support/
├── model/
│   ├── TicketPriority.java     (LOW, MEDIUM, HIGH)
│   ├── TicketState.java        (OPEN, IN_PROGRESS, WAITING_FOR_INPUT, RESOLVED, CLOSED)
│   └── Ticket.java             (holds state handler, delegates operations)
├── chain/
│   ├── TicketHandler.java      ← abstract base with setNext() + passToNext()
│   ├── BotHandler.java         (handles LOW)
│   ├── L1AgentHandler.java     (handles MEDIUM)
│   ├── L2SpecialistHandler.java (handles HIGH)
│   └── ManagerHandler.java     (terminal — handles anything that falls through)
├── state/
│   ├── TicketStateHandler.java ← interface: assign, waitForInput, resolve, close
│   ├── OpenState.java
│   ├── InProgressState.java
│   ├── WaitingForInputState.java
│   ├── ResolvedState.java
│   └── ClosedState.java
├── service/
│   └── TicketService.java      (builds chain, stores tickets, delegates state ops)
└── controller/
    └── TicketController.java
```

## Running the Project

**Prerequisites:** Java 21, Maven

```bash
mvn clean compile
mvn spring-boot:run
```

## API Endpoints

### Create a ticket (triggers chain routing immediately)
```bash
# LOW — bot handles it
curl -X POST "http://localhost:8080/api/tickets/create?title=Password reset&description=Forgot password&priority=LOW"

# MEDIUM — escalates to L1
curl -X POST "http://localhost:8080/api/tickets/create?title=Billing issue&description=Wrong charge&priority=MEDIUM"

# HIGH — escalates to L2
curl -X POST "http://localhost:8080/api/tickets/create?title=System down&description=Production outage&priority=HIGH"
```

### State transitions
```bash
curl -X POST "http://localhost:8080/api/tickets/{id}/assign"
curl -X POST "http://localhost:8080/api/tickets/{id}/wait"
curl -X POST "http://localhost:8080/api/tickets/{id}/resolve"
curl -X POST "http://localhost:8080/api/tickets/{id}/close"
curl -GET  "http://localhost:8080/api/tickets/{id}"
```

## What Happens on Create

```
# LOW
[SERVICE] Ticket created: 5cc6bf8a | Priority: LOW
[BOT] Handling LOW priority ticket: 5cc6bf8a — Password reset

# MEDIUM
[SERVICE] Ticket created: 532f4564 | Priority: MEDIUM
[BOT] Cannot handle priority MEDIUM. Escalating.
[L1 AGENT] Handling MEDIUM priority ticket: 532f4564 — Billing issue

# HIGH
[SERVICE] Ticket created: e15978be | Priority: HIGH
[BOT] Cannot handle priority HIGH. Escalating.
[L1 AGENT] Cannot handle priority HIGH. Escalating.
[L2 SPECIALIST] Handling HIGH priority ticket: e15978be — System down
```

## What Happens on State Transitions

```
# Happy path
[IN_PROGRESS] Resolving ticket 532f4564
[RESOLVED] Closing ticket 532f4564. Done.

# Illegal transitions — absorbed gracefully
[IN_PROGRESS] Already assigned.
[CLOSED] Already closed.
```

## Key Design Decisions

**Why Chain of Responsibility over if-else for routing?**
Adding a new priority (e.g., CRITICAL) with if-else = open the routing block. With Chain = add one new handler class, insert it in the chain. The entry point (`handlerChain.handle(ticket)`) never changes.

**Why `setNext()` returns `next` instead of `void`?**
Enables fluent chaining: `bot.setNext(l1).setNext(l2).setNext(manager)` instead of four separate statements.

**Why State pattern over switch statements?**
5 states × 4 methods = 20 switch branches in `Ticket`. Add a state → touch all 4 methods. With State = add one new class. `Ticket` never changes.

**Why keep both the enum and the state handler?**
The enum is for serialization, logging, and API responses. The handler is for behavior. `setState()` updates both atomically so they never diverge.

## Valid State Transitions

```
OPEN → IN_PROGRESS          (assign)
IN_PROGRESS → WAITING       (waitForInput)
IN_PROGRESS → RESOLVED      (resolve)
WAITING → IN_PROGRESS       (assign — user responded)
RESOLVED → CLOSED           (close)
```

All other transitions are rejected silently by the state object.

## Related Projects

- [Phase 1 — Notification Service](../notification-service) — Builder, Factory Method, Abstract Factory, Singleton
- [Phase 2 — Food Delivery](../food-delivery) — Strategy, Observer, Command, Template Method
- [Phase 3 — Payment Gateway](../payment-gateway) — Adapter, Decorator, Facade, Proxy
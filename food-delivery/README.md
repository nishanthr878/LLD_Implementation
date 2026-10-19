# Food Delivery — LLD Phase 2

A Spring Boot project demonstrating **behavioral design patterns** through a real food delivery order lifecycle. Built as part of an LLD learning series.

## Patterns Covered

| Pattern | Where | What it solves |
|---|---|---|
| Strategy | `strategy/` | Swappable pricing algorithms at runtime |
| Observer | `observer/` | Multi-party notifications on order status change |
| Command | `command/` | Order actions as objects — undoable and queueable |
| Template Method | `template/` | Fixed order processing pipeline, variable payment step |

## Project Structure

```
src/main/java/com/lld/fooddelivery/
├── model/
│   ├── enums/
│   │   ├── OrderStatus.java        (PLACED, CONFIRMED, PREPARING, OUT_FOR_DELIVERY, DELIVERED, CANCELLED)
│   │   ├── PaymentMethod.java      (CASH_ON_DELIVERY, CREDIT_CARD, UPI, WALLET)
│   │   └── PaymentStatus.java      (PENDING, SUCCESS, FAILED, REFUNDED)
│   ├── Customer.java
│   ├── MenuItem.java
│   ├── Restaurant.java
│   ├── OrderItem.java
│   ├── Order.java
│   ├── Payment.java
│   ├── DeliveryAgent.java
│   └── DeliveryTask.java
├── strategy/
│   ├── PricingStrategy.java        ← interface
│   ├── RegularPricing.java         (items + ₹30 delivery)
│   ├── SurgePricing.java           (items × 1.5 + ₹60 delivery)
│   └── LoyaltyPricing.java         (items − 10% + free delivery)
├── observer/
│   ├── OrderObserver.java          ← interface
│   ├── CustomerNotificationObserver.java
│   ├── RestaurantObserver.java
│   └── DeliveryAgentObserver.java
├── command/
│   ├── OrderCommand.java           ← interface
│   ├── PlaceOrderCommand.java
│   ├── CancelOrderCommand.java
│   └── CommandHistory.java         (undo stack)
├── template/
│   ├── OrderProcessor.java         ← abstract base class
│   ├── CodOrderProcessor.java
│   └── OnlineOrderProcessor.java
├── service/
│   └── OrderService.java           (subject — manages observers + strategy)
└── controller/
    └── OrderController.java
```

## Running the Project

**Prerequisites:** Java 21, Maven

```bash
mvn clean compile
mvn spring-boot:run
```

## API Endpoints

### Place an Order
```bash
POST /api/orders/place

# Regular pricing, online payment
curl -X POST "http://localhost:8080/api/orders/place?customerName=Nishanth&restaurantName=Meghana Foods&isSurge=false&isCod=false"

# Surge pricing, COD
curl -X POST "http://localhost:8080/api/orders/place?customerName=Nishanth&restaurantName=Meghana Foods&isSurge=true&isCod=true"
```

### Cancel an Order
```bash
POST /api/orders/cancel?orderId={orderId}

curl -X POST "http://localhost:8080/api/orders/cancel?orderId=your-order-id"
```

### Undo Last Action
```bash
POST /api/orders/undo

curl -X POST "http://localhost:8080/api/orders/undo"
```

## What Happens When You Place an Order

```
[ORDER] Status updated to: PLACED
[CUSTOMER] Order placed successfully.
[PROCESSOR] Order validated
[ONLINE/COD] Payment processed
[ORDER] Status updated to: CONFIRMED
[CUSTOMER] Your order is confirmed
[RESTAURANT] New order received. Start preparing.
[PROCESSOR] Restaurant notified
[ORDER] Status updated to: PREPARING
[DELIVERY AGENT] Order ready soon. Head to: <address>
[PROCESSOR] Delivery agent assigned
```

## Key Design Decisions

**Why `updateStatus` instead of `order.setStatus()` directly?**
Every status change must trigger observer notifications. `updateStatus` is the single controlled entry point — status change and notification are always atomic.

**Why Strategy over if-else for pricing?**
Adding a new pricing model (e.g., corporate discount) requires zero changes to `OrderService`. One new class implementing `PricingStrategy` is all it takes.

**Why Command for order actions?**
A method call is fire-and-forget. Command wraps the action as an object — it can be queued, logged, retried, and undone. `CommandHistory` maintains a stack for undo support.

**Why Template Method over Strategy for order processing?**
Template Method is appropriate when multiple steps may vary across subclasses. If only one step varies, Strategy is cleaner. Here, Template Method was used deliberately to demonstrate the pattern.

## Pricing

| Strategy | Formula |
|---|---|
| Regular | `(items total) + ₹30` |
| Surge | `(items total × 1.5) + ₹60` |
| Loyalty | `(items total − 10%) + ₹0` |

## Related Projects

- [Phase 1 — Notification Service](../notification-service) — Builder, Factory Method, Abstract Factory, Singleton
- Phase 3 — Payment Gateway Wrapper — Decorator, Adapter, Facade, Proxy *(coming soon)*
- Phase 4 — Support Ticket System — Chain of Responsibility, State *(coming soon)*
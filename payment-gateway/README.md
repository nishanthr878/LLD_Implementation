# Payment Gateway Wrapper — LLD Phase 3

A Spring Boot project demonstrating **structural design patterns** through a payment gateway integration. Built as part of an LLD learning series.

## Patterns Covered

| Pattern | Where | What it solves |
|---|---|---|
| Adapter | `gateway/` | Translates provider SDKs into a unified interface |
| Decorator | `decorator/` | Adds logging and retry without touching provider code |
| Facade | `facade/` | Hides all wiring behind one simple entry point |
| Proxy | `proxy/` | Blocks unauthorized merchants before reaching the gateway |

## Project Structure

```
src/main/java/com/lld/payment/
├── gateway/
│   ├── PaymentGateway.java              ← unified interface
│   ├── RazorpayGateway.java             ← Adapter (Razorpay SDK → our interface)
│   └── StripeGateway.java               ← Adapter (Stripe SDK → our interface)
├── external/
│   ├── RazorpayClient.java              ← simulated Razorpay SDK
│   └── StripeClient.java                ← simulated Stripe SDK
├── decorator/
│   ├── PaymentGatewayDecorator.java     ← abstract base (default passthrough)
│   ├── LoggingDecorator.java            ← logs before/after every call
│   └── RetryDecorator.java              ← retries charge up to 3 times on failure
├── facade/
│   └── PaymentFacade.java               ← assembles the full stack, hides wiring
├── proxy/
│   └── PaymentGatewayProxy.java         ← blocks unauthorized merchants
└── controller/
    └── PaymentController.java
```

## Running the Project

**Prerequisites:** Java 21, Maven

```bash
mvn clean compile
mvn spring-boot:run
```

## API Endpoints

### Charge via Facade (all patterns firing)
```bash
curl -X POST "http://localhost:8080/api/payment/charge?amount=500&currency=INR&orderId=order_123"
```

### Fetch Status
```bash
curl "http://localhost:8080/api/payment/status?paymentId=rzp_txn_123"
```

### Refund
```bash
curl -X POST "http://localhost:8080/api/payment/refund?paymentId=rzp_txn_123&amount=500"
```

### Charge via Authorized Proxy
```bash
curl -X POST "http://localhost:8080/api/payment/charge-authorized?amount=500&currency=INR&orderId=order_456"
```

### Charge via Unauthorized Proxy (blocked)
```bash
curl -X POST "http://localhost:8080/api/payment/charge-unauthorized?amount=500&currency=INR&orderId=order_789"
# Returns: BLOCKED: Unauthorized merchant: merchant_999
```

## What Happens When You Charge via Facade

```
[LOG] charge() called — amount: 500.0 currency: INR orderId: order_123
[RETRY] charge attempt 1
[RAZORPAY SDK] Initiating transaction: ₹500.0 | Receipt: order_123
[LOG] charge() result — paymentId: rzp_txn_1792410436946
```

## What Happens With Authorized Proxy

```
[PROXY] Merchant merchant_001 authorized. Forwarding charge.
[LOG] charge() called — amount: 500.0 currency: INR orderId: order_456
[RETRY] charge attempt 1
[RAZORPAY SDK] Initiating transaction: ₹500.0 | Receipt: order_456
[LOG] charge() result — paymentId: rzp_txn_1792410481244
```

## Key Design Decisions

**Why Adapter instead of calling Razorpay SDK directly?**
Two providers have completely different method names and signatures. Adapter gives you one interface to program against — swap Razorpay for Stripe without touching callers.

**Why Decorator instead of adding logging inside RazorpayGateway?**
Adding logging to `RazorpayGateway` means adding it to `StripeGateway` too. Every future provider gets the same treatment. Decorator separates cross-cutting concerns — one `LoggingDecorator` works for any provider, forever.

**Why Facade when callers could just build the stack themselves?**
Stack composition is error-prone and repeated. Wrong decorator order, wrong client instantiation — bugs scatter everywhere. Facade owns the wiring in one place. Change the stack = change one constructor.

**Why Proxy for auth instead of checking in the controller?**
Auth is not the controller's job. It's a gateway-level concern. Proxy intercepts before the real gateway, in the same layer where payment execution happens.

## Pattern Comparison

| Pattern | Wraps something? | Same interface? | Real object always called? | Purpose |
|---|---|---|---|---|
| Adapter | Yes (their SDK) | No — maps to ours | Yes | Fix mismatch |
| Decorator | Yes (PaymentGateway) | Yes | Yes | Add behavior |
| Facade | Yes (subsystem) | No — simpler surface | Yes | Hide complexity |
| Proxy | Yes (PaymentGateway) | Yes | No — decides | Control access |

## Related Projects

- [Phase 1 — Notification Service](../notification-service) — Builder, Factory Method, Abstract Factory, Singleton
- [Phase 2 — Food Delivery](../food-delivery) — Strategy, Observer, Command, Template Method
- Phase 4 — Support Ticket System — Chain of Responsibility, State *(coming soon)*
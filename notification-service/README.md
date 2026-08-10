## Notification Service
- Sends notification via Email, SMS and Push
- Supports multiple channel (eg senGrid vs SES for email)
- Simple or Complex (with attachment, templates, metadata)


| Pattern              | Problem it solves                                                                               |
| -------------------- | ----------------------------------------------------------------------------------------------- |
| **Factory Method**   | Create the right notifier (Email/SMS/Push) without the caller knowing the concrete class        |
| **Abstract Factory** | Swap entire provider families (e.g., AWS suite vs Twilio suite) without changing client code    |
| Builder              | Construct a `Notification` object that has 10+ optional fields without telescoping constructors |
| Singleton            | One shared `NotificationConfig` / provider registry across the app                              |



|
```bash
notification-service/
├── src/main/java/com/lld/notification/
│   ├── model/
│   │   └── Notification.java          ← Builder pattern
│   ├── notifier/
│   │   ├── Notifier.java              ← interface
│   │   ├── EmailNotifier.java
│   │   ├── SmsNotifier.java
│   │   └── PushNotifier.java
│   ├── factory/
│   │   ├── NotifierFactory.java       ← Factory Method
│   │   ├── AwsNotifierFactory.java    ← Abstract Factory (AWS family)
│   │   └── TwilioNotifierFactory.java ← Abstract Factory (Twilio family)
│   ├── config/
│   │   └── NotificationConfig.java    ← Singleton
│   └──
```

- If we have to write a constructor for an object that has `to`, `subject`, `body`, `channel`, `priority`, `templateId`, `attachments`, `metadata`, `retryCount`, `scheduleAt`?
- We will end up with ***telescoping constructor problem***
```java
public Notification(String to, String body) { ... }
public Notification(String to, String body, String subject) { ... }
public Notification(String to, String body, String subject, String channel) { ... }
public Notification(String to, String body, String subject, String channel, int priority) { ... }
// ... 6 more of these
```

Problems with this:
1. **Unreadable at call site** — `new Notification("a@b.com", "Hello", null, "EMAIL", 1, null, null, null, 3, null)` — what does that `3` mean?
2. **Order-sensitive** — swap two `String` arguments, compiler won't catch it, bug in production
3. **Combinatorial explosion** — 10 fields means potentially dozens of constructors to cover meaningful combinations
4. **Adding a new optional field** — you touch every constructor

```bash
# to create project sturcture
mkdir -p notification-service/src/main/java/com/lld/notification/{model,notifier,factory,config}
cd notification-service
```




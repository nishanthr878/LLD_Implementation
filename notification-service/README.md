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
```bash
# to create project sturcture
mkdir -p notification-service/src/main/java/com/lld/notification/{model,notifier,factory,config}
cd notification-service
```





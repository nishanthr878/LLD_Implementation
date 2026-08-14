package com.lld.notification.service;

import com.lld.notification.factory.NotifierFactory;
import com.lld.notification.model.Notification;
import com.lld.notification.notifier.Notifier;

public class NotificationService {
    private final NotifierFactory factory;

    public NotificationService(NotifierFactory factory) {
        this.factory = factory;
    }

    public void send(Notification notification) {
        Notifier notifier = switch (notification.getChannel().toUpperCase()) {
            case "EMAIL" -> factory.createEmailNotifier();
            case "SMS"   -> factory.createSmsNotifier();
            case "PUSH"  -> factory.createPushNotifier();
            default -> throw new IllegalArgumentException("Unknown channel");
        };
        notifier.send(notification);
    }
}

package com.lld.notification.notifier;

import com.lld.notification.model.Notification;

public class PushNotifier implements Notifier {
    @Override
    public void send(Notification notification) {
        System.out.println("[PUSH] To: " + notification.getTo()
                + " | Body: " + notification.getBody());
    }
}

package com.lld.notification.notifier;

import com.lld.notification.model.Notification;

public class AwsSmsNotifier implements Notifier {
    @Override
    public void send(Notification notification) {
        System.out.println("[AWS SNS] Sending SMS to: " + notification.getTo());
    }
}

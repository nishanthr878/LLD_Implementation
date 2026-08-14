package com.lld.notification.notifier;

import com.lld.notification.model.Notification;

public class AwsEmailNotifier implements Notifier {
    @Override
    public void send(Notification notification) {
        System.out.println("[AWS SES] Sending email to: " + notification.getTo());
    }
}

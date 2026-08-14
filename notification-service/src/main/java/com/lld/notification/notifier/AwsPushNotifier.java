package com.lld.notification.notifier;

import com.lld.notification.model.Notification;

public class AwsPushNotifier implements Notifier{
    @Override
    public void send(Notification notification) {
        System.out.println("[AWS Pinpoint] Sending push to: " + notification.getTo());
    }
}

package com.lld.notification.notifier;

import com.lld.notification.model.Notification;

public class TwilioPushNotifier implements Notifier{
    @Override
    public void send(Notification notification) {
        System.out.println("[Twilio Push] Sending push to: " + notification.getTo());
    }
}

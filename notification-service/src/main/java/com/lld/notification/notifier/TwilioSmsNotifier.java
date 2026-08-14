package com.lld.notification.notifier;

import com.lld.notification.model.Notification;

public class TwilioSmsNotifier implements Notifier{
    @Override
    public void send(Notification notification) {
        System.out.println("[Twilio SMS] Sending SMS to: " + notification.getTo());
    }
}

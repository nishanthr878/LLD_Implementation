package com.lld.notification.notifier;

import com.lld.notification.model.Notification;

public class TwilioEmailNotifier implements Notifier{
    @Override
    public void send(Notification notification) {
        System.out.println("[Twilio SendGrid] Sending email to: " + notification.getTo());
    }
}

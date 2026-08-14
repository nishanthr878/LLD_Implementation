package com.lld.notification.notifier;

import com.lld.notification.model.Notification;

public class SmsNotifer implements Notifier {
    @Override
    public void send(Notification notification) {
        System.out.println("[SMS] To: " + notification.getTo()
                + " | Body: " + notification.getBody());
    }
}

package com.lld.notification.notifier;

import com.lld.notification.model.Notification;

public class EmailNotifier implements Notifier{
    @Override
    public void send(Notification notification) {
        System.out.println("[EMAIL] To: " + notification.getTo()
                + " | Subject: " + notification.getSubject()
                + " | Body: " + notification.getBody());
    }
}

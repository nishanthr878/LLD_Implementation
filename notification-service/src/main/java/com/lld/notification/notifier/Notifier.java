package com.lld.notification.notifier;

import com.lld.notification.model.Notification;

public interface Notifier {
    void send(Notification notification);
}

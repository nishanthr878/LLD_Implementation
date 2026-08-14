package com.lld.notification.factory;


import com.lld.notification.notifier.Notifier;

public interface NotifierFactory {
   Notifier createEmailNotifier();
   Notifier createSmsNotifier();
   Notifier createPushNotifier();
}



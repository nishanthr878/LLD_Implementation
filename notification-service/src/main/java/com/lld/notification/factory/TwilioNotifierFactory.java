package com.lld.notification.factory;

import com.lld.notification.notifier.Notifier;
import com.lld.notification.notifier.TwilioEmailNotifier;
import com.lld.notification.notifier.TwilioPushNotifier;
import com.lld.notification.notifier.TwilioSmsNotifier;

public class TwilioNotifierFactory implements NotifierFactory{
    @Override
    public Notifier createEmailNotifier() {
        return new TwilioEmailNotifier();
    }

    @Override
    public Notifier createSmsNotifier() {
        return new TwilioSmsNotifier();
    }

    @Override
    public Notifier createPushNotifier() {
        return new TwilioPushNotifier();
    }
}

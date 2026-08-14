package com.lld.notification.factory;

import com.lld.notification.notifier.AwsEmailNotifier;
import com.lld.notification.notifier.AwsPushNotifier;
import com.lld.notification.notifier.AwsSmsNotifier;
import com.lld.notification.notifier.Notifier;

public class AwsNotifierFactory implements NotifierFactory {
    @Override
    public Notifier createEmailNotifier() {
        return new AwsEmailNotifier();
    }

    @Override
    public Notifier createSmsNotifier() {
        return new AwsSmsNotifier();
    }

    @Override
    public Notifier createPushNotifier() {
        return new AwsPushNotifier();
    }
}

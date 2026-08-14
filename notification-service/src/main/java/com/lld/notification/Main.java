package com.lld.notification;

import com.lld.notification.config.NotificationConfig;
import com.lld.notification.factory.AwsNotifierFactory;
import com.lld.notification.factory.NotifierFactory;
import com.lld.notification.factory.TwilioNotifierFactory;
import com.lld.notification.model.Notification;
import com.lld.notification.service.NotificationService;

public class Main {
    public static void main(String[] args) {

        // Singleton config
        NotificationConfig config = NotificationConfig.getInstance();
        System.out.println("Config loaded. Region " + config.getAwsRegion());

        // Build notifications using builder
        Notification emailNotif = new Notification.Builder(
                "user@example.com", "Your order has been placed", "EMAIL")
                .subject("Order Confirmation")
                .priority(1)
                .retryCount(config.getMaxRetryCount())
                .build();

        Notification smsNotif = new Notification.Builder(
                "+919876543210", "Your OTP is 4521", "SMS")
                .priority(1)
                .build();

        // use AWS Factory
        System.out.println("\n--- AWS Provider ----");
        NotifierFactory awsFactory = new AwsNotifierFactory();
        NotificationService awsService = new NotificationService(awsFactory);
        awsService.send(emailNotif);
        awsService.send(smsNotif);

        // Swith to twilio - zero other changes
        System.out.println("\n--- Twilio Provider ----");
        NotifierFactory twilioFactory = new TwilioNotifierFactory();
        NotificationService twilioService = new NotificationService(twilioFactory);
        twilioService.send(emailNotif);
        twilioService.send(smsNotif);
    }
}

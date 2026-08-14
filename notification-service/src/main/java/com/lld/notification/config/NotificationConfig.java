package com.lld.notification.config;

public class NotificationConfig {

    // volatile - eunsures visibility across threads
    private static volatile NotificationConfig instance;

    private String awsRegion;
    private String twilioAccountSid;
    private String defaultChannel;
    private int maxRetryCount;

    // private constructor - no one can cal new NotificationConfig()
    private NotificationConfig() {
        this.awsRegion = "ap-south-1";
        this.twilioAccountSid = "AC-DUMMY-SID";
        this.defaultChannel = "EMAIL";
        this.maxRetryCount = 3;
    }

    public static NotificationConfig getInstance() {
        if (instance == null) {
            synchronized (NotificationConfig.class) {
                if (instance == null) {
                    instance = new NotificationConfig();
                }
            }
        }
        return instance;
    }

    public String getAwsRegion() {
        return awsRegion;
    }
    public String getTwilioAccountSid() {
        return twilioAccountSid;
    }
    public String getDefaultChannel() {
        return defaultChannel;
    }
    public int getMaxRetryCount() {
        return maxRetryCount;
    }
}

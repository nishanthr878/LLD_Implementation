package  com.lld.notification.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Notification {

    private final String to;
    private final String subject;
    private final String body;
    private final String channel;
    private final int priority;
    private final String templateId;
    private final List<String> attachments;
    private final Map<String, String> metadata;
    private final int retryCount;
    private final LocalDateTime scheduledAt;

    // Private constructore - Only builder can call this
    private Notification(Builder builder) {
        this.to = builder.to;
        this.subject = builder.subject;
        this.body = builder.body;
        this.channel = builder.channel;
        this.priority = builder.priority;
        this.templateId = builder.templateId;
        this.attachments = builder.attachments;
        this.metadata = builder.metadata;
        this.retryCount = builder.retryCount;
        this.scheduledAt = builder.scheduledAt;
    }

    // Getters only - no setter, immutable
    public String getTo() { return to; }
    public String getSubject() { return subject; }
    public String getBody() { return body; }
    public String getChannel() { return channel; }
    public int getPriority() { return priority; }
    public String getTemplateId() { return templateId; }
    public List<String> getAttachments() { return attachments; }
    public Map<String, String> getMetadata() { return metadata; }
    public int getRetryCount() { return retryCount; }
    public LocalDateTime getScheduledAt() { return scheduledAt; }

    @Override
    public String toString() {
        return "Notification{" +
                "to='" + to + '\'' +
                ", channel='" + channel + '\'' +
                ", subject='" + subject + '\'' +
                ", priority=" + priority +
                ", retryCount=" + retryCount +
                '}';
    }

    // -- Static nested Builder class ---
    public static class Builder {
        private final String to;
        private final String body;
        private final String channel;

        // Optional fields with defaults
        private String subject = "";
        private int priority = 2;
        private String templateId = null;
        private List<String> attachments = List.of();
        private Map<String, String> metadata = Map.of();
        private int retryCount = 3;
        private LocalDateTime scheduledAt = null;

        // Required fields go in Builder constructore - enforeced at compile time
        public Builder (String to, String body, String channel) {
            this.to = to;
            this.body = body;
            this.channel = channel;
        }

        public Builder subject (String subject) {
            this.subject = subject;
            return this;
        }

        public Builder priority(int priority) {
            this.priority = priority;
            return this;
        }

        public Builder templateId(String templateId) {
            this.templateId = templateId;
            return this;
        }

        public Builder attachments(List<String> attachments) {
            this.attachments = attachments;
            return this;
        }

        public Builder metadata(Map<String, String> metadata) {
            this.metadata = metadata;
            return this;
        }

        public Builder retryCount(int retryCount) {
            this.retryCount = retryCount;
            return this;
        }

        public Builder scheduledAt(LocalDateTime scheduledAt) {
            this.scheduledAt = scheduledAt;
            return this;
        }

        // Terminal method - calls private Notification constructor

        public Notification build() {
            return new Notification(this);
        }
    }

}
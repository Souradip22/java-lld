package org.example.projects.socialappfacebook;

import java.time.LocalDateTime;
import java.util.UUID;

public class Notification {
    private String id;
    private User forUser;
    private NotificationType type;
    private String content;
    private LocalDateTime notificationTime;

    public Notification(User forUser, NotificationType type, String content) {
        this.id = "notify-" + UUID.randomUUID().toString().substring(0,5);
        this.forUser = forUser;
        this.type = type;
        this.content = content;
        this.notificationTime = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public User getForUser() {
        return forUser;
    }

    public void setForUser(User forUser) {
        this.forUser = forUser;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(LocalDateTime notificationTime) {
        this.notificationTime = notificationTime;
    }
}

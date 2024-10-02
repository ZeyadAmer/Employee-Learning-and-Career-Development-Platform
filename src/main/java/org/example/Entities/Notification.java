package org.example.Entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notification")
public class Notification {

    @EmbeddedId
    private NotificationId notificationId;

    private boolean isSeen;

    @ManyToOne
    @JoinColumn(name = "notification_data_id", insertable = false, updatable = false)
    private NotificationData notificationData;

    public Notification() {}

    public Notification(NotificationId notificationId, boolean isSeen, NotificationData notificationData1) {
        this.notificationId = notificationId;
        this.isSeen = isSeen;
        this.notificationData = notificationData1;
    }

    public NotificationId getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(NotificationId notificationId) {
        this.notificationId = notificationId;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean isSeen) {
        this.isSeen = isSeen;
    }

    public NotificationData getNotificationData() {
        return notificationData;
    }

    public void setNotificationData(NotificationData notificationData) {
        this.notificationData = notificationData;
    }
}

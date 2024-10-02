package org.example.Mappers;

import org.example.Entities.NotificationData;
import org.example.Entities.NotificationId;

public class NotificationDTO {

    private NotificationId notificationId;
    private boolean isSeen;
    private NotificationDataDTO notificationData;

    public NotificationDTO(NotificationId notificationId, boolean isSeen, NotificationDataDTO notificationData) {
        this.notificationId = notificationId;
        this.isSeen = isSeen;
        this.notificationData = notificationData;
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

    public void setSeen(boolean seen) {
        isSeen = seen;
    }

    public NotificationDataDTO getNotificationData() {
        return notificationData;
    }

    public void setNotificationData(NotificationDataDTO notificationData) {
        this.notificationData = notificationData;
    }
}

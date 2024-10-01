package org.example.Entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class NotificationId implements Serializable {

    @Column(name = "notification_data_id")
    private int notificationDataId;

    @Column(name = "receiver_id")
    private int receiverId; // UserDB ID

    public NotificationId() {}

    public NotificationId(int notificationDataId, int receiverId) {
        this.notificationDataId = notificationDataId;
        this.receiverId = receiverId;
    }

    public int getNotificationDataId() {
        return notificationDataId;
    }

    public void setNotificationDataId(int notificationDataId) {
        this.notificationDataId = notificationDataId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationId that = (NotificationId) o;
        return notificationDataId == that.notificationDataId && receiverId == that.receiverId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationDataId, receiverId);
    }
}

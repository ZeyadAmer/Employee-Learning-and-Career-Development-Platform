package org.example.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "entities_type")
public class EntitiesType {

    @Id
    private String name;

    @OneToMany(mappedBy = "entitiesType", cascade = CascadeType.ALL) // Use 'entitiesType' instead of 'entities_type'
    private List<NotificationData> notificationData;

    public EntitiesType() {}

    public EntitiesType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NotificationData> getNotificationData() {
        return notificationData;
    }

    public void setNotificationData(List<NotificationData> notificationData) {
        this.notificationData = notificationData;
    }
}

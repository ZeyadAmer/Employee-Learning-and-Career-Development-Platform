package org.example.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(
        name = "action"
)
public class Action {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    private String name;

    @OneToMany(mappedBy = "action", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotificationData> notificationData;

    public Action(){}

    public Action(String name, List<NotificationData> notificationData){
        this.name = name;
        this.notificationData = notificationData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

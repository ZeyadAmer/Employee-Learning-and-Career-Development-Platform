package org.example.Entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "notification_data")
public class NotificationData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int actorId;
    private Date date;
    private int entityId;

    @ManyToOne
    @JoinColumn(name = "action_id", nullable = false)
    private Action action;

    @ManyToOne
    @JoinColumn(name = "entity_type_name", referencedColumnName = "name")
    private EntitiesType entitiesType;

    @OneToMany(mappedBy = "notificationData", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications;

    public NotificationData() {}

    public NotificationData(int actorId, Date date, int entityId, Action action, EntitiesType entitiesType) {
        this.action = action;
        this.actorId = actorId;
        this.date = date;
        this.entityId = entityId;
        this.entitiesType = entitiesType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public EntitiesType getEntitiesType() {
        return entitiesType;
    }

    public void setEntitiesType(EntitiesType entitiesType) {
        this.entitiesType = entitiesType;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}

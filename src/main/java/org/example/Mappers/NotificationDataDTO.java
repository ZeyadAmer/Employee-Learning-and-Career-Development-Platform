package org.example.Mappers;

import org.example.Entities.EntitiesType;

import java.util.Date;

public class NotificationDataDTO {
    private int id;
    private int actorId;
    private Date date;
    private ActionDTO action;
    private EntitiesTypeDTO entitiesType;
    private int entityId;

    public NotificationDataDTO(int actorId, Date date, EntitiesTypeDTO entitiesType, int entityId, ActionDTO action) {
        this.actorId = actorId;
        this.date = date;
        this.entityId = entityId;
        this.action = action;
        this.entitiesType = entitiesType;
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

    public ActionDTO getAction() {
        return action;
    }

    public void setAction(ActionDTO actionDTO) {
        this.action = actionDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EntitiesTypeDTO getEntitiesType() {
        return entitiesType;
    }

    public void setEntitiesType(EntitiesTypeDTO entitiesTypeDTO) {
        this.entitiesType = entitiesTypeDTO;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }
}

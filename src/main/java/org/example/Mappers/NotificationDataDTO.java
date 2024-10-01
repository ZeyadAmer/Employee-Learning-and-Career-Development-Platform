package org.example.Mappers;

import org.example.Entities.EntitiesType;

import java.util.Date;

public class NotificationDataDTO {
    private int id;
    private int actorId;
    private Date date;
    private ActionDTO actionDTO;
    private EntitiesTypeDTO entitiesTypeDTO;
    private int entityId;

    public NotificationDataDTO(int actorId, Date date, EntitiesTypeDTO entitiesTypeDTO, int entityId, ActionDTO actionDTO) {
        this.actorId = actorId;
        this.date = date;
        this.entityId = entityId;
        this.actionDTO = actionDTO;
        this.entitiesTypeDTO = entitiesTypeDTO;
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

    public ActionDTO getActionDTO() {
        return actionDTO;
    }

    public void setActionDTO(ActionDTO actionDTO) {
        this.actionDTO = actionDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EntitiesTypeDTO getEntitiesTypeDTO() {
        return entitiesTypeDTO;
    }

    public void setEntitiesTypeDTO(EntitiesTypeDTO entitiesTypeDTO) {
        this.entitiesTypeDTO = entitiesTypeDTO;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }
}

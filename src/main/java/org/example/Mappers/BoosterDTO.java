package org.example.Mappers;

import org.example.Entities.BoosterType;

public class BoosterDTO {

    private int id;
    private String name;
    private boolean isActive;
    private BoosterTypeDTO boosterTypeDTO;

    public BoosterDTO(){}

    public BoosterDTO(String name, boolean isActive, BoosterTypeDTO boosterTypeDTO){
        this.name = name;
        this.isActive = isActive;
        this.boosterTypeDTO = boosterTypeDTO;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public BoosterTypeDTO getBoosterType() {
        return boosterTypeDTO;
    }

    public void setBoosterType(BoosterTypeDTO boosterTypeDTO) {
        this.boosterTypeDTO = boosterTypeDTO;
    }
}

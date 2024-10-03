package org.example.Mappers;

public class BoosterTypeDTO {
    private int id;
    private String name;
    private int value;
    private boolean isActive;
    public BoosterTypeDTO(){}

    public BoosterTypeDTO(String name, int value, boolean isActive){
        this.name = name;
        this.value = value;
        this.isActive = isActive;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

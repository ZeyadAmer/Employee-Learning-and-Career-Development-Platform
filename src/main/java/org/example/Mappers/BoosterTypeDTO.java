package org.example.Mappers;

public class BoosterTypeDTO {
    private int id;
    private String name;

    public BoosterTypeDTO(){}

    public BoosterTypeDTO(String name){
        this.name = name;
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
}

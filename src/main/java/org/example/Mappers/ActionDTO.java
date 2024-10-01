package org.example.Mappers;

public class ActionDTO {
    private int id;
    private String name;

    public ActionDTO(){}

    public ActionDTO(String name){
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

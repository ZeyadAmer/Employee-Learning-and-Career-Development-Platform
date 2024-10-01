package org.example.Entities;

import javax.persistence.*;

@Entity
@Table(
        name = "entities_type"
)
public class EntitiesType {

    @Id
    private String name;

    public EntitiesType(){}

    public EntitiesType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package org.example.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "boosterType")
public class BoosterType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int value;

    private String name;
    @OneToMany(mappedBy = "boosterType", cascade = CascadeType.ALL)
    List<Booster> boosters;
    public BoosterType() {}

    public BoosterType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}

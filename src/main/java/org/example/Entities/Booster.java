package org.example.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "booster")
public class Booster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "booster_type_id", nullable = false)
    private BoosterType boosterType;
    @OneToMany(mappedBy = "booster", cascade = CascadeType.ALL)
    private List<UserLearning> userLearnings;
    private boolean isActive;
    public Booster() {}
    public Booster(String name, BoosterType boosterType, boolean isActive) {
        this.name = name;
        this.boosterType = boosterType;
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
    public BoosterType getBoosterType() {
        return boosterType;
    }
    public void setBoosterType(BoosterType boosterType) {
        this.boosterType = boosterType;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
}


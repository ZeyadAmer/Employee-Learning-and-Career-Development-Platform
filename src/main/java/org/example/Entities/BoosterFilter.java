package org.example.Entities;
import javax.persistence.*;

@Entity
@Table(name = "booster_filters")
public class BoosterFilter {

    @EmbeddedId
    private BoosterFiltersId id;

    @ManyToOne
    @MapsId("boosterId")
    @JoinColumn(name = "booster_id", referencedColumnName = "id")
    private Booster booster;

    @ManyToOne
    @MapsId("learningTypeId")
    @JoinColumn(name = "learning_type_id", referencedColumnName = "id")
    private LearningTypes learningType;

    // Constructors, getters, and setters

    public BoosterFilter() {}

    public BoosterFilter(Booster booster, LearningTypes learningType) {
        this.id = new BoosterFiltersId(booster.getId(), learningType.getId());
        this.booster = booster;
        this.learningType = learningType;
    }

    public BoosterFiltersId getId() {
        return id;
    }

    public void setId(BoosterFiltersId id) {
        this.id = id;
    }

    public Booster getBooster() {
        return booster;
    }

    public void setBooster(Booster booster) {
        this.booster = booster;
    }

    public LearningTypes getLearningType() {
        return learningType;
    }

    public void setLearningType(LearningTypes learningType) {
        this.learningType = learningType;
    }
}

package org.example.Entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@Embeddable
public class BoosterFilterId implements Serializable {

    @Column(name = "booster_id")
    private int boosterId;

    @Column(name = "learning_type_id")
    private int learningTypeId;

    // Constructors, getters, setters, equals, and hashCode methods

    public BoosterFilterId() {}

    public BoosterFilterId(int boosterId, int learningTypeId) {
        this.boosterId = boosterId;
        this.learningTypeId = learningTypeId;
    }

    public int getBoosterId() {
        return boosterId;
    }

    public void setBoosterId(int boosterId) {
        this.boosterId = boosterId;
    }

    public int getLearningTypeId() {
        return learningTypeId;
    }

    public void setLearningTypeId(int learningTypeId) {
        this.learningTypeId = learningTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoosterFilterId that = (BoosterFilterId) o;
        return boosterId == that.boosterId && learningTypeId == that.learningTypeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(boosterId, learningTypeId);
    }
}


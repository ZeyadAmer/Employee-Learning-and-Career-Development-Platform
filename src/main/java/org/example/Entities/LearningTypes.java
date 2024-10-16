package org.example.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "learning_types")
public class LearningTypes {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    @Column(name = "learning_type", nullable = false)
    private String learningType;
    private int baseScore;
    @OneToMany(mappedBy = "learningType", cascade = CascadeType.ALL)
    private List<UserLearning> userLearnings;
    @OneToMany(mappedBy = "learningType", cascade = CascadeType.ALL)
    private List<Learnings> learnings;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLearningType() {
        return learningType;
    }

    public void setLearningType(String learningType) {
        this.learningType = learningType;
    }


    public int getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(int baseScore) {
        this.baseScore = baseScore;
    }
}

package org.example.Entities;

import javax.persistence.*;

@Entity
@Table(name = "learnings")
public class Learnings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String url;
    private String description;
    private float lengthInHours;

    @ManyToOne
    @JoinColumn(name = "learning_type", nullable = false)
    private LearningTypes learningType;

    @ManyToOne
    @JoinColumn(name = "learning_subject", nullable = false)
    private LearningSubjects learningSubject;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getLengthInHours() {
        return lengthInHours;
    }

    public void setLengthInHours(float lengthInHours) {
        this.lengthInHours = lengthInHours;
    }

    public LearningTypes getLearningType() {
        return learningType;
    }

    public void setLearningType(LearningTypes learningType) {
        this.learningType = learningType;
    }

    public LearningSubjects getLearningSubject() {
        return learningSubject;
    }

    public void setLearningSubject(LearningSubjects learningSubject) {
        this.learningSubject = learningSubject;
    }
}

package org.example.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "learning_subjects")
public class LearningSubjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private LearningSubjectsType learningSubjectsType;

    private String subject;

    @OneToMany(mappedBy = "learningSubject", cascade = CascadeType.ALL) // Corrected mappedBy
    private List<Learnings> learnings;

    public enum LearningSubjectsType{
        FUNCTIONAL, ORGANISATIONAL
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Learnings> getLearnings() {
        return learnings;
    }

    public void setLearnings(List<Learnings> learnings) {
        this.learnings = learnings;
    }
}

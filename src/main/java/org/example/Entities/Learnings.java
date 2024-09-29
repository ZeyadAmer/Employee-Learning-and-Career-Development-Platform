package org.example.Entities;

import javax.persistence.*;

@Entity
@Table(name = "learnings")
public class Learnings {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
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
}

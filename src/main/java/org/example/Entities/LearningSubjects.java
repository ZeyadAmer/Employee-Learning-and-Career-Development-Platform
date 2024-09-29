package org.example.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "learning_subjects")
public class LearningSubjects {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    private enum subjectType{Functional,Organisational};
    private String subject;
    @OneToMany(mappedBy = "learning_subject", cascade = CascadeType.ALL)
    private List<Learnings> learnings;
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
}

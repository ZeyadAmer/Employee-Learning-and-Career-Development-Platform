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
    private String learning_type;
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

    public String getLearning_type() {
        return learning_type;
    }

    public void setLearning_type(String learning_type) {
        this.learning_type = learning_type;
    }
}

package org.example.Entities;

import javax.persistence.*;

@Entity
@Table(
        name = "user_scores"
)
public class UserScores {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private int id; // userId from UsersDB
    private int score;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

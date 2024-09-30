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
}

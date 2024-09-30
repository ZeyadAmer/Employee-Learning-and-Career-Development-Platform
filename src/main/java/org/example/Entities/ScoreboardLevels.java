package org.example.Entities;

import javax.persistence.*;

@Entity
@Table(
        name = "scoreboardLevels"
)
public class ScoreboardLevels {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    private String levelName;
    private int minScore;

    public int getId() {
        return id;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getMinScore() {
        return minScore;
    }

    public void setMinScore(int minScore) {
        this.minScore = minScore;
    }
}

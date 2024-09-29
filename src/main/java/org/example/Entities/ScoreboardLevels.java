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
    private String levelname;
    private int minsScore;

    public int getId() {
        return id;
    }

    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname;
    }

    public int getMinsScore() {
        return minsScore;
    }

    public void setMinsScore(int minsScore) {
        this.minsScore = minsScore;
    }
}

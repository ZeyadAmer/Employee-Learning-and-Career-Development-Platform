package org.example.Mappers;

public class ScoreboardLevelsDTO {
    private int id;
    private String levelName;

    private int minScore;

    public ScoreboardLevelsDTO(){}

    public ScoreboardLevelsDTO(String levelName, int minScore){
        this.levelName = levelName;
        this.minScore = minScore;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinScore() {
        return minScore;
    }

    public void setMinScore(int minScore) {
        this.minScore = minScore;
    }
}

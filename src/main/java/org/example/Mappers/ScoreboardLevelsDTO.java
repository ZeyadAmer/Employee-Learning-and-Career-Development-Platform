package org.example.Mappers;

public class ScoreboardLevelsDTO {
    private int id;
    private String levelName;

    public ScoreboardLevelsDTO(){}

    public ScoreboardLevelsDTO(String levelName){
        this.levelName = levelName;
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
}

package org.example.Mappers;

public class BoosterFilterDTO {
    private int boosterId;
    private int learningTypeId;
    private String boosterName;
    private String learningTypeName;

    public BoosterFilterDTO(){}

    public BoosterFilterDTO(int boosterId, int learningTypeId, String boosterName, String learningTypeName){
        this.boosterId = boosterId;
        this.learningTypeId = learningTypeId;
        this.boosterName = boosterName;
        this.learningTypeName = learningTypeName;
    }

    public int getBoosterId() {
        return boosterId;
    }

    public void setBoosterId(int boosterId) {
        this.boosterId = boosterId;
    }

    public int getLearningTypeId() {
        return learningTypeId;
    }

    public void setLearningTypeId(int learningTypeId) {
        this.learningTypeId = learningTypeId;
    }

    public String getBoosterName() {
        return boosterName;
    }

    public void setBoosterName(String boosterName) {
        this.boosterName = boosterName;
    }

    public String getLearningTypeName() {
        return learningTypeName;
    }

    public void setLearningTypeName(String learningTypeName) {
        this.learningTypeName = learningTypeName;
    }
}

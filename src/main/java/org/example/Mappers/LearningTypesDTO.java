package org.example.Mappers;

public class LearningTypesDTO {
    private int id;
    private String learningType;

    public LearningTypesDTO(){}

    public LearningTypesDTO(String learningType){
        this.learningType = learningType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLearningType() {
        return learningType;
    }

    public void setLearningType(String learningType) {
        this.learningType = learningType;
    }
}

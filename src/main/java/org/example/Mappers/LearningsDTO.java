package org.example.Mappers;

public class LearningsDTO {
    private int id;
    private LearningTypesDTO learningTypesDTO;
    private String url;
    private String description;
    private LearningSubjectsDTO learningSubjectsDTO;

    public LearningsDTO(){}

    public LearningsDTO(LearningTypesDTO learningTypesDTO, String url, String description, LearningSubjectsDTO learningSubjectsDTO ){
        this.learningTypesDTO = learningTypesDTO;
        this.url = url;
        this.description = description;
        this.learningSubjectsDTO = learningSubjectsDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LearningTypesDTO getLearningTypesDTO() {
        return learningTypesDTO;
    }

    public void setLearningTypesDTO(LearningTypesDTO learningTypesDTO) {
        this.learningTypesDTO = learningTypesDTO;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LearningSubjectsDTO getLearningSubjectsDTO() {
        return learningSubjectsDTO;
    }

    public void setLearningSubjectsDTO(LearningSubjectsDTO learningSubjectsDTO) {
        this.learningSubjectsDTO = learningSubjectsDTO;
    }
}

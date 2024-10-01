package org.example.Mappers;

public class BoosterFilterRequest {
    private BoosterDTO boosterDTO;
    private LearningTypesDTO learningTypesDTO;

    public BoosterFilterRequest() {}

    public BoosterFilterRequest(BoosterDTO boosterDTO, LearningTypesDTO learningTypesDTO) {
        this.boosterDTO = boosterDTO;
        this.learningTypesDTO = learningTypesDTO;
    }

    public BoosterDTO getBoosterDTO() {
        return boosterDTO;
    }

    public void setBoosterDTO(BoosterDTO boosterDTO) {
        this.boosterDTO = boosterDTO;
    }

    public LearningTypesDTO getLearningTypesDTO() {
        return learningTypesDTO;
    }

    public void setLearningTypesDTO(LearningTypesDTO learningTypesDTO) {
        this.learningTypesDTO = learningTypesDTO;
    }
}


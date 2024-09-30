package org.example.Mappers;

import org.example.Entities.LearningTypes;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LearningTypesMapper {
    LearningTypesDTO learningTypesToLearningTypesDTO(LearningTypes learningTypes);
    LearningTypes learningTypesDTOToLearningTypes(LearningTypesDTO learningTypesDTO);
    List<LearningTypesDTO> learningTypesListToLearningTypesDTOsList(List<LearningTypes> learningTypes);
    List<LearningTypes> learningTypesDTOsListToLearningTypesList(List<LearningTypesDTO> learningTypesDTOS);
}

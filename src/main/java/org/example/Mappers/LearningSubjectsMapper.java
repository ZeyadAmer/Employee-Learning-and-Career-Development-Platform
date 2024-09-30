package org.example.Mappers;

import org.example.Entities.LearningSubjects;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LearningSubjectsMapper {
    LearningSubjectsDTO learningSubjectsToLearningSubjectsDTO(LearningSubjects learningSubjects);
    LearningSubjects learningSubjectsDTOToLearningSubjects(LearningSubjectsDTO learningSubjectsDTO);
    List<LearningSubjectsDTO> learningSubjectsListToLearningSubjectsDTOsList(List<LearningSubjects> learningSubjects);
    List<LearningSubjects> learningSubjectsDTOsListToLearningSubjectsList(List<LearningSubjectsDTO> learningSubjectsDTOS);
}

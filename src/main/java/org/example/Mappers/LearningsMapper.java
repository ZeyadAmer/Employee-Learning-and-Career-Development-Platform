package org.example.Mappers;

import org.example.Entities.Learnings;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LearningsMapper {
    LearningsDTO learningsToLearningsDTO(Learnings learnings);
    Learnings learningsDTOToLearnings(LearningsDTO learningsDTO);
    List<Learnings> learningsDTOsListToLearningsList(List<LearningsDTO> learningsDTOS);
    List<LearningsDTO> learningsListToLearningsDTOsList(List<Learnings> learnings);
}

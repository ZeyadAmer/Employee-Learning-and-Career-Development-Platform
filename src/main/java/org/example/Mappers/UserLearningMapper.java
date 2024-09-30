package org.example.Mappers;

import org.example.Entities.UserLearning;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserLearningMapper {
    UserLearningDTO userLearningToUserLearningDTO(UserLearning userLearning);
    UserLearning userLearningDTOToUserLearning(UserLearningDTO userLearningDTO);
    List<UserLearningDTO> userLearningsListToUserLearningsDTOsList(List<UserLearning> userLearnings);
    List<UserLearning> userLearningDTOsListToUserLearningsList(List<UserLearningDTO> userLearningDTOS);
}

package org.example.Mappers;

import org.example.Entities.UserScores;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserScoresMapper {
    UserScoresDTO userScoresToUserScoresDTO(UserScores userScores);
    UserScores userScoresDTOToUserScores(UserScoresDTO userScoresDTO);
    List<UserScores> userScoresDTOsListToUserScoresList(List<UserScoresDTO> userScoresDTOS);
    List<UserScoresDTO> userScoresListToUserScoresDTOsList(List<UserScores> userScores);
}

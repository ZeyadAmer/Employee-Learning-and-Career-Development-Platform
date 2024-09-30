package org.example.Mappers;

import org.example.Entities.ScoreboardLevels;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScoreboardLevelsMapper {
    ScoreboardLevelsDTO scoreboardLevelsToScoreboardLevelsDTO(ScoreboardLevels scoreboardLevels);
    ScoreboardLevels scoreboardLevelsDTOToScoreboardLevels(ScoreboardLevelsDTO scoreboardLevelsDTO);
    List<ScoreboardLevels> scoreboardLevelsDTOsListToScoreboardLevelsList(List<ScoreboardLevelsDTO> scoreboardLevelsDTOS);
    List<ScoreboardLevelsDTO> scoreboardLevelsListToScoreboardLevelsDTOsList(List<ScoreboardLevels> scoreboardLevels);
}

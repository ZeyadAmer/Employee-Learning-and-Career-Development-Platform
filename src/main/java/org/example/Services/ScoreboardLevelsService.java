package org.example.Services;

import org.example.Entities.ScoreboardLevels;
import org.example.Exceptions.ExistsException;
import org.example.Mappers.ScoreboardLevelsDTO;
import org.example.Mappers.ScoreboardLevelsMapper;
import org.example.Repositories.ScoreboardLevelsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreboardLevelsService {

    private ScoreboardLevelsMapper scoreboardLevelsMapper;
    private ScoreboardLevelsRepository scoreboardLevelsRepository;

    public ScoreboardLevelsService(ScoreboardLevelsMapper scoreboardLevelsMapper,ScoreboardLevelsRepository scoreboardLevelsRepository){
        this.scoreboardLevelsMapper = scoreboardLevelsMapper;
        this.scoreboardLevelsRepository = scoreboardLevelsRepository;
    }

    public void createScoreBoardLevel(ScoreboardLevelsDTO scoreboardLevelsDTO){
        if(scoreboardLevelsRepository.findByLevelName(scoreboardLevelsDTO.getLevelName()).isPresent()){
            throw new ExistsException("Scoreboard Level already exists");
        }
        System.out.println(scoreboardLevelsDTO.getLevelName());
        scoreboardLevelsRepository.save(scoreboardLevelsMapper.scoreboardLevelsDTOToScoreboardLevels(scoreboardLevelsDTO));
    }

    public void deleteScoreboardLevel(String levelName){
        Optional<ScoreboardLevels> scoreboardLevel = scoreboardLevelsRepository.findByLevelName(levelName);
        if(scoreboardLevel.isEmpty()){
            throw new ExistsException("Scoreboard Level does not exist");
        }
        scoreboardLevelsRepository.delete(scoreboardLevel.get());
    }

    public void updateScoreboardLevelName(String oldName, String newName){
        Optional<ScoreboardLevels> scoreboardLevel = scoreboardLevelsRepository.findByLevelName(oldName);
        if(scoreboardLevel.isEmpty()){
            throw new ExistsException("Scoreboard Level does not exist");
        }
        ScoreboardLevels updatedScoreboardLevel = scoreboardLevel.get();
        updatedScoreboardLevel.setLevelName(newName);
        scoreboardLevelsRepository.save(updatedScoreboardLevel);
    }

    public void updateScoreboardLevelScore(String levelName, int minScore){
        Optional<ScoreboardLevels> scoreboardLevel = scoreboardLevelsRepository.findByLevelName(levelName);
        if(scoreboardLevel.isEmpty()){
            throw new ExistsException("Scoreboard Level does not exist");
        }
        ScoreboardLevels updatedScoreboardLevel = scoreboardLevel.get();
        updatedScoreboardLevel.setMinScore(minScore);
        scoreboardLevelsRepository.save(updatedScoreboardLevel);
    }

    public ScoreboardLevelsDTO getScoreboardLevel(String levelName){
        Optional<ScoreboardLevels> scoreboardLevel = scoreboardLevelsRepository.findByLevelName(levelName);
        if(scoreboardLevel.isEmpty()){
            throw new ExistsException("Scoreboard Level does not exist");
        }
        return scoreboardLevelsMapper.scoreboardLevelsToScoreboardLevelsDTO(scoreboardLevel.get());
    }

    public List<ScoreboardLevelsDTO> getAllScoreboardLevels(){
        List<ScoreboardLevels> scoreboardLevels = scoreboardLevelsRepository.findAll();
        return scoreboardLevelsMapper.scoreboardLevelsListToScoreboardLevelsDTOsList(scoreboardLevels);
    }


    public String getScoreboardLevelByScore(int score) {
        String scoreBoardLevel = scoreboardLevelsRepository.findLevelNameByMinScore(score);
        System.out.println(scoreBoardLevel);
        return scoreBoardLevel;

    }
}

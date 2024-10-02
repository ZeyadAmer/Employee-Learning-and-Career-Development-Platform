package org.example.Services;

import org.example.Entities.UserScores;
import org.example.Exceptions.ExistsException;
import org.example.Mappers.UserScoresDTO;
import org.example.Mappers.UserScoresMapper;
import org.example.Repositories.UserScoresRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserScoresService {

    private UserScoresMapper userScoresMapper;
    private UserScoresRepository userScoresRepository;

    public UserScoresService(UserScoresMapper userScoresMapper, UserScoresRepository userScoresRepository){
        this.userScoresMapper = userScoresMapper;
        this.userScoresRepository = userScoresRepository;
    }

    public void createUserScore(UserScoresDTO userScoresDTO){
        if(userScoresRepository.findById(userScoresDTO.getId()).isPresent()){
            throw new ExistsException("User Score ID already exists");
        }
        userScoresRepository.save(userScoresMapper.userScoresDTOToUserScores(userScoresDTO));
    }

    public void deleteUserScore(int id){
        Optional<UserScores> userScore = userScoresRepository.findById(id);
        if(userScore.isEmpty()){
            throw new ExistsException("User Score ID does not exist.");
        }
        userScoresRepository.delete(userScore.get());
    }

    public void updateUserScore(int id, int newScore){
        Optional<UserScores> userScore = userScoresRepository.findById(id);
        if(userScore.isEmpty()){
            throw new ExistsException("User Score ID does not exist.");
        }
        UserScores updatedUserScore = userScore.get();
        updatedUserScore.setScore(newScore);
        userScoresRepository.save(updatedUserScore);
    }

    public UserScoresDTO getUserScore(int id){
        Optional<UserScores> userScore = userScoresRepository.findById(id);
        if(userScore.isEmpty()){
            throw new ExistsException("User Score ID does not exist.");
        }
        return userScoresMapper.userScoresToUserScoresDTO(userScore.get());
    }

    public List<UserScoresDTO> getAllUserScores(){
        List<UserScores> userScores = userScoresRepository.findAllByOrderByScoreDesc();
        return userScoresMapper.userScoresListToUserScoresDTOsList(userScores);
    }
}

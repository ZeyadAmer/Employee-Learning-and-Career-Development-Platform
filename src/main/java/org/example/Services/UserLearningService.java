package org.example.Services;

import org.example.Entities.Learnings;
import org.example.Entities.UserLearning;
import org.example.Exceptions.ExistsException;
import org.example.Mappers.LearningsDTO;
import org.example.Mappers.UserLearningDTO;
import org.example.Mappers.UserLearningMapper;
import org.example.Repositories.UserLearningRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserLearningService {

    private UserLearningRepository userLearningRepository;
    private UserLearningMapper userLearningMapper;

    public UserLearningService(UserLearningRepository userLearningRepository, UserLearningMapper userLearningMapper){
        this.userLearningMapper = userLearningMapper;
        this.userLearningRepository = userLearningRepository;
    }

    public void createUserLearning(UserLearningDTO userLearningDTO){
        if(userLearningRepository.findById(userLearningDTO.getId()).isPresent()){
            throw new ExistsException("User Learning already exist");
        }
        userLearningRepository.save(userLearningMapper.userLearningDTOToUserLearning(userLearningDTO));
    }

    public void deleteUserLearning(int id){
        Optional<UserLearning> userLearning = userLearningRepository.findById(id);
        if(userLearning.isEmpty()){
            throw new ExistsException("User Learning does not exist");
        }
        userLearningRepository.delete(userLearning.get());
    }

    public void updateUserLearning(int id, UserLearningDTO userLearningDTO){
        Optional<UserLearning> userLearning = userLearningRepository.findById(id);
        if(userLearning.isEmpty()){
            throw new ExistsException("Learning does not exist");
        }
        UserLearning updatedLearning = userLearningMapper.userLearningDTOToUserLearning(userLearningDTO);
        updatedLearning.setId(id);
        userLearningRepository.save(updatedLearning);
    }

    public UserLearningDTO getUserLearning(int id){
        Optional<UserLearning> userLearning = userLearningRepository.findById(id);
        if(userLearning.isEmpty()){
            throw new ExistsException("User Learning does not exist");
        }
        return userLearningMapper.userLearningToUserLearningDTO(userLearning.get());
    }

        public List<UserLearningDTO> getAllUserLearnings(){
        List<UserLearning> learnings = userLearningRepository.findAll();
        return userLearningMapper.userLearningsListToUserLearningsDTOsList(learnings);
    }
}

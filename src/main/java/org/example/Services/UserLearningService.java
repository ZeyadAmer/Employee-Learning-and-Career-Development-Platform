package org.example.Services;

import org.example.Entities.LearningTypes;
import org.example.Entities.Learnings;
import org.example.Entities.ProofTypes;
import org.example.Entities.UserLearning;
import org.example.Exceptions.ExistsException;
import org.example.Mappers.*;
import org.example.Repositories.LearningTypesRepository;
import org.example.Repositories.ProofTypesRepository;
import org.example.Repositories.UserLearningRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserLearningService {

    private final UserLearningRepository userLearningRepository;
    private final UserLearningMapper userLearningMapper;
    private final LearningTypesRepository learningTypesRepository;
    private final ProofTypesRepository proofTypesRepository;

    public UserLearningService(UserLearningRepository userLearningRepository, UserLearningMapper userLearningMapper,
                               LearningTypesRepository learningTypesRepository, ProofTypesRepository proofTypesRepository){
        this.userLearningMapper = userLearningMapper;
        this.userLearningRepository = userLearningRepository;
        this.learningTypesRepository = learningTypesRepository;
        this.proofTypesRepository = proofTypesRepository;
    }

    public void createUserLearning(UserLearningDTO userLearningDTO) {
        // Check if the user learning already exists
        if (userLearningRepository.findById(userLearningDTO.getId()).isPresent()) {
            throw new ExistsException("User Learning already exists");
        }
        UserLearning userLearning = userLearningMapper.userLearningDTOToUserLearning(userLearningDTO);
        LearningTypes learningType = learningTypesRepository.findById(userLearningDTO.getLearningTypesDTO().getId()).get();
        ProofTypes proofType = proofTypesRepository.findById(userLearningDTO.getProofTypesDTO().getId()).get();
        userLearning.setLearningType(learningType);
        userLearning.setProofType(proofType);
        userLearningRepository.save(userLearning);
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

    public List<UserLearningDTO> getAllUserLearnings(int id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserLearning> learnings = userLearningRepository.findAllByUserId(id,pageable);
        return userLearningMapper.userLearningsListToUserLearningsDTOsList(learnings.getContent());
    }

    public void approveUserLearning(int id) {
        Optional<UserLearning> learning = userLearningRepository.findById(id);
        learning.get().setApprovalStatus(UserLearning.ApprovalStatus.APPROVED);
        userLearningRepository.save(learning.get());
    }
    public void rejectUserLearning(int id) {
        Optional<UserLearning> learning = userLearningRepository.findById(id);
        learning.get().setApprovalStatus(UserLearning.ApprovalStatus.REJECTED);
        userLearningRepository.save(learning.get());
    }
    public List<UserLearningDTO> getAllPending(int id){
        List<UserLearning> learnings = userLearningRepository.findAllPending(id);
        return userLearningMapper.userLearningsListToUserLearningsDTOsList(learnings);
    }
}

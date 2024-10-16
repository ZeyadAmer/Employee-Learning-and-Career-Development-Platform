package org.example.Services;

import org.example.Entities.LearningTypes;
import org.example.Exceptions.ExistsException;
import org.example.Mappers.LearningTypesDTO;
import org.example.Mappers.LearningTypesMapper;
import org.example.Repositories.LearningTypesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearningTypesService {

    private LearningTypesRepository learningTypesRepository;
    private LearningTypesMapper learningTypesMapper;

    public LearningTypesService(LearningTypesRepository learningTypesRepository, LearningTypesMapper learningTypesMapper){
        this.learningTypesMapper = learningTypesMapper;
        this.learningTypesRepository = learningTypesRepository;
    }

    public void createLearningType(LearningTypesDTO learningTypesDTO){
        if(learningTypesRepository.findByLearningType(learningTypesDTO.getLearningType()).isPresent()){
            throw new ExistsException("Learning Type already exists");
        }
        learningTypesRepository.save(learningTypesMapper.learningTypesDTOToLearningTypes(learningTypesDTO));
    }

    public void deleteLearningType(String learningTypeName){
        Optional<LearningTypes> learningType = learningTypesRepository.findByLearningType(learningTypeName);
        if(learningType.isEmpty()){
            throw new ExistsException("Learning Type does not exist");
        }
        learningTypesRepository.delete(learningType.get());
    }

    public void updateLearningTypeName(String oldName, String newName){
        Optional<LearningTypes> learningType = learningTypesRepository.findByLearningType(oldName);
        if(learningType.isEmpty()){
            throw new ExistsException("Learning Type does not exist");
        }
        LearningTypes updatedLearningType = learningType.get();
        updatedLearningType.setLearningType(newName);
        learningTypesRepository.save(updatedLearningType);
    }

    public void updateLearningTypeScore(String learningTypeName, int newBaseScore){
        Optional<LearningTypes> learningType = learningTypesRepository.findByLearningType(learningTypeName);
        if(learningType.isEmpty()){
            throw new ExistsException("Learning Type does not exist");
        }
        LearningTypes updatedLearningType = learningType.get();
        updatedLearningType.setBaseScore(newBaseScore);
        learningTypesRepository.save(updatedLearningType);
    }

    public LearningTypesDTO getLearningType(String learninTypeName){
        Optional<LearningTypes> learningType = learningTypesRepository.findByLearningType(learninTypeName);
        if(learningType.isEmpty()){
            throw new ExistsException("Learning Type does not exist");
        }
        return learningTypesMapper.learningTypesToLearningTypesDTO(learningType.get());
    }

    public List<LearningTypesDTO> getAllLearningTypes(){
        List<LearningTypes> learningTypes = learningTypesRepository.findAll();
        return learningTypesMapper.learningTypesListToLearningTypesDTOsList(learningTypes);
    }
}

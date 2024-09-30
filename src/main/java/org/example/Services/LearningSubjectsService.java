package org.example.Services;

import org.example.Entities.LearningSubjects;
import org.example.Exceptions.ExistsException;
import org.example.Mappers.LearningSubjectsDTO;
import org.example.Mappers.LearningSubjectsMapper;
import org.example.Repositories.LearningSubjectsRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class LearningSubjectsService {

    private final LearningSubjectsMapper learningSubjectsMapper;
    private final LearningSubjectsRepository learningSubjectsRepository;

    public LearningSubjectsService(LearningSubjectsMapper learningSubjectsMapper, LearningSubjectsRepository learningSubjectsRepository){
        this.learningSubjectsMapper = learningSubjectsMapper;
        this.learningSubjectsRepository = learningSubjectsRepository;
    }

    public void createLearningSubject(LearningSubjectsDTO learningSubjectsDTO){
        if(learningSubjectsRepository.findBySubject(learningSubjectsDTO.getSubject()).isPresent()){
            throw new ExistsException("Learning Subject already exists");
        }
        learningSubjectsRepository.save(learningSubjectsMapper.learningSubjectsDTOToLearningSubjects(learningSubjectsDTO));
    }

    public void deleteLearningSubject(String subject){
        Optional<LearningSubjects> learningSubject = learningSubjectsRepository.findBySubject(subject);
        if(learningSubject.isEmpty()){
            throw new ExistsException("Learning Subject doesnt exist");
        }
        learningSubjectsRepository.delete(learningSubject.get());
    }

    public void updateLearningSubjectName(String subject){
        Optional<LearningSubjects> learningSubject = learningSubjectsRepository.findBySubject(subject);
        if(learningSubject.isEmpty()){
            throw new ExistsException("Learning Subject does not exist");
        }
        LearningSubjects updatedLearningSubject = learningSubject.get();
        updatedLearningSubject.setSubject(subject);
        learningSubjectsRepository.save(updatedLearningSubject);
    }

    public void updateLearningSubjectType(String subject, LearningSubjects.LearningSubjectsType learningSubjectsType){
        Optional<LearningSubjects> learningSubject = learningSubjectsRepository.findBySubject(subject);
        if(learningSubject.isEmpty()){
            throw new ExistsException("Learning Subject does not exist");
        }
        boolean isValidType = Arrays.stream(LearningSubjects.LearningSubjectsType.values())
                .anyMatch(type -> type.equals(learningSubjectsType));
        if(!isValidType){
            throw new ExistsException("Learning Subject Type does not exist");
        }
        LearningSubjects updatedLearningSubject = learningSubject.get();
        updatedLearningSubject.setLearningSubjectsType(learningSubjectsType);
    }
    public LearningSubjectsDTO getLearningSubject(String subject){
        Optional<LearningSubjects> learningSubject = learningSubjectsRepository.findBySubject(subject);
        if(learningSubject.isEmpty()){
            throw new ExistsException("Learning Subject does not exist");
        }
        return learningSubjectsMapper.learningSubjectsToLearningSubjectsDTO(learningSubject.get());
    }

    public List<LearningSubjectsDTO> getAllLearningSubjects(){
        List<LearningSubjects> learningSubjects = learningSubjectsRepository.findAll();
        return learningSubjectsMapper.learningSubjectsListToLearningSubjectsDTOsList(learningSubjects);
    }
}

package org.example.Services;


import org.example.Entities.Learnings;
import org.example.Exceptions.ExistsException;
import org.example.Mappers.LearningsDTO;
import org.example.Mappers.LearningsMapper;
import org.example.Repositories.LearningsRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class LearningsService {

    private final LearningsRepository learningsRepository;
    private final LearningsMapper learningsMapper;

    public  LearningsService(LearningsRepository learningsRepository, LearningsMapper learningsMapper){
        this.learningsMapper = learningsMapper;
        this.learningsRepository = learningsRepository;
    }

    public void createLearning(LearningsDTO learningsDTO){
        if(learningsRepository.findById(learningsDTO.getId()).isPresent()){
            throw new ExistsException("Learnings already exist");
        }

        learningsRepository.save(learningsMapper.learningsDTOToLearnings(learningsDTO));
    }

    public void deleteLearning(int id){
        Optional<Learnings> learning = learningsRepository.findById(id);
        if(learning.isEmpty()){
            throw new ExistsException("Learning does not exist");
        }
        learningsRepository.delete(learning.get());
    }

    public void updateLearning(int id, LearningsDTO learningsDTO){
        Optional<Learnings> learning = learningsRepository.findById(id);
        if(learning.isEmpty()){
            throw new ExistsException("Learning does not exist");
        }
        Learnings updatedLearning = learningsMapper.learningsDTOToLearnings(learningsDTO);
        updatedLearning.setId(id);
        learningsRepository.save(updatedLearning);
    }

    public LearningsDTO getLearning(int id){
        Optional<Learnings> learning = learningsRepository.findById(id);
        if(learning.isEmpty()){
            throw new ExistsException("Learning does not exist");
        }
        return learningsMapper.learningsToLearningsDTO(learning.get());
    }

    public List<LearningsDTO> getAllLearnings(){
        List<Learnings> learnings = learningsRepository.findAll();
        return learningsMapper.learningsListToLearningsDTOsList(learnings);
    }
}

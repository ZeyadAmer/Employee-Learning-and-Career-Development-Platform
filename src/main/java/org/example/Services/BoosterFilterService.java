package org.example.Services;

import org.example.Entities.Booster;
import org.example.Entities.BoosterFilter;
import org.example.Entities.BoosterFilterId;
import org.example.Entities.LearningTypes;
import org.example.Exceptions.ExistsException;
import org.example.Mappers.BoosterDTO;
import org.example.Mappers.BoosterFilterDTO;
import org.example.Mappers.BoosterFilterMapper;
import org.example.Mappers.LearningTypesDTO;
import org.example.Repositories.BoosterFilterRepository;
import org.example.Repositories.BoosterRepository;
import org.example.Repositories.LearningTypesRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class BoosterFilterService {

    private BoosterFilterMapper boosterFilterMapper;
    private BoosterFilterRepository boosterFilterRepository;
    private BoosterRepository boosterRepository;
    private LearningTypesRepository learningTypesRepository;

    public BoosterFilterService(BoosterFilterMapper boosterFilterMapper, BoosterFilterRepository boosterFilterRepository){
        this.boosterFilterMapper = boosterFilterMapper;
        this.boosterFilterRepository = boosterFilterRepository;
    }

    public void createBoosterFilter(BoosterDTO boosterDTO, LearningTypesDTO learningTypesDTO) {
        if (boosterDTO == null || learningTypesDTO == null) {
            throw new EntityNotFoundException("Booster or LearningType not found.");
        }

        BoosterFilterId boosterFilterId = new BoosterFilterId(boosterDTO.getId(), learningTypesDTO.getId());

        if (boosterFilterRepository.existsById(boosterFilterId)) {
            throw new ExistsException("BoosterFilter ID already exists.");
        }

        Optional<Booster> booster = boosterRepository.findByName(boosterDTO.getName());
        Optional<LearningTypes> learningType = learningTypesRepository.findByLearningType(learningTypesDTO.getLearningType());
        BoosterFilter boosterFilter = new BoosterFilter(booster.get(), learningType.get());

        boosterFilterRepository.save(boosterFilter);
    }

        public void deleteBoosterFilter(BoosterFilterId id){
        Optional<BoosterFilter> boosterFilter = boosterFilterRepository.findById(id);
        if(boosterFilter.isEmpty()){
            throw new ExistsException("Booster Filter does not exist");
        }
        boosterFilterRepository.delete(boosterFilter.get());
    }

    public BoosterFilterDTO getBoosterFilter(BoosterFilterId boosterFilterId){
        Optional<BoosterFilter> boosterFilter = boosterFilterRepository.findById(boosterFilterId);
        return boosterFilterMapper.boosterFilterToBoosterFilterDTO(boosterFilter.get());
    }

    public List<BoosterFilterDTO> getAllBoosterFilters(){
        List<BoosterFilter> boosterFilters = boosterFilterRepository.findAll();
        return boosterFilterMapper.boosterFiltersListToBoosterFilterDTOsList(boosterFilters);
    }
}

package org.example.Services;

import org.example.Entities.Booster;
import org.example.Entities.BoosterType;
import org.example.Exceptions.ExistsException;
import org.example.Mappers.BoosterTypeDTO;
import org.example.Mappers.BoosterTypeMapper;
import org.example.Repositories.BoosterTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoosterTypeService {

    private final BoosterTypeRepository boosterTypeRepository;
    private final BoosterTypeMapper boosterTypeMapper;

    public BoosterTypeService(BoosterTypeRepository boosterTypeRepository, BoosterTypeMapper boosterTypeMapper){
        this.boosterTypeMapper = boosterTypeMapper;
        this.boosterTypeRepository = boosterTypeRepository;
    }

    public void createBoosterType(BoosterTypeDTO boosterTypeDTO){
        if(boosterTypeRepository.findByName(boosterTypeDTO.getName()).isPresent()){
            throw new ExistsException("Booster Type already exists");
        }
        boosterTypeRepository.save(boosterTypeMapper.boosterTypeDTOToBoosterType(boosterTypeDTO));
    }

    public void deleteBoosterType(String name){
        Optional<BoosterType> boosterType = boosterTypeRepository.findByName(name);
        if(boosterType.isEmpty()){
            throw new ExistsException("Booster Type does not exist");
        }

        boosterTypeRepository.delete(boosterType.get());
    }

    public void updateBoosterType(String name, int newValue){
        Optional<BoosterType> boosterType = boosterTypeRepository.findByName(name);
        if(boosterType.isEmpty()){
            throw new ExistsException("Booster Type does not exist");
        }

        BoosterType updatedBoosterType = boosterType.get();
        updatedBoosterType.setValue(newValue);
        boosterTypeRepository.save(updatedBoosterType);
    }

    public BoosterTypeDTO getBoosterType(String name){
        Optional<BoosterType> boosterType = boosterTypeRepository.findByName(name);
        if(boosterType.isEmpty()){
            throw new ExistsException("Booster Type does not exist");
        }
        return boosterTypeMapper.boosterTypeToBoosterTypeDTO(boosterType.get());
    }

    public List<BoosterTypeDTO> getAllBoosterTypes(){
        List<BoosterType> boosterTypes = boosterTypeRepository.findAll();
        return boosterTypeMapper.boosterTypesListToBoosterTypeDTOsList(boosterTypes);
    }
}

package org.example.Services;

import org.example.Entities.Booster;
import org.example.Entities.BoosterType;
import org.example.Entities.UserLearning;
import org.example.Exceptions.ExistsException;
import org.example.Mappers.BoosterDTO;
import org.example.Mappers.BoosterMapper;
import org.example.Mappers.BoosterTypeDTO;
import org.example.Repositories.BoosterRepository;
import org.example.Repositories.BoosterTypeRepository;
import org.example.Repositories.UserLearningRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoosterService {

    private final BoosterRepository boosterRepository;
    private final BoosterMapper boosterMapper;
    private final BoosterTypeRepository boosterTypeRepository;

    public BoosterService(BoosterRepository boosterRepository, BoosterMapper boosterMapper,
                          BoosterTypeRepository boosterTypeRepository){
        this.boosterRepository = boosterRepository;
        this.boosterMapper = boosterMapper;
        this.boosterTypeRepository = boosterTypeRepository;
    }

    public void createBooster(BoosterDTO boosterDTO){
        if(boosterRepository.findByName(boosterDTO.getName()).isPresent()){
            throw new ExistsException("Booster Name already exists.");
        }
        if(boosterTypeRepository.findByName(boosterDTO.getBoosterType() .getName()).isEmpty()){
            throw new ExistsException("Booster Type does not exist.");
        }
        BoosterType boosterType = boosterTypeRepository.findByName(boosterDTO.getBoosterType().getName()).get();
        Booster booster = boosterMapper.boosterDTOTObooster(boosterDTO);
        booster.setBoosterType(boosterType);
        boosterRepository.save(booster);
    }

    public void deleteBooster(String name){
        Optional<Booster> booster = boosterRepository.findByName(name);
        if(booster.isEmpty()){
            throw new ExistsException("Boost Name does not exist");
        }
        boosterRepository.delete(booster.get());
    }

    public void updateBoosterName(String oldName, String newName){
        Optional<Booster> booster = boosterRepository.findByName(oldName);
        if(booster.isEmpty()){
            throw new ExistsException("Booster Name does not exist");
        }

        Booster updatedBooster = booster.get();
        updatedBooster.setName(newName);
        boosterRepository.save(updatedBooster);
    }

    public void updateBoosterType(String boosterName, String boosterTypeName){
        Optional<Booster> booster = boosterRepository.findByName(boosterName);
        if(booster.isEmpty()){
            throw new ExistsException("Booster does not exist");
        }
        if(boosterTypeRepository.findByName(boosterTypeName).isEmpty()){
            throw new ExistsException("Booster Type does not exist");
        }

        Booster updatedBooster = booster.get();
        BoosterType boosterType = boosterTypeRepository.findByName(boosterTypeName).get();
        updatedBooster.setBoosterType(boosterType);
        boosterRepository.save(updatedBooster);
    }

    public BoosterDTO getBooster(String name){
        Optional<Booster> booster = boosterRepository.findByName(name);
        if(booster.isEmpty()){
            throw new ExistsException("Booster does not exist");
        }
        return boosterMapper.boosterToBoosterDTO(booster.get());
    }

    public List<BoosterDTO> getAllBoosters(){
        List<Booster> boosters = boosterRepository.findAll();
        return boosterMapper.boostersListToBoosterDTOsList(boosters);
    }
}

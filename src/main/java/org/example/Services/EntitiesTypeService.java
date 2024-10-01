package org.example.Services;

import org.example.Entities.EntitiesType;
import org.example.Exceptions.ExistsException;
import org.example.Mappers.EntitiesTypeDTO;
import org.example.Mappers.EntitiesTypeMapper;
import org.example.Repository.EntitiesTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntitiesTypeService {

    private EntitiesTypeRepository entitiesTypeRepository;
    private EntitiesTypeMapper entitiesTypeMapper;

    public EntitiesTypeService(EntitiesTypeRepository entitiesTypeRepository, EntitiesTypeMapper entitiesTypeMapper) {
        this.entitiesTypeRepository = entitiesTypeRepository;
        this.entitiesTypeMapper = entitiesTypeMapper;
    }

    public void createEntitiesType(EntitiesTypeDTO entitiesTypeDTO){
        if(entitiesTypeRepository.findById(entitiesTypeDTO.getName()).isPresent()){
            throw new ExistsException("Entites Type already exists");
        }
        entitiesTypeRepository.save(entitiesTypeMapper.entitiesTypeDTOtoEntitiesType(entitiesTypeDTO));
    }

    public void deleteEntitiesType(String name){
        Optional<EntitiesType> entitiesType = entitiesTypeRepository.findById(name);
        if(entitiesType.isEmpty()){
            throw new ExistsException("Entities Type does not exist");
        }
        entitiesTypeRepository.delete(entitiesType.get());
    }

    public void updateEntitiesType(String oldName, String newName){
        Optional<EntitiesType> entitiesType = entitiesTypeRepository.findById(oldName);
        if(entitiesType.isEmpty()){
            throw new ExistsException("Entities Type does not exist");
        }
        EntitiesType updatedEntitiesType = entitiesType.get();
        updatedEntitiesType.setName(newName);
        entitiesTypeRepository.save(updatedEntitiesType);
    }

    public List<EntitiesTypeDTO> getAllEntitiesTypes(){
        List<EntitiesType> entitiesTypes = entitiesTypeRepository.findAll();
        return entitiesTypeMapper.entitiesTypesToEntitiesTypeDTOs(entitiesTypes);
    }
}

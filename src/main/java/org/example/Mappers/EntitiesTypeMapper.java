package org.example.Mappers;

import org.example.Entities.EntitiesType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntitiesTypeMapper {
    EntitiesTypeDTO entitiesTypeToEntitiesTypeDTO(EntitiesType entitiesType);
    EntitiesType entitiesTypeDTOtoEntitiesType(EntitiesTypeDTO entitiesTypeDTO);
    List<EntitiesTypeDTO> entitiesTypesToEntitiesTypeDTOs(List<EntitiesType> entitiesTypes);
    List<EntitiesType> entitiesTypeDTOsToEntitiesTypes(List<EntitiesTypeDTO> entitiesTypeDTOS);
}

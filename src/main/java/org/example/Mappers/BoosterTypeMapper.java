package org.example.Mappers;


import org.example.Entities.BoosterType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoosterTypeMapper {
    BoosterTypeDTO boosterTypeToBoosterTypeDTO(BoosterType boosterType);
    BoosterType boosterTypeDTOToBoosterType(BoosterDTO boosterDTO);
    List<BoosterDTO> boosterTypesListToBoosterTypeDTOsList(List<BoosterType> boosterTypes);
    List<BoosterType> boosterTypeDTOsListToBoosterTypesList(List<BoosterTypeDTO> boosterTypeDTOS);
}

package org.example.Mappers;

import org.example.Entities.Booster;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoosterMapper {
    BoosterDTO boosterToBoosterDTO(Booster booster);
    Booster boosterDTOTObooster(BoosterDTO boosterDTO);
    List<BoosterDTO> boostersListToBoosterDTOsList(List<Booster> boosters);
    List<Booster> boosterDTOsListToBoostersList(List<BoosterDTO> boosterDTOS);
}

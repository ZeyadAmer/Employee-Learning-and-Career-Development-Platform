package org.example.Mappers;

import org.example.Entities.BoosterFilter;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoosterFilterMapper {
    BoosterFilterDTO boosterFilterToBoosterFilterDTO(BoosterFilter boosterFilter);
    BoosterFilter boosterFilterDTOToBoosterFilter(BoosterFilterDTO boosterFilterDTO);
    List<BoosterFilterDTO> boosterFiltersListToBoosterFilterDTOsList(List<BoosterFilter> boosterFilters);
    List<BoosterFilter> boosterFilterDTOsListToBoosterFiltersList(List<BoosterFilterDTO> boosterFilterDTOS);
}

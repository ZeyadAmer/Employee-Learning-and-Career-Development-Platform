package org.example.Mappers;

import org.example.Entities.Action;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActionMapper {
    ActionDTO actionToActionDTO(Action action);
    Action actionDTOToAction(ActionDTO actionDTO);
    List<ActionDTO> actionsToActionDTOs(List<Action> actions);
    List<Action> actionDTOsToActions(List<ActionDTO> actionDTOS);
}

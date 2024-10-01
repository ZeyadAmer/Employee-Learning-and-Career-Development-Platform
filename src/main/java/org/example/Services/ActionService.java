package org.example.Services;

import org.example.Entities.Action;
import org.example.Exceptions.ExistsException;
import org.example.Mappers.ActionDTO;
import org.example.Mappers.ActionMapper;
import org.example.Repository.ActionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActionService {

    private ActionMapper actionMapper;
    private ActionRepository actionRepository;

    public ActionService(ActionRepository actionRepository, ActionMapper actionMapper) {
        this.actionRepository = actionRepository;
        this.actionMapper = actionMapper;
    }

    public void createAction(ActionDTO actionDTO){
        if(actionRepository.findByName(actionDTO.getName()).isPresent()){
            throw new ExistsException("Action already exists");
        }
        actionRepository.save(actionMapper.actionDTOToAction(actionDTO));
    }

    public void deleteAction(int id){
        Optional<Action> action = actionRepository.findById(id);
        if(action.isEmpty()){
            throw new ExistsException("Action does not exist");
        }
        actionRepository.delete(action.get());
    }

    public void updateAction(int id, String newName){
        Optional<Action> action = actionRepository.findById(id);
        if(action.isEmpty()){
            throw new ExistsException("Action does not exist");
        }
        Action updatedAction = action.get();
        updatedAction.setName(newName);
        actionRepository.save(updatedAction);
    }

    public List<ActionDTO> getAllActions(){
        List<Action> actions = actionRepository.findAll();
        return actionMapper.actionsToActionDTOs(actions);
    }
}

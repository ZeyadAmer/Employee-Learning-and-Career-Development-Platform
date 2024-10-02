package org.example.Services;

import org.example.Entities.Action;
import org.example.Entities.EntitiesType;
import org.example.Entities.NotificationData;
import org.example.Exceptions.ExistsException;
import org.example.Mappers.EntitiesTypeDTO;
import org.example.Mappers.NotificationDataDTO;
import org.example.Mappers.NotificationDataMapper;
import org.example.Repository.ActionRepository;
import org.example.Repository.EntitiesTypeRepository;
import org.example.Repository.NotificationDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationDataService {
    private NotificationDataRepository notificationDataRepository;
    private NotificationDataMapper notificationDataMapper;
    private ActionRepository actionRepository;
    private EntitiesTypeRepository entitiesTypeRepository;

    @Autowired
    public NotificationDataService(NotificationDataRepository notificationDataRepository, NotificationDataMapper notificationDataMapper, ActionRepository actionRepository, EntitiesTypeRepository entitiesTypeRepository) {
        this.notificationDataRepository = notificationDataRepository;
        this.notificationDataMapper = notificationDataMapper;
        this.actionRepository = actionRepository;
        this.entitiesTypeRepository = entitiesTypeRepository;
    }

    public void createNotificationData(NotificationDataDTO notificationDataDTO) {
        if (notificationDataRepository.findById(notificationDataDTO.getId()).isPresent()) {
            throw new ExistsException("Notification Data already exists.");
        }
        if(actionRepository.findByName(notificationDataDTO.getActionDTO().getName()).isEmpty()){
            throw new ExistsException("Action does not exist.");
        }
        if(entitiesTypeRepository.findById(notificationDataDTO.getEntitiesTypeDTO().getName()).isEmpty()){
            throw new ExistsException("Entities Type does not exist.");
        }

        Action action = actionRepository.findByName(notificationDataDTO.getActionDTO().getName()).get();
        EntitiesType entitiesType = entitiesTypeRepository.findById(notificationDataDTO.getEntitiesTypeDTO().getName()).get();
        NotificationData notificationData = notificationDataMapper.notificationDatoDTOToNotificationData(notificationDataDTO);
        notificationData.setEntitiesType(entitiesType);
        notificationData.setAction(action);

        // Save the NotificationData entity
        notificationDataRepository.save(notificationData);
    }

    public void deleteNotificationData(int id){
        Optional<NotificationData> notificationData = notificationDataRepository.findById(id);
        if(notificationData.isEmpty()){
            throw new ExistsException("Notification Data does not exist");
        }
        notificationDataRepository.delete(notificationData.get());
    }

    public void updateNotificationData(NotificationDataDTO notificationDataDTO){
        Optional<NotificationData> notificationData = notificationDataRepository.findById(notificationDataDTO.getId());
        if(notificationData.isEmpty()){
            throw new ExistsException("Notification Data does not exist.");
        }
        Optional<Action> action = actionRepository.findByName(notificationDataDTO.getActionDTO().getName());
        if(action.isEmpty()){
            throw new ExistsException("Action does not exist.");
        }
        Optional<EntitiesType> entitiesType = entitiesTypeRepository.findById(notificationDataDTO.getEntitiesTypeDTO().getName());
        if(entitiesType.isEmpty()){
            throw new ExistsException("Entity Type does not exist.");
        }
        NotificationData updatedNotificationData = notificationData.get();
        updatedNotificationData.setAction(action.get());
        updatedNotificationData.setEntitiesType(entitiesType.get());
        notificationDataRepository.save(updatedNotificationData);
    }

    public List<NotificationDataDTO> getAllNotificationDatas(){
        List<NotificationData> notificationDatas = notificationDataRepository.findAll();
        return notificationDataMapper.notificationDatasToNotificationDataDTOs(notificationDatas);
    }
}

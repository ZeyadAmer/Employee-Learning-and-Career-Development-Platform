package org.example.Services;

import org.aspectj.weaver.ast.Not;
import org.example.Entities.Notification;
import org.example.Entities.NotificationData;
import org.example.Entities.NotificationId;
import org.example.Exceptions.ExistsException;
import org.example.Mappers.NotificationDTO;
import org.example.Mappers.NotificationMapper;
import org.example.Repository.NotificationDataRepository;
import org.example.Repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private NotificationDataRepository notificationDataRepository;
    private NotificationRepository notificationRepository;
    private NotificationMapper notificationMapper;

    public NotificationService(NotificationDataRepository notificationDataRepository, NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationDataRepository = notificationDataRepository;
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    public void createNotification(NotificationDTO notificationDTO){
        if(notificationRepository.findById(notificationDTO.getNotificationId()).isPresent()){
            throw new ExistsException("Notification Already Exists");
        }
        if(notificationDataRepository.findById(notificationDTO.getNotificationData().getId()).isEmpty()){
            throw new ExistsException("Notification Data does not exist");
        }

        NotificationData notificationData = notificationDataRepository.findById(notificationDTO.getNotificationData().getId()).get();
        System.out.println("notification data: "+notificationData.getActorId());
        Notification notification = notificationMapper.notificationDTOToNotification(notificationDTO);
        notification.setNotificationData(notificationData);
        notificationRepository.save(notification);
    }

    public void deleteNotification(NotificationId notificationId){
        Optional<Notification> notification = notificationRepository.findById(notificationId);
        if(notification.isEmpty()){
            throw new ExistsException("Notification does not exist");
        }
        notificationRepository.delete(notification.get());
    }

    public List<NotificationDTO> getAllNotifications(){
        List<Notification> notifications = notificationRepository.findAll();
        return notificationMapper.notificationsToNotificationDTOs(notifications);
    }

}

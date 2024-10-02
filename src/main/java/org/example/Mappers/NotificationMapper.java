package org.example.Mappers;

import org.example.Entities.Notification;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationDTO notificationToNotificationDTO(Notification notification);
    Notification notificationDTOToNotification(NotificationDTO notificationDTO);
    List<NotificationDTO> notificationsToNotificationDTOs(List<Notification> notifications);
    List<Notification> notificationDTOsToNotifications(List<NotificationDTO> notificationDTOS);
}

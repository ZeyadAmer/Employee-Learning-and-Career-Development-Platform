package org.example.Mappers;

import org.example.Entities.NotificationData;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationDataMapper {
    NotificationDataDTO notificationDataToNotificationDataDTO(NotificationData notificationData);
    NotificationData notificationDatoDTOToNotificationData(NotificationDataDTO notificationDataDTO);
    List<NotificationDataDTO> notificationDatasToNotificationDataDTOs(List<NotificationData> notificationData);
    List<NotificationData> notificationDataDTOsToNotifcationDatas(List<NotificationDataDTO> notificationDataDTOS);
}

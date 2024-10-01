package org.example.Controllers;

import org.example.Mappers.NotificationDataDTO;
import org.example.Services.NotificationDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notificationData")
public class NotificationDataController {

    @Autowired
    private NotificationDataService notificationDataService;

    @PostMapping
    public ResponseEntity<String> createNotificationData(@RequestBody NotificationDataDTO notificationDataDTO){
        notificationDataService.createNotificationData(notificationDataDTO);
        return ResponseEntity.ok("Notification Data created.");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteNotificationData(@RequestBody int id){
        notificationDataService.deleteNotificationData(id);
        return ResponseEntity.ok("Notification Data deleted.");
    }

    @PutMapping
    public ResponseEntity<String> updateNotificationData(@RequestBody NotificationDataDTO notificationDataDTO){
        notificationDataService.updateNotificationData(notificationDataDTO);
        return ResponseEntity.ok("Notification Data updated.");
    }

    @GetMapping("/all")
    public ResponseEntity<List<NotificationDataDTO>> getAllNotificationDatas(){
        return ResponseEntity.ok(notificationDataService.getAllNotificationDatas());
    }
}

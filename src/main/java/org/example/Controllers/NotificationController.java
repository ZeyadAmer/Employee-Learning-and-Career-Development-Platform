package org.example.Controllers;

import org.example.Entities.NotificationId;
import org.example.Mappers.NotificationDTO;
import org.example.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OneToMany;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<String> createNotification(@RequestBody NotificationDTO notificationDTO){
        notificationService.createNotification(notificationDTO);
        return ResponseEntity.ok("Notification created");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteNotification(@RequestBody NotificationId notificationId){
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.ok("Notification deleted");
    }

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> getAllNotifications(){
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }
}

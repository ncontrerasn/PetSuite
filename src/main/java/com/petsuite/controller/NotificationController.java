package com.petsuite.controller;

import com.petsuite.Services.basics.Entero;
import com.petsuite.Services.dto.DogDayCare_Dto;
import com.petsuite.Services.dto.NotificationUserData_Dto;
import com.petsuite.Services.model.Notification;
import com.petsuite.Services.services.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class NotificationController {

    @Autowired
    ChangeNotificationStatusService changeNotificationStatusService;

    @Autowired
    DeleteNotificationService deleteNotificationService;
    @Autowired
    ShowNotificationService notificationService;

    @PostMapping("/readNotification")
    public boolean changeStatus(@Valid @RequestBody Entero id) {
        Integer res = changeNotificationStatusService.changeNotificationStatus(id.getEntero(), "Leido");
        if(res==1){
            deleteNotificationService.deleteNotification(id.getEntero());
            return true;
        }
        return false;
    }
    
    @PostMapping("/showMyNotifications")
    public List<Notification> getNotificationList(@Valid @RequestBody NotificationUserData_Dto userData) { return notificationService.getNotificationList(userData.getUser_id(), userData.getStatus());}
     
    
    

}




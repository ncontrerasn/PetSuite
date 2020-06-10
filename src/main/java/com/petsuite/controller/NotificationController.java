package com.petsuite.controller;

import com.petsuite.Services.basics.Entero;
import com.petsuite.Services.services.*;
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

    @GetMapping("/readNotification")
    public boolean changeStatus(@Valid @RequestBody Entero id) {
        Boolean res = changeNotificationStatusService.changeNotificationStatus(id.getEntero(), "Leido");
        if(res){
            deleteNotificationService.deleteNotification(id.getEntero());
            return true;
        }
        return false;
    }

}




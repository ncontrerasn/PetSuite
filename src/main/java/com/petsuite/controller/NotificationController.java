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

    @Autowired
    CreateNotificationService createNotificationService;

    @PostMapping("/readNotification")
    public boolean changeStatus(@Valid @RequestBody Entero id) {
        Integer res = changeNotificationStatusService.changeNotificationStatus(id.getEntero(), "Leido");
        if(res == 1){
            deleteNotificationService.deleteNotification(id.getEntero());
            return true;
        }
        return false;
    }
    
    @PostMapping("/showMyNotifications")
    public List<Notification> getNotificationList(@Valid @RequestBody NotificationUserData_Dto userData) { return notificationService.getNotificationList(userData.getUser_id(), userData.getStatus());}

    @PostMapping("/sendNotification")//recibir del front una notificacion con todos los datos pero con el id en null, es posible que mande de una vez el destinatario y el emisor en esa notificacion? en caso contrario hacer una consulta aqui
    public boolean sendNotification(@Valid @RequestBody Notification notification) {
         //El front tiene orden de recibir en el Subject el nombre del perro, a continuacion se hace el subject completo
        notification.setNotification_subject("Hay una emergencia con tu perro "+ notification.getNotification_subject());
        Notification notification1 = createNotificationService.createNotification(notification);
        if(notification1 != null)
            return true;
        return false;
    }

}




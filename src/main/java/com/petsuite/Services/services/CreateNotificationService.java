package com.petsuite.Services.services;

import com.petsuite.Services.model.Notification;
import com.petsuite.Services.repository.NotificationRepository;
import com.petsuite.Services.services.interfaces.ICreateNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateNotificationService implements ICreateNotification {

    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
}

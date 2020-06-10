package com.petsuite.Services.services;

import com.petsuite.Services.repository.NotificationRepository;
import com.petsuite.Services.services.interfaces.IDeleteNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteNotificationService implements IDeleteNotification {

    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public void deleteNotification(Integer notification_id) {
        notificationRepository.deleteById(notification_id);
    }
}

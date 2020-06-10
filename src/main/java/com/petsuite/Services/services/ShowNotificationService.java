package com.petsuite.Services.services;

import com.petsuite.Services.model.Notification;
import com.petsuite.Services.repository.NotificationRepository;
import com.petsuite.Services.services.interfaces.IShowNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowNotificationService implements IShowNotification {

    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public List<Notification> getNotificationList(String user, String status) {
        return notificationRepository.findByUserAndStatus(user, status);
    }
}

package com.petsuite.Services.services;

import com.petsuite.Services.repository.NotificationRepository;
import com.petsuite.Services.services.interfaces.IChangeNotificationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangeNotificationStatusService implements IChangeNotificationStatus {

    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public boolean changeNotificationStatus(Integer notification_id, String status) {
        return notificationRepository.changeNotificationStatus(status, notification_id);
    }
}


package com.petsuite.Services.services;

import com.petsuite.Services.model.Notification;
import com.petsuite.Services.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateNotificationServiceTest {

    @InjectMocks
    CreateNotificationService createNotificationService;

    @Mock
    NotificationRepository notificationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createNotification()
    {
        Notification notification = new Notification();

        notification.setNotification_subject("Cancelado");
        notification.setNotification_description("Estoy enfermo");
        notification.setNotification_id(1);
        notification.setNotification_status("Pending");
        notification.setUser("htovars");

        when(notificationRepository.save(any())).thenReturn(notification);

        assertEquals(notification.getNotification_subject(),createNotificationService.createNotification(notification).getNotification_subject());
    }
}
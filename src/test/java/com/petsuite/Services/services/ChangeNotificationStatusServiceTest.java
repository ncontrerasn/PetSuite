package com.petsuite.Services.services;

import com.petsuite.Services.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ChangeNotificationStatusServiceTest {

    @InjectMocks
    ChangeNotificationStatusService changeNotificationStatusService;

    @Mock
    NotificationRepository notificationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void changeNotificationStatus()
    {
        when(notificationRepository.changeNotificationStatus(anyString(),anyInt())).thenReturn(1);

        assertEquals(java.util.Optional.of(1),java.util.Optional.ofNullable(changeNotificationStatusService.changeNotificationStatus(1, "Status")));
    }
}
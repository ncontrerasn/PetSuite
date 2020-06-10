package com.petsuite.Services.services.interfaces;

import com.petsuite.Services.model.Notification;

import java.util.List;

public interface IShowNotification {

    List<Notification> getNotificationList(String user, String status);

}

package com.petsuite.Services.repository;

import com.petsuite.Services.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    @Query(value = "SELECT * FROM notification WHERE user = ?1 AND notification_status = ?2", nativeQuery = true)
    List<Notification> findByUserAndStatus(String user, String status);

    @Transactional
    @Modifying
    @Query(value = "UPDATE notification SET notification_status = ?1 WHERE notification_id = ?2", nativeQuery = true)
    Integer changeNotificationStatus(String status, Integer notification_id);
}

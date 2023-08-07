package com.ssafy.ssuk.notify.service;

import com.ssafy.ssuk.notify.domain.Notification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface NotificationService {
    void save(Notification notification);

    Optional<Notification> findNotification();

    void updateNotification(Notification notification);
}

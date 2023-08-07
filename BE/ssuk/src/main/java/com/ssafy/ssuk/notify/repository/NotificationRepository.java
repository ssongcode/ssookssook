package com.ssafy.ssuk.notify.repository;

import com.ssafy.ssuk.notify.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    // 조회 (visible 1인거만)
    @Query("select n from Notification n where n.user.id = :userId and n.visible = true ")
    Optional<Notification> findByUser_Id(@Param("userId") Integer userId);

    // 알림 확인 (visible 0으로 갱신)
    @Query("update Notification n set n.visible = 0 where n.id = :notificationId")
    void updateNotification(@Param("notificationId") Integer notificationId);
}

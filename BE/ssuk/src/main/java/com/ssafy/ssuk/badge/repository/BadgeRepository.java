package com.ssafy.ssuk.badge.repository;

import com.ssafy.ssuk.badge.Badge;

import java.util.List;

public interface BadgeRepository {
    void save(Badge badge);

    List<Badge> findAllByName(String badgeName);

    List<Badge> findAll();
}

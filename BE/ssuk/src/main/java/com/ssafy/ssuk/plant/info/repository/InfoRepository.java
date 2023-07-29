package com.ssafy.ssuk.plant.info.repository;

import com.ssafy.ssuk.plant.info.Info;

import java.util.List;

public interface InfoRepository {
    Info findOneById(Integer plantId, Integer level);

    void save(Info info);

    List<Info> findAll(Integer plantId);
}

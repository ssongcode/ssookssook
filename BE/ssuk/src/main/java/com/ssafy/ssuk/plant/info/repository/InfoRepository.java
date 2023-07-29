package com.ssafy.ssuk.plant.info.repository;

import com.ssafy.ssuk.plant.info.Info;

public interface InfoRepository {
    Info findOneById(Integer plantId, Integer level);

    void save(Info info);
}

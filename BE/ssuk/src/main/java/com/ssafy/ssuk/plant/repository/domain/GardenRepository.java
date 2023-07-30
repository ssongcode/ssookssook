package com.ssafy.ssuk.plant.repository.domain;

import com.ssafy.ssuk.plant.domain.Garden;
import com.ssafy.ssuk.plant.dto.response.GardenSearchOneResponseDto;

public interface GardenRepository {
    Garden findUsingByPotId(Integer potId);

    void save(Garden garden);

    Garden findOneById(Integer id);

    Garden findOneByIdAndUserId(Integer gardenId, Integer userId);
}

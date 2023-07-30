package com.ssafy.ssuk.plant.service;

import com.ssafy.ssuk.plant.domain.Garden;
import com.ssafy.ssuk.plant.domain.Plant;
import com.ssafy.ssuk.plant.dto.request.GardenRegisterRequestDto;

public interface GardenService {
    Garden findUsingByPotId(Integer potId);

    void save(Integer userId, Plant plant, String nickname);
}

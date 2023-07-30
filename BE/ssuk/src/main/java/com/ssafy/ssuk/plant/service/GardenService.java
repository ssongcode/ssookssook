package com.ssafy.ssuk.plant.service;

import com.ssafy.ssuk.plant.domain.Garden;
import com.ssafy.ssuk.plant.domain.Plant;
import com.ssafy.ssuk.plant.dto.request.GardenRenameRequestDto;
import com.ssafy.ssuk.plant.dto.response.GardenSearchOneResponseDto;
import com.ssafy.ssuk.pot.domain.Pot;

public interface GardenService {
    Garden findUsingByPotId(Integer potId);

    void save(Integer userId, Plant plant, Pot pot, String nickname);

    Garden findOndById(Integer id);

    boolean deleteGarden(Integer userId, Integer gardenId);

    boolean renameGarden(Integer gardenId, Integer userId, String nickname);

    Garden findOndByIdAndUserId(Integer gardenId, Integer userId);
}

package com.ssafy.ssuk.plant.service;

import com.ssafy.ssuk.plant.domain.Garden;
import com.ssafy.ssuk.plant.domain.Plant;
import com.ssafy.ssuk.plant.dto.request.GardenRegisterRequestDto;
import com.ssafy.ssuk.plant.repository.domain.GardenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GardenServiceImpl implements GardenService {

    private final GardenRepository gardenRepository;

    @Override
    public Garden findUsingByPotId(Integer potId) {
        return gardenRepository.findUsingByPotId(potId);
    }

    @Override
    @Transactional
    public void save(Integer userId, Plant plant, String nickname) {
        // 일단 potId는 1로 고정...
        Garden garden = new Garden(userId, plant, 1, nickname);
        gardenRepository.save(garden);
    }
}

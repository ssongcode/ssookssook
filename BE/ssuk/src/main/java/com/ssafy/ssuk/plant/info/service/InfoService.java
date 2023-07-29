package com.ssafy.ssuk.plant.info.service;

import com.ssafy.ssuk.plant.info.Info;
import com.ssafy.ssuk.plant.info.dto.InfoRegisterRequestDto;
import com.ssafy.ssuk.plant.plant.Plant;

import java.util.List;

public interface InfoService {
    boolean isDuplicated(Integer plantId, Integer level);

    void saveInfo(InfoRegisterRequestDto infoRegisterRequestDto, Plant plant);

    List<Info> findAll(Integer plantId);

    Info findOne(Integer plantId, Integer level);
}

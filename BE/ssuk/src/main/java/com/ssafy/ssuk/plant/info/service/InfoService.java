package com.ssafy.ssuk.plant.info.service;

import com.ssafy.ssuk.plant.info.dto.InfoRegisterRequestDto;

public interface InfoService {
    boolean isDuplicated(Integer plantId, Integer level);

    void saveInfo(InfoRegisterRequestDto infoRegisterRequestDto);
}

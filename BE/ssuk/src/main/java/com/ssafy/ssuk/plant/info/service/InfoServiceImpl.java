package com.ssafy.ssuk.plant.info.service;

import com.ssafy.ssuk.plant.info.Info;
import com.ssafy.ssuk.plant.info.dto.InfoRegisterRequestDto;
import com.ssafy.ssuk.plant.info.repository.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService {
    private final InfoRepository infoRepository;

    @Override
    public boolean isDuplicated(Integer plantId, Integer level) {
        Info info = infoRepository.findOneById(plantId, level);
        return info != null;
    }

    @Override
    @Transactional
    public void saveInfo(InfoRegisterRequestDto infoRegisterRequestDto) {
        infoRepository.save(new Info(infoRegisterRequestDto));
    }
}

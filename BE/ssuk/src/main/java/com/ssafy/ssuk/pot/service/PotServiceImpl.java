package com.ssafy.ssuk.pot.service;

import com.ssafy.ssuk.pot.domain.Pot;
import com.ssafy.ssuk.pot.dto.requset.PotInsertDto;
import com.ssafy.ssuk.pot.repository.PotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PotServiceImpl implements PotService {

    PotRepository potRepository;

    @Autowired
    public PotServiceImpl(PotRepository potRepository) {
        this.potRepository = potRepository;
    }

    //화분 조회 (유저가 가지고 있는 전체 화분 조회)
    @Override
    public List<Pot> findByUser_Id(Integer user_id) {

        return potRepository.findByUser_Id(user_id);
    }

    @Override
    public void save(Pot pot) {
        Pot findPot = potRepository.findBySerialNumber(pot.getSerialNumber());

        // 조회 후, 등록여부가 false 면 등록이 가능
        if (!findPot.getIsRegisted()) {
            findPot.getUser().setId(findPot.getId());
            findPot.setRegistedDate(LocalDateTime.now());
        }
        else {// 조회 후, 등록여부가 true면 등록이 불가능
            //예외 처리
        }

    }


}

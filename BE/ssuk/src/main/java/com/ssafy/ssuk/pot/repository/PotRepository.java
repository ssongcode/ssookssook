package com.ssafy.ssuk.pot.repository;

import com.ssafy.ssuk.pot.domain.Pot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PotRepository extends JpaRepository<Pot, Integer> {
    //물려있는지 체크
    Pot findBySerialNumber(String serialNumber);
    //조회
    List<Pot> findByUser_Id(Integer user_id);
    //상세조회

    //등록

    //수정

    //삭제
}

package com.ssafy.ssuk.plant.service;

import com.ssafy.ssuk.plant.PlantCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlantInfoServiceImpl implements PlantInfoService{

}

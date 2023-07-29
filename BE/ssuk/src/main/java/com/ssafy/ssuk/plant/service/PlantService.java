package com.ssafy.ssuk.plant.service;

import com.ssafy.ssuk.plant.Plant;
import com.ssafy.ssuk.plant.category.Category;
import com.ssafy.ssuk.plant.dto.PlantRegisterRequestDto;

import java.util.List;

public interface PlantService {
    public void savePlant(Category category, PlantRegisterRequestDto plantRegisterRequestDto);

    public List<Plant> findPlants();
}

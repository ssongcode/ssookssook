package com.ssafy.ssuk.plant.plant.service;

import com.ssafy.ssuk.plant.plant.Plant;
import com.ssafy.ssuk.plant.category.Category;
import com.ssafy.ssuk.plant.plant.dto.PlantRegisterRequestDto;
import com.ssafy.ssuk.plant.plant.dto.PlantUpdateRequestDto;

import java.util.List;

public interface PlantService {
    public void savePlant(Category category, PlantRegisterRequestDto plantRegisterRequestDto);

    public List<Plant> findPlants();

    Plant findOneById(Integer id);

    boolean modifyPlant(PlantUpdateRequestDto plantUpdateRequestDto, Category category);
}

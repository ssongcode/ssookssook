package com.ssafy.ssuk.plant.plant.repository;

import com.ssafy.ssuk.plant.plant.Plant;

import java.util.List;

public interface PlantRepository {
    public void save(Plant newPlant);
    public List<Plant> findAll();
    public Plant findOneById(Integer id);
}

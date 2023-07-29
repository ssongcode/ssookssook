package com.ssafy.ssuk.plant.repository;

import com.ssafy.ssuk.plant.Plant;

import java.util.List;

public interface PlantRepository {
    void save(Plant newPlant);

    List<Plant> findAll();

    Plant findOneById(Integer id);
}

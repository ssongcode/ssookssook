package com.ssafy.ssuk.plant.repository;

import com.ssafy.ssuk.plant.PlantCategory;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PlantCategoryRepository {
    public void save(PlantCategory plantCategory);
    public PlantCategory findOneById(Integer id);
    public List<PlantCategory> findAll();
    public List<PlantCategory> findAllByName(String name);
    public Collection<Object> findAllByNameExceptId(Integer id, String name);
}

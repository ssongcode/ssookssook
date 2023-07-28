package com.ssafy.ssuk.plant.service;

import com.ssafy.ssuk.plant.PlantCategory;

import java.util.List;

public interface PlantCategoryService {

    public void savePlantCategory(PlantCategory plantCategory);

    public boolean updatePlantCategory(Integer categoryId, String updateName);

    public List<PlantCategory> findPlantCategories();

    public PlantCategory findOneById(Integer categoryId);

    public boolean isDuplicateName(String name);
}

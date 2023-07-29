package com.ssafy.ssuk.plant.category.repository;

import com.ssafy.ssuk.plant.category.Category;

import java.util.Collection;
import java.util.List;

public interface CategoryRepository {
    public void save(Category plantCategory);
    public Category findOneById(Integer id);
    public List<Category> findAll();
    public List<Category> findAllByName(String name);
    public List<Category> findAllByNameExceptId(Integer id, String name);
}

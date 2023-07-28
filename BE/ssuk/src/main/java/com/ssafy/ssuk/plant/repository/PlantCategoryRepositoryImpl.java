package com.ssafy.ssuk.plant.repository;

import com.ssafy.ssuk.plant.PlantCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlantCategoryRepositoryImpl implements PlantCategoryRepository {

    private final EntityManager em;

    @Override
    public void save(PlantCategory plantCategory) {
        em.persist(plantCategory);
    }

    @Override
    public PlantCategory findOneById(Integer id) {
        return em.find(PlantCategory.class, id);
    }

    @Override
    public List<PlantCategory> findAll() {
        return em.createQuery("select pc from PlantCategory pc").getResultList();
    }

    @Override
    public List<PlantCategory> findAllByName(String name) {
        return em.createQuery("select pc from PlantCategory pc where pc.categoryName = :name")
                .setParameter("name", name)
                .getResultList();
    }
}

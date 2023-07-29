package com.ssafy.ssuk.plant.repository;

import com.ssafy.ssuk.plant.Plant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlantRepositoryImpl implements PlantRepository{

    private final EntityManager em;

    @Override
    public void save(Plant newPlant) {
        em.persist(newPlant);
    }

    @Override
    public List<Plant> findAll() {
        return em.createQuery("select p from Plant p" +
                " join fetch p.category pc", Plant.class).getResultList();
    }
}

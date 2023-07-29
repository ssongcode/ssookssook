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

    @Override
    public Plant findOneById(Integer id) {
        List<Plant> result = em.createQuery("select p from Plant p" +
                " join fetch p.category pc" +
                " where p.id = :id", Plant.class)
                .setParameter("id", id).getResultList();
        if(result.isEmpty())
            return null;
        return result.get(0);
    }
}

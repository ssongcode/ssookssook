package com.ssafy.ssuk.plant;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "plant_category")
@Getter @Setter
public class PlantCategory {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Integer id;

    @Column(name = "category_name")
    private String name;

    public PlantCategory(String name) {
        this.name = name;
    }

    public PlantCategory() {

    }
}

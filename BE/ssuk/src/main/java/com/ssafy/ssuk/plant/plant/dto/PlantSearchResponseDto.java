package com.ssafy.ssuk.plant.plant.dto;

import com.ssafy.ssuk.plant.plant.Plant;
import lombok.Data;

@Data
public class PlantSearchResponseDto {

    private Integer plantId;
    private String plantName;
    private String categoryName;
    private Float tempMax;
    private Float tempMin;
    private Float moistureMax;
    private Float moistureMin;

    public PlantSearchResponseDto(Plant plant) {
        plantId = plant.getId();
        plantName = plant.getName();
        categoryName = plant.getCategory().getName();
        tempMax = plant.getTempMax();
        tempMin = plant.getTempMin();
        moistureMax = plant.getMoistureMax();
        moistureMin = plant.getMoistureMin();
    }
}

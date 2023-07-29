package com.ssafy.ssuk.plant.plant.dto;

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

    public PlantSearchResponseDto(Integer plantId, String plantName, String categoryName, Float tempMax, Float tempMin, Float moistureMax, Float moistureMin) {
        this.plantId = plantId;
        this.plantName = plantName;
        this.categoryName = categoryName;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.moistureMax = moistureMax;
        this.moistureMin = moistureMin;
    }
}

package com.ssafy.ssuk.plant.dto;

import lombok.Data;

@Data
public class PlantCategorySearchResponseDto {
    String categoryName;

    public PlantCategorySearchResponseDto(String categoryName) {
        this.categoryName = categoryName;
    }
}

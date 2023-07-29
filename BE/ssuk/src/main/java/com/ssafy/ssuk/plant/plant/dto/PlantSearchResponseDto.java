package com.ssafy.ssuk.plant.plant.dto;

import com.ssafy.ssuk.plant.info.Info;
import com.ssafy.ssuk.plant.info.dto.InfoSearchResponseDto;
import com.ssafy.ssuk.plant.plant.Plant;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PlantSearchResponseDto {

    private Integer plantId;
    private String plantName;
    private String categoryName;
    private Float tempMax;
    private Float tempMin;
    private Float moistureMax;
    private Float moistureMin;
    private List<InfoSearchResponseDto> plantInfos = new ArrayList<>();

    public PlantSearchResponseDto(Plant plant) {
        plantId = plant.getId();
        plantName = plant.getName();
        categoryName = plant.getCategory().getName();
        tempMax = plant.getTempMax();
        tempMin = plant.getTempMin();
        moistureMax = plant.getMoistureMax();
        moistureMin = plant.getMoistureMin();
        plantInfos = plant.getInfos().stream().map(pi -> new InfoSearchResponseDto(pi)).collect(Collectors.toList());
    }
}

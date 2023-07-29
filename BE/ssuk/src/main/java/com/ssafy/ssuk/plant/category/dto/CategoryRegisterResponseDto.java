package com.ssafy.ssuk.plant.category.dto;

import lombok.Data;

@Data
public class CategoryRegisterResponseDto {
    private String message;

    public CategoryRegisterResponseDto(String message) {
        this.message = message;
    }
}

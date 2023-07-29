package com.ssafy.ssuk.plant.category.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryRegisterRequestDto {
    @NotBlank
    private String categoryName;
}

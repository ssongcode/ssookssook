package com.ssafy.ssuk.plant.info.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class InfoRegisterRequestDto {
    @NotNull
    private Integer plantId;
    @NotNull
    @Range(min = 1, max = 3)
    private Integer level;
    @NotBlank
    private String plantGuide;
    @NotNull
    private Integer waterTerm;
    @NotNull
    private Integer waterAmount;
    @NotBlank
    private String characterName;
    @NotBlank
    private String characterComment;
    @NotBlank
    private String characterImage;
}

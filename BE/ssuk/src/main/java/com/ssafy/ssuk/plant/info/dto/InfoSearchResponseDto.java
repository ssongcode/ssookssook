package com.ssafy.ssuk.plant.info.dto;

import com.ssafy.ssuk.plant.info.Info;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class InfoSearchResponseDto {
    private Integer level;
    private String guide;
    private Integer waterTerm;
    private Integer waterAmount;
    private String characterName;
    private String characterComment;
    private String characterImage;

    public InfoSearchResponseDto(Info info) {
        this.level = info.getLevel();
        this.guide = info.getGuide();
        this.waterTerm = info.getWaterTerm();
        this.waterAmount = info.getWaterAmount();
        this.characterName = info.getCharacterName();
        this.characterComment = info.getCharacterComment();
        this.characterImage = info.getCharacterImage();
    }
}

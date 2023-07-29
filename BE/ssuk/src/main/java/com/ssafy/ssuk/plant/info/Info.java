package com.ssafy.ssuk.plant.info;

import com.ssafy.ssuk.plant.info.dto.InfoRegisterRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "plant_info")
@IdClass(InfoId.class)
@NoArgsConstructor
@Getter
public class Info {

    @Id
    @Column(name = "plant_id")
    private Integer plantId;
    @Id
    private Integer level;
    @Column(name = "plant_guide")
    private String guide;
    @Column(name = "water_term")
    private Integer waterTerm;
    @Column(name = "water_amount")
    private Integer waterAmount;
    @Column(name = "character_name")
    private String characterName;
    @Column(name = "character_comment")
    private String characterComment;
    @Column(name = "character_image")
    private String characterImage;

    public Info(InfoRegisterRequestDto infoRegisterRequestDto) {
        this.plantId = infoRegisterRequestDto.getPlantId();
        this.level = infoRegisterRequestDto.getLevel();
        this.guide = infoRegisterRequestDto.getPlantGuide();
        this.waterTerm = infoRegisterRequestDto.getWaterTerm();
        this.waterAmount = infoRegisterRequestDto.getWaterAmount();
        this.characterName = infoRegisterRequestDto.getCharacterName();
        this.characterComment = infoRegisterRequestDto.getCharacterComment();
        this.characterImage = infoRegisterRequestDto.getCharacterImage();
    }
}

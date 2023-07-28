package com.ssafy.ssuk.plant.controller;

import com.ssafy.ssuk.plant.PlantCategory;
import com.ssafy.ssuk.plant.dto.PlantCategoryRegisterRequestDto;
import com.ssafy.ssuk.plant.dto.PlantCategoryRegisterResponseDto;
import com.ssafy.ssuk.plant.service.PlantCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("plantinfo")
@RequiredArgsConstructor
@Slf4j
public class PlantInfoController {

    private final PlantCategoryService plantCategoryService;

    private final String SUCCESS = "OK";
    private final String FAIL = "false";

    @PostMapping("/category/admin")
    public ResponseEntity<PlantCategoryRegisterResponseDto> registerCategory(@RequestBody @Validated PlantCategoryRegisterRequestDto plantCategoryRegisterRequestDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(new PlantCategoryRegisterResponseDto(FAIL), HttpStatus.CONFLICT);   // 수정해야함 코드
        }

        String name = plantCategoryRegisterRequestDto.getCategoryName();
        log.debug("name={}", name);
        if(plantCategoryService.isDuplicateName(name)) {
            return new ResponseEntity<>(new PlantCategoryRegisterResponseDto(FAIL), HttpStatus.CONFLICT);   // 수정해야함 코드
        }
        plantCategoryService.savePlantCategory(new PlantCategory(name));
        return new ResponseEntity<>(new PlantCategoryRegisterResponseDto(SUCCESS), HttpStatus.OK);   // 수정해야함 코드
    }
}

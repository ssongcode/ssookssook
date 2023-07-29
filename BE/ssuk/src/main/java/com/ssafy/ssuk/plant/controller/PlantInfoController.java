package com.ssafy.ssuk.plant.controller;

import com.ssafy.ssuk.plant.Plant;
import com.ssafy.ssuk.plant.category.Category;
import com.ssafy.ssuk.plant.category.dto.CategoryRegisterRequestDto;
import com.ssafy.ssuk.plant.category.dto.CategoryRegisterResponseDto;
import com.ssafy.ssuk.plant.category.dto.CategorySearchResponseDto;
import com.ssafy.ssuk.plant.category.dto.CategoryUpdateRequestDto;
import com.ssafy.ssuk.plant.dto.*;
import com.ssafy.ssuk.plant.category.service.CategoryService;
import com.ssafy.ssuk.plant.service.PlantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("plantinfo")
@RequiredArgsConstructor
@Slf4j
public class PlantInfoController {

    private final CategoryService plantCategoryService;
    private final PlantService plantService;

    private final String SUCCESS = "OK";
    private final String FAIL = "false";

    @GetMapping("/category")
    public ResponseEntity<ResponseDto> searchCategories() {
        List<CategorySearchResponseDto> collect = plantCategoryService.findPlantCategories()
                .stream()
                .map(pc -> new CategorySearchResponseDto(pc.getId(), pc.getName()))
                .collect(Collectors.toList());
        ResponseDto responseDto = new ResponseDto(SUCCESS);
        responseDto.getData().put("categories", collect);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/category/admin")
    public ResponseEntity<CategoryRegisterResponseDto> registerCategory(@RequestBody @Validated CategoryRegisterRequestDto plantCategoryRegisterRequestDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(new CategoryRegisterResponseDto(FAIL), HttpStatus.CONFLICT);   // 수정해야함 코드
        }

        String name = plantCategoryRegisterRequestDto.getCategoryName();
        log.debug("name={}", name);
        if(plantCategoryService.isDuplicateName(name)) {
            return new ResponseEntity<>(new CategoryRegisterResponseDto(FAIL), HttpStatus.CONFLICT);   // 수정해야함 코드
        }
        plantCategoryService.savePlantCategory(new Category(name));
        return new ResponseEntity<>(new CategoryRegisterResponseDto(SUCCESS), HttpStatus.OK);
    }

    @PutMapping("/category/admin")
    public ResponseEntity<ResponseDto> updateCategory(
            @RequestBody @Validated CategoryUpdateRequestDto plantCategoryUpdateRequestDto,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return new ResponseEntity<>(new ResponseDto(FAIL), HttpStatus.NOT_FOUND);

        Integer id = plantCategoryUpdateRequestDto.getCategoryId();
        String updateName = plantCategoryUpdateRequestDto.getUpdateName();

        if(plantCategoryService.isDuplicateName(id, updateName))
            return new ResponseEntity<>(new ResponseDto(FAIL), HttpStatus.CONFLICT);

        plantCategoryService.modifyPlantCategory(id, updateName);

        return new ResponseEntity<>(new ResponseDto(SUCCESS), HttpStatus.OK);
    }

    @PostMapping("/plant/admin")
    public ResponseEntity<ResponseDto>  registerPlant(
            @RequestBody @Validated PlantRegisterRequestDto plantRegisterRequestDto,
            BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>(new ResponseDto(FAIL), HttpStatus.NOT_FOUND);

        Category category = plantCategoryService.findOneById(plantRegisterRequestDto.getCategoryId());
        if(category == null)
            return new ResponseEntity<>(new ResponseDto(FAIL), HttpStatus.NOT_FOUND);
        // 이름 중복 검사를 안해도 되나..
        plantService.savePlant(category, plantRegisterRequestDto);
        return new ResponseEntity<>(new ResponseDto(SUCCESS), HttpStatus.OK);
    }

    @GetMapping("/plant/{plantId}")
    public ResponseEntity<ResponseDto> searchPlant(@PathVariable Integer plantId) {
        // 필요없을듯?
//        if(plantId == null)
//            return new ResponseEntity<>(new ResponseDto(FAIL), HttpStatus.NOT_FOUND);

        Plant plant = plantService.findOneById(plantId);
        if(plant == null)
            return new ResponseEntity<>(new ResponseDto(FAIL), HttpStatus.NOT_FOUND);
        PlantSearchResponseDto returnDto = new PlantSearchResponseDto(
                plant.getName(),
                plant.getCategory().getName(),
                plant.getTempMax(),
                plant.getTempMin(),
                plant.getMoistureMax(),
                plant.getMoistureMin()
        );
        ResponseDto responseDto = new ResponseDto(SUCCESS);
        responseDto.getData().put("plants", returnDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/plant")
    public ResponseEntity<ResponseDto> searchPlants() {
        List<PlantSearchResponseDto> collect = plantService.findPlants()
                .stream()
                .map(p -> new PlantSearchResponseDto(
                        p.getName(),
                        p.getCategory().getName(),
                        p.getTempMax(), p.getTempMin(),
                        p.getMoistureMax(),
                        p.getMoistureMin()))
                .collect(Collectors.toList());
        ResponseDto responseDto = new ResponseDto(SUCCESS);
        responseDto.getData().put("plants", collect);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/plant/admin")
    public ResponseEntity<ResponseDto> updateCategory(
            @RequestBody @Validated PlantUpdateRequestDto plantUpdateRequestDto,
            BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return new ResponseEntity<>(new ResponseDto(FAIL), HttpStatus.NOT_FOUND);

        Category category = plantCategoryService.findOneById(plantUpdateRequestDto.getCategoryId());

        if(category == null)
            return new ResponseEntity<>(new ResponseDto("존재하지 않는 카테고리입니다."), HttpStatus.CONFLICT);

        if(!plantService.modifyPlant(plantUpdateRequestDto, category))
            return new ResponseEntity<>(new ResponseDto("존재하지 않는 식물입니다."), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new ResponseDto(SUCCESS), HttpStatus.OK);
    }
}

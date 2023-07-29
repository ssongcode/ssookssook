package com.ssafy.ssuk.plant.category.repository;

import com.ssafy.ssuk.plant.dto.TotalCategoryResponseDto;

import java.util.List;

public interface CategoryQueryRepository {
    List<TotalCategoryResponseDto> findTotalInfo();
    List<TotalCategoryResponseDto> findTotalInfo(List<Integer> catergoryIds);
}

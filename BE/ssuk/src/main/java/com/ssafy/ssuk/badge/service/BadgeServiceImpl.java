package com.ssafy.ssuk.badge.service;

import com.ssafy.ssuk.badge.Badge;
import com.ssafy.ssuk.badge.dto.request.BadgeRegisterRequestDto;
import com.ssafy.ssuk.badge.dto.response.BadgeSearchResponseDto;
import com.ssafy.ssuk.badge.repository.BadgeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class BadgeServiceImpl implements BadgeService {

    private final BadgeRepository badgeRepository;

    @Override
    @Transactional
    public void saveBadge(BadgeRegisterRequestDto badgeRegisterRequestDto) {
        Badge badge = new Badge(badgeRegisterRequestDto);
        log.info("badge={}", badge);
        badgeRepository.save(badge);
    }

    @Override
    public boolean isDuplicated(String badgeName) {
        if(badgeRepository.findAllByName(badgeName).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<BadgeSearchResponseDto> findAll() {
        return badgeRepository.findAll()
                .stream()
                .map(b -> new BadgeSearchResponseDto(b))
                .collect(Collectors.toList());
    }
}

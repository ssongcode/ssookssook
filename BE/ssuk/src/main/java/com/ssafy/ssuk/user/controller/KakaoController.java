package com.ssafy.ssuk.user.controller;

import com.ssafy.ssuk.user.domain.User;
import com.ssafy.ssuk.user.dto.request.KakaoCodeRequsetDto;
import com.ssafy.ssuk.utils.auth.jwt.TokenInfo;
import com.ssafy.ssuk.utils.auth.oauth.kakao.KakaoAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/kakao")
public class KakaoController {

    private final KakaoAuthService kakaoAuthService;

    private final String REDIRECT_URL = "http://i9b102.p.ssafy.io:8080/kakao";
    @GetMapping("")
    public String kakao(@RequestParam String code) throws Exception {
        log.debug("tmpcode={}", code);
//        String kakaoAccessToken = kakaoAuthService.getAccessToken(code, REDIRECT_URL).getAccessToken();
//        // 사용자 정보 가져오거나 회원가입
//        User user = kakaoAuthService.saveOrGetUser(kakaoAccessToken);
////        redisService.
//        log.debug("회원가입 또는 사용자 정보 가져오기");
//        TokenInfo tokenInfo = kakaoAuthService.kakaoLogin(user.getEmail());
//        log.debug("loginTokenInfo={}", tokenInfo);

        return "redirect:http://i9b102.p.ssafy.io:8080?code=" + code;
    }

    @PostMapping("/code")
    public ResponseEntity<TokenInfo> kakaoLogin(@RequestBody @Validated KakaoCodeRequsetDto kakaoCodeRequsetDto) throws Exception {
        String code = kakaoCodeRequsetDto.getCode();
        log.debug("code={}", code);
        String kakaoAccessToken = kakaoAuthService.getAccessToken(code).getAccessToken();
        // 사용자 정보 가져오거나 회원가입
        User user = kakaoAuthService.saveOrGetUser(kakaoAccessToken);
//        redisService.
        log.debug("회원가입 또는 사용자 정보 가져오기");
        TokenInfo tokenInfo = kakaoAuthService.kakaoLogin(user.getEmail());
        log.debug("loginTokenInfo={}", tokenInfo);

        return new ResponseEntity<>(tokenInfo, HttpStatus.OK);
    }
}
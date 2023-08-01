package com.ssafy.ssuk.user.controller;

import com.ssafy.ssuk.plant.dto.response.ResponseDto;
import com.ssafy.ssuk.user.domain.User;
import com.ssafy.ssuk.user.dto.request.CheckEmailRequestDto;
import com.ssafy.ssuk.user.dto.request.LoginRequestDto;
import com.ssafy.ssuk.user.dto.request.RegisterUserRequestDto;
import com.ssafy.ssuk.user.service.UserService;
import com.ssafy.ssuk.utils.email.EmailMessage;
import com.ssafy.ssuk.utils.jwt.TokenInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final EmailMessage emailMessage;

    // 회원가입 시 이메일 인증
    @GetMapping("/email")
    public ResponseEntity<?> checkEmail
    (@RequestBody @Validated CheckEmailRequestDto checkEmailRequestDto, BindingResult bindingResult) throws Exception {
        log.debug("휴");
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>("Email is empty", HttpStatus.FORBIDDEN);
        }
        Optional<User> findUser = userService.findByEmail(checkEmailRequestDto);
        // 사용자 존재 => 중복 => 가입 안됨
        if (findUser.isPresent()) {
            return new ResponseEntity<>("FALSE", HttpStatus.CONFLICT);
        }
        // 사용자 존재X => 이메일로 인증번호 전송
        String authCode = emailMessage.sendMail(checkEmailRequestDto.getEmail());
//        System.out.println(authCode);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    // 사용자 등록
    @PostMapping("/join")
    public ResponseEntity<?> registerUser
    (@RequestBody @Validated RegisterUserRequestDto registerUserRequestDto, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>("error", HttpStatus.FORBIDDEN);
        }
        log.debug("dddd");
        userService.createUser(registerUserRequestDto);
        log.debug("assaas");
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    // 로그인
    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Validated LoginRequestDto loginRequestDto, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>("error", HttpStatus.FORBIDDEN);
        }
//        String email = loginRequestDto.getEmail();
//        String password = loginRequestDto.getPassword();
        log.debug("before token");
        TokenInfo tokenInfo = userService.login(loginRequestDto);
        System.out.println(tokenInfo.toString());
        return new ResponseEntity<>(tokenInfo,HttpStatus.OK);
    }


}

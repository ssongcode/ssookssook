package com.ssafy.ssuk.user.controller;

import com.ssafy.ssuk.plant.dto.response.ResponseDto;
import com.ssafy.ssuk.user.domain.User;
import com.ssafy.ssuk.user.dto.request.CheckEmailRequestDto;
import com.ssafy.ssuk.user.dto.request.RegisterUserRequestDto;
import com.ssafy.ssuk.user.service.UserService;
import com.ssafy.ssuk.utils.email.EmailMessage;
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

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final UserService userService;
    private final EmailMessage emailMessage;

    @Autowired
    public UserController(UserService userService, EmailMessage emailMessage) {
        this.userService = userService;
        this.emailMessage = emailMessage;
    }

    // 회원 가입
    @PostMapping("/join")
    public ResponseEntity<ResponseDto> registerUser(@RequestBody @Validated RegisterUserRequestDto registerUserRequestDto) {


        return null;
    }

    // 회원가입 시 이메일 인증
    @GetMapping("/email")
    public ResponseEntity<?> checkEmail(@RequestBody @Validated CheckEmailRequestDto checkEmailRequestDto, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>("Email is empty", HttpStatus.FORBIDDEN);
        }
        User findUser = userService.findByEmail(checkEmailRequestDto);
        // 해당 이메일을 가진 유저가 있음 => 중복 => 가입 안됨
        if (findUser != null) {
            return new ResponseEntity<>("FALSE", HttpStatus.CONFLICT);
        }
        // 해당 이메일을 가진 유저가 없음 => 이메일로 인증번호 전송
        String authCode = emailMessage.sendMail(checkEmailRequestDto.getEmail());
//        System.out.println(authCode);
        return new ResponseEntity<>("OK", HttpStatus.OK);

    }
}

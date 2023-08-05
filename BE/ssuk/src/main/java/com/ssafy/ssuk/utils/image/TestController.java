package com.ssafy.ssuk.utils.image;

import com.ssafy.ssuk.user.domain.User;
import com.ssafy.ssuk.user.repository.UserRepository;
import com.ssafy.ssuk.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/test")
@RestController
@Slf4j
public class TestController {

    private final S3Uploader s3Uploader;
    private final UserService userService;
    private final UserRepository userRepository;
    private final static String IMAGE_URL = "https://ssook.s3.ap-northeast-2.amazonaws.com/image/";

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> upload(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        String imageName = s3Uploader.upload(multipartFile);
        Map<String, String> map = new HashMap<>();
        map.put("imageName", imageName);
        map.put("imageUrl", IMAGE_URL + imageName);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("")
    public String length() {
        User user = userService.findById(3);

        return IMAGE_URL + user.getProfileImage();
    }
}

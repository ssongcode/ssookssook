package com.ssafy.ssuk.notify.controller;

import com.ssafy.ssuk.notify.dto.PushRequestDto;
import com.ssafy.ssuk.notify.dto.TokenRequestDto;
import com.ssafy.ssuk.notify.service.FcmService;
import com.ssafy.ssuk.pot.dto.requset.PotInsertDto;
import com.ssafy.ssuk.utils.response.CommonResponseEntity;
import com.ssafy.ssuk.utils.response.SuccessCode;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    private final FcmService fcmService;

    @Autowired
    public TestController(FcmService fcmService) {
        this.fcmService = fcmService;
    }

    private static final String EXPO_PUSH_API_URL = "https://exp.host/--/api/v2/push/send";
    //private static final String EXPO_PUSH_TOKEN = "ExponentPushToken[mwb75mLXxguCpfxMmtzy-2]";

    private static final String EXPO_PUSH_TOKEN = "ExponentPushToken[7psLXVD6U22E9wNznpIlOZ]";

    //모듈화를 해봅시다
    @PostMapping("/send-push")
    public String sendPushNotification(@RequestBody PotInsertDto potInsertDto) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(EXPO_PUSH_API_URL);
            httpPost.addHeader("Accept", "application/json");
            httpPost.addHeader("Content-Type", "application/json");

            // Replace YOUR_EXPO_PUSH_TOKEN with your actual Expo push token
            httpPost.addHeader("Expo-Push-Token", EXPO_PUSH_TOKEN);

            // Create the JSON payload for the push notification
            String payload = "{\n" +
                    "  \"to\": \"" + EXPO_PUSH_TOKEN + "\",\n" +
                    "  \"title\": \"" + "hi " + "\",\n" +
                    "  \"body\": \"" + "TT" + "\"\n" +
                    "}";

            HttpEntity entity = new StringEntity(payload);
            httpPost.setEntity(entity);

            // Send the POST request to Expo Push API
            HttpResponse response = httpClient.execute(httpPost);

            // Check the response status (200 indicates success)
            if (response.getStatusLine().getStatusCode() == 200) {
                return "Push notification sent successfully!";
            } else {
                return "Failed to send push notification!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while sending push notification!";
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> insertToken(@RequestAttribute Integer userId, @RequestBody TokenRequestDto tokenRequestDto)
    {
        fcmService.checkTokenByUser_Id(userId, tokenRequestDto);
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_FCM, null);
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendPush(@RequestAttribute Integer userId, @RequestBody PushRequestDto pushRequestDto)
    {
        fcmService.sendPushTo(userId,pushRequestDto.getTitle(), pushRequestDto.getBody());
        return CommonResponseEntity.getResponseEntity(SuccessCode.SUCCESS_FCM, null);
    }
}

package com.ssafy.ssuk.notify.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.ssafy.ssuk.notify.domain.Fcm;
import com.ssafy.ssuk.notify.dto.FCMMessageDto;
import com.ssafy.ssuk.notify.dto.TokenRequestDto;
import com.ssafy.ssuk.notify.mapper.FcmMapper;
import com.ssafy.ssuk.notify.repository.FcmRepository;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Service
public class FcmService {
    private final ObjectMapper objectMapper;
    private final FcmRepository fcmRepository;
    private final FcmMapper fcmMapper;

    @Autowired
    public FcmService(ObjectMapper objectMapper, FcmRepository fcmRepository, FcmMapper fcmMapper) {
        this.objectMapper = objectMapper;
        this.fcmRepository = fcmRepository;
        this.fcmMapper = fcmMapper;
    }

    public String getAccessToken() throws IOException {
        // firebase로 부터 access token을 가져온다.
        String firebaseConfigPath = "key.json";

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped(Arrays.asList("https://www.googleapis.com/auth/cloud-platform"));

        googleCredentials.refreshIfExpired();

        return googleCredentials.getAccessToken().getTokenValue();
    }

    public String makeMessage(
            String targetToken, String title, String body, String name, String description
    ) throws JsonProcessingException {

        String targetToken2 = "";
        FCMMessageDto fcmMessage = FCMMessageDto.builder()
                .message(
                        FCMMessageDto.Message.builder()
                                .token(targetToken)
                                .notification(
                                        FCMMessageDto.Notification.builder()
                                                .title(title)
                                                .body(body)
                                                .build()
                                )
                                .data(
                                        FCMMessageDto.Data.builder()
                                                .name(name)
                                                .description(description)
                                                .build()
                                )
                                .build()
                )
                .validateOnly(false)
                .build();

        return objectMapper.writeValueAsString(fcmMessage);
    }

    /**
     * 알림 푸쉬를 보내는 역할을 하는 메서드
     *
     * @param targetToken : 푸쉬 알림을 받을 클라이언트 앱의 식별 토큰
     */
    public void sendMessageTo(
            String targetToken, String title, String body, String id, String isEnd
    ) throws IOException {
        //String firebaseAlarmSendApiUri = "https://fcm.googleapis.com/v1/projects/ssuk-ssuk-push-server/messages:send";
        String firebaseAlarmSendApiUri = "https://exp.host/@sonsuhyeong/api/v2/push/send";
        String targetToken2 = "ExponentPushToken[mwb75mLXxguCpfxMmtzy-2]";
        String message = makeMessage(targetToken2, title, body, id, isEnd);

        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(message, MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(firebaseAlarmSendApiUri)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();

        Response response = client.newCall(request).execute();

        log.info(response.body().string());

        return;
    }


    //유저가 토큰을 가지고 있는지 체크
    public void checkTokenByUser_Id(Integer userId, TokenRequestDto tokenRequestDto) {
        Optional<Fcm> findFcm = fcmRepository.findByUser_Id(userId);
        //save
        if (!findFcm.isPresent()) {
            Fcm fcm = fcmMapper.requestDtoToFcm(tokenRequestDto, userId);
            fcmRepository.save(fcm);
        } else {
            findFcm.get().setFcm_token(tokenRequestDto.getFcm_token());
        }

    }

}

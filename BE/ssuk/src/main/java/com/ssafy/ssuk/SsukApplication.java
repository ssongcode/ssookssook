package com.ssafy.ssuk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {
        org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration.class,
        org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration.class,
        org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration.class
})
@RestController // 테스트용
@RequestMapping("/")
public class SsukApplication {
    public static final String APPLICATION_LOCATIONS = "spring.config.location=" +
            "classpath:application.yml" +
            "classpath:aws.yml";

    @GetMapping("")
    String home()
    {
        return "ssook ssook!";
    }

    @PostMapping("")
    String testPost()
    {
        return "testPost";
    }

    public static void main(String[] args) {
        SpringApplication.run(SsukApplication.class, args);
    }

}

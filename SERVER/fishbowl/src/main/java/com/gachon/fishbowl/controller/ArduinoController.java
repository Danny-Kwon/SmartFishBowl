package com.gachon.fishbowl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Controller
public class ArduinoController {

    //**아두이노에서 요청 수신 시

    //기기번호 체크 후 해당 기기 테이블에 센싱 정보 저장

    //물 온도 세팅값 이탈 시 앱으로 알림
    //물 탁도 세팅값 이탈 시 앱으로 알림
    //물 수위 세팅값 이탈 시 앱으로 알림
    //물 ph 세팅값 이탈 시 앱으로 알림

//    @GetMapping("/bowl")
//    public String chatGET(){
//
//        log.info("@ChatController, chat GET()");
//
//        return "chat";
//    }
}
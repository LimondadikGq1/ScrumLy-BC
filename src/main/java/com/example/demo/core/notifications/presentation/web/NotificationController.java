/*
package com.example.demo.core.notifications.presentation.web;

import com.example.demo.unknown.websockets.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;
@Slf4j
@Controller
@RequiredArgsConstructor
public class NotificationController {

    @MessageMapping("/greeting")
    public void handleInviteProjectNotification(
            Principal principal
    ){
        UserPrincipal user = (UserPrincipal) principal;
        log.info("email:{},user:{}",user.email(),user.getName());

    }
}
*/

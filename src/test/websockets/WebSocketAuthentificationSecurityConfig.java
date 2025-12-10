package com.example.demo.auth.configurations.websockets;

import com.example.demo.unknown.websockets.AuthChannelInterseptorAdapter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Order;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@RequiredArgsConstructor
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class WebSocketAuthentificationSecurityConfig implements WebSocketMessageBrokerConfigurer {
    private final AuthChannelInterseptorAdapter interseptorAdapter;


    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(interseptorAdapter);
    }
}

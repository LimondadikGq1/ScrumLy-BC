package com.example.demo.unknown.websockets;

import com.example.demo.auth.jwt.readers.JwtTokenReader;
import com.example.demo.auth.http.exceptions.AuthentificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthChannelInterseptorAdapter implements ChannelInterceptor {
    private final JwtTokenReader jwtTokenReader;
    private final static String JWT = "Authorization";

    @Autowired
    public AuthChannelInterseptorAdapter(
            @Qualifier("websockets-reader") JwtTokenReader jwtTokenReader) {
        this.jwtTokenReader = jwtTokenReader;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        final StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if(StompCommand.CONNECT == accessor.getCommand()){
            final String jwt = accessor.getFirstNativeHeader(JWT);
            final UsernamePasswordAuthenticationToken
                    user = jwtTokenReader.extractUser(jwt);
            if(user == null){
                throw new AuthentificationException("Error parse");
            }

            accessor.setUser(user);
        }
        return message;
    }
}

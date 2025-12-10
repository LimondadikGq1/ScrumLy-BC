package com.example.demo.auth.configurations.websockets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app")

        ;
        registry.enableSimpleBroker("/topic","/queue");


    }
    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        scheduler.setThreadNamePrefix("ws-heartbeat-thread-sheduler");
        scheduler.setDaemon(true);
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        scheduler.initialize();
        return  scheduler;
    }
    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
       registry.setMessageSizeLimit(16 * 1024)
               .setSendTimeLimit(5 * 1000)
               .setSendBufferSizeLimit(32*1024);
    }
}

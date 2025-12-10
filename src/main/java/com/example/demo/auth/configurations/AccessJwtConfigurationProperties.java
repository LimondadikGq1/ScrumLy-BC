package com.example.demo.auth.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "security.token.access", ignoreUnknownFields = false)
public class AccessJwtConfigurationProperties {

    private String secret;

    private Duration duration;
}

package com.example.demo.core.projects.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "project.key", ignoreInvalidFields = false)
public class KeyConfiguration {

    private String prefix;
    private Integer min;
    private Integer max;
}

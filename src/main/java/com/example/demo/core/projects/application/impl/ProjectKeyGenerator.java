package com.example.demo.core.projects.application.impl;

import com.example.demo.core.projects.configuration.KeyConfiguration;
import com.example.demo.core.projects.infrastructure.jpa.ProjectRepository;
import com.example.demo.global.utils.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ProjectKeyGenerator implements KeyGenerator {

    private final KeyConfiguration keyConfiguration;

    private final ProjectRepository projectRepository;
    private final Random random;
    private final String JIRA_PREFIX;
    private final Integer MIN;
    private final Integer MAX;

    @Autowired
    public ProjectKeyGenerator(KeyConfiguration keyConfiguration,
                               ProjectRepository projectRepository) {
        this.keyConfiguration = keyConfiguration;
        this.projectRepository = projectRepository;
        JIRA_PREFIX = keyConfiguration.getPrefix();
        MIN = keyConfiguration.getMin();
        MAX = keyConfiguration.getMax();
        this.random = new Random();
    }

    @Override
    public String generateKey() {
        String jiraKey = keyConfiguration.getPrefix() + generateNum();

        while(projectRepository.findKey(jiraKey).isPresent()){
            jiraKey = JIRA_PREFIX + generateNum();
        }
        return jiraKey;
    }

    private Integer generateNum(){
        return random.nextInt(MAX - MIN + 1) + MIN;
    }
}

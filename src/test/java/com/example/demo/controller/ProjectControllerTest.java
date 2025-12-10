package com.example.demo.controller;

import com.example.demo.global.exceptions.responses.ErrorResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class ProjectControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
   void its_(){
        ResponseEntity<ErrorResponse> response =
                restTemplate.exchange(
                       "/admin/",
                        HttpMethod.POST,
                        null,
                        ErrorResponse.class
                );
        Assertions.assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }
}

package com.aneeq.venuemanager.contoller;

import com.aneeq.venuemanager.MockAuthorizerRequest;
import com.aneeq.venuemanager.dto.model.request.AuthorizerRequest;
import com.aneeq.venuemanager.repository.AuthorizerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorizerControllerFunctionalTests {

    @Autowired
    AuthorizerRepository authorizerRepository;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void testCreateAuthorizer() {
        AuthorizerRequest authorizerRequest = MockAuthorizerRequest.generateAuthorizerRequest();
        ResponseEntity<Void> response = testRestTemplate.postForEntity("/authorizers", authorizerRequest, Void.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
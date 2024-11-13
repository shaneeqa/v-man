package com.aneeq.venuemanager.service;

import com.aneeq.venuemanager.MockAuthorizerRequest;
import com.aneeq.venuemanager.dto.model.request.AuthorizerRequest;
import com.aneeq.venuemanager.entity.Authorizer;
import com.aneeq.venuemanager.repository.AuthorizerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AuthorizerServiceIntegrationTests {

    @Autowired
    AuthorizerService authorizerService;

    @Autowired
    AuthorizerRepository authorizerRepository;

    @BeforeEach
    void cleanup() {
        authorizerRepository.deleteAll();
    }

    @Test
    void testSaveAuthorizer() {
        AuthorizerRequest authorizerRequest = MockAuthorizerRequest.generateAuthorizerRequest();
        authorizerService.saveAuthorizer(authorizerRequest);
        List<Authorizer> authorizers = authorizerRepository.findAll();

        assertEquals(authorizers.get(0).getName(), authorizerRequest.getName());
        assertEquals(authorizers.get(0).getDesignation(), authorizerRequest.getDesignation());
    }
}

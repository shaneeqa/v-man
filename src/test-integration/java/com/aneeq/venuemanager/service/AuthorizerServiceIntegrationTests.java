package com.aneeq.venuemanager.service;

import com.aneeq.venuemanager.MockAuthorizer;
import com.aneeq.venuemanager.MockAuthorizerRequest;
import com.aneeq.venuemanager.dto.model.request.AuthorizerRequest;
import com.aneeq.venuemanager.dto.model.response.AuthorizerResponse;
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

    @Test
    void testGetAllAuthorizers() {
        List<Authorizer> authorizers = MockAuthorizer.generateAuthorizerList(4);
        authorizerRepository.saveAll(authorizers);
        List<AuthorizerResponse> authorizerResponses = authorizerService.getAllAuthorizers();

        assertEquals(4, authorizerResponses.size());
        assertAuthorizerResponseList(authorizers, authorizerResponses);
    }

    private void assertAuthorizerResponseObject(Authorizer authorizer, AuthorizerResponse authorizerResponse) {
        assertEquals(authorizer.getName(), authorizerResponse.getName());
        assertEquals(authorizer.getDesignation(), authorizerResponse.getDesignation());
    }

    private void assertAuthorizerResponseList(List<Authorizer> authorizerList, List<AuthorizerResponse> authorizerResponseList) {
        for (int i = 0; i < authorizerList.size(); i++) {
            assertAuthorizerResponseObject(authorizerList.get(i), authorizerResponseList.get(i));
        }
    }
}

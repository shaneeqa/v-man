package com.aneeq.venuemanager.service;

import com.aneeq.venuemanager.MockAuthorizer;
import com.aneeq.venuemanager.MockAuthorizerRequest;
import com.aneeq.venuemanager.dto.model.request.AuthorizerRequest;
import com.aneeq.venuemanager.dto.model.response.AuthorizerResponse;
import com.aneeq.venuemanager.entity.Authorizer;
import com.aneeq.venuemanager.exception.AuthorizerNotFoundException;
import com.aneeq.venuemanager.repository.AuthorizerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testGetAuthorizerById() throws AuthorizerNotFoundException {
        Authorizer authorizer = authorizerRepository.save(MockAuthorizer.generateAuthorizer());
        AuthorizerResponse authorizerResponse = authorizerService.getAuthorizerById(authorizer.getId());

        assertNotNull(authorizerResponse);
        assertAuthorizerResponseObject(authorizer, authorizerResponse);
    }

    @Test
    void testUpdateAuthorizerById() throws AuthorizerNotFoundException {
        AuthorizerRequest authorizerRequest = MockAuthorizerRequest.generateAuthorizerRequest();
        Authorizer authorizer = authorizerRepository.save(MockAuthorizer.generateAuthorizer());
        authorizerService.updateAuthorizerById(authorizer.getId(), authorizerRequest);

        Optional<Authorizer> updatedAuthorizer = authorizerRepository.findById(authorizer.getId());
        assertAuthorizerRequestObject(updatedAuthorizer.get(), authorizerRequest);
    }

    @Test
    void testDeleteAuthorizerById() throws AuthorizerNotFoundException {
        Authorizer authorizer = authorizerRepository.save(MockAuthorizer.generateAuthorizer());
        authorizerService.deleteAuthorizerById(authorizer.getId());
        assertFalse(authorizerRepository.existsById(authorizer.getId()));
    }

    private void assertAuthorizerRequestObject(Authorizer authorizer, AuthorizerRequest authorizerRequest) {
        assertEquals(authorizer.getName(), authorizerRequest.getName());
        assertEquals(authorizer.getDesignation(), authorizerRequest.getDesignation());
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

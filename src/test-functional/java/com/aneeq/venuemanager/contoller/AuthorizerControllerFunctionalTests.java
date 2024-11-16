package com.aneeq.venuemanager.contoller;

import com.aneeq.venuemanager.MockAuthorizer;
import com.aneeq.venuemanager.MockAuthorizerRequest;
import com.aneeq.venuemanager.MockVenue;
import com.aneeq.venuemanager.dto.model.request.AuthorizerRequest;
import com.aneeq.venuemanager.dto.model.response.AuthorizerResponse;
import com.aneeq.venuemanager.dto.model.response.VenueResponse;
import com.aneeq.venuemanager.entity.Authorizer;
import com.aneeq.venuemanager.entity.Venue;
import com.aneeq.venuemanager.repository.AuthorizerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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

    @Test
    void testViewAllAuthorizers() {
        ResponseEntity<AuthorizerResponse[]> authorizerResponses = testRestTemplate.getForEntity("/authorizers", AuthorizerResponse[].class);
        assertEquals(HttpStatus.OK, authorizerResponses.getStatusCode());
    }

    @Test
    void testViewAuthorizerById() {
        Authorizer authorizer = authorizerRepository.save(MockAuthorizer.generateAuthorizer());
        ResponseEntity<AuthorizerResponse> authorizerResponse = testRestTemplate.getForEntity("/authorizers/{id}", AuthorizerResponse.class, authorizer.getId());
        assertEquals(HttpStatus.OK, authorizerResponse.getStatusCode());
    }

    @Test
    void testUpdateVenueById() {
        Authorizer authorizer = authorizerRepository.save(MockAuthorizer.generateAuthorizer());
        ResponseEntity<AuthorizerResponse> response = testRestTemplate.exchange(
                "/authorizers/{id}",
                HttpMethod.PUT,
                new HttpEntity<>(authorizer),
                AuthorizerResponse.class,
                authorizer.getId()
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testDeleteVenueById() {
        Authorizer authorizer = authorizerRepository.save(MockAuthorizer.generateAuthorizer());
        ResponseEntity<AuthorizerResponse> response = testRestTemplate.exchange(
                "/authorizers/{id}",
                HttpMethod.DELETE,
                new HttpEntity<>(authorizer),
                AuthorizerResponse.class,
                authorizer.getId()
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}

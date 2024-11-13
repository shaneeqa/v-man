package com.aneeq.venuemanager.contoller;

import com.aneeq.venuemanager.MockAuthorizerRequest;
import com.aneeq.venuemanager.controller.AuthorizerController;
import com.aneeq.venuemanager.dto.model.request.AuthorizerRequest;
import com.aneeq.venuemanager.service.AuthorizerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

class AuthorizerControllerTests {

    @InjectMocks
    private AuthorizerController authorizerController;

    @Mock
    private AuthorizerService authorizerService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAuthorizer() {
        AuthorizerRequest authorizerRequest = MockAuthorizerRequest.generateAuthorizerRequest();
        ResponseEntity<AuthorizerRequest> response = authorizerController.createAuthorizer(authorizerRequest);

        verify(authorizerService).saveAuthorizer(authorizerRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}

package com.aneeq.venuemanager.contoller;

import com.aneeq.venuemanager.MockAuthorizerRequest;
import com.aneeq.venuemanager.MockAuthorizerResponse;
import com.aneeq.venuemanager.controller.AuthorizerController;
import com.aneeq.venuemanager.dto.model.request.AuthorizerRequest;
import com.aneeq.venuemanager.dto.model.response.AuthorizerResponse;
import com.aneeq.venuemanager.service.AuthorizerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test
    void testViewAllAuthorizers() {
        List<AuthorizerResponse> authorizerResponses = MockAuthorizerResponse.generateAuthorizeResponseList(3);
        when(authorizerService.getAllAuthorizers()).thenReturn(authorizerResponses);
        ResponseEntity<List<AuthorizerResponse>> authorizerResponseList = authorizerController.viewAllAuthorizers();

        assertEquals(HttpStatus.OK, authorizerResponseList.getStatusCode());
        assertEquals(authorizerResponses, authorizerResponseList.getBody());
    }
}

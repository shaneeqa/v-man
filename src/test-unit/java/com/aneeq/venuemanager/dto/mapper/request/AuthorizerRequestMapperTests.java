package com.aneeq.venuemanager.dto.mapper.request;

import com.aneeq.venuemanager.MockAuthorizerRequest;
import com.aneeq.venuemanager.dto.model.request.AuthorizerRequest;
import com.aneeq.venuemanager.entity.Authorizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorizerRequestMapperTests {

    @InjectMocks
    AuthorizerRequestMapperImpl authorizerRequestMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthorizerRequestToAuthorizer() {
        AuthorizerRequest authorizerRequest = MockAuthorizerRequest.generateAuthorizerRequest();
        Authorizer authorizer = authorizerRequestMapper.authorizerRequestToAuthorizer(authorizerRequest);
        assertEquals(authorizer.getName(), authorizerRequest.getName());
        assertEquals(authorizer.getDesignation(), authorizerRequest.getDesignation());
    }
}

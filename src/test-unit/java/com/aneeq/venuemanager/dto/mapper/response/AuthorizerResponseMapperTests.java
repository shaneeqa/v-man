package com.aneeq.venuemanager.dto.mapper.response;

import com.aneeq.venuemanager.MockAuthorizer;
import com.aneeq.venuemanager.dto.model.response.AuthorizerResponse;
import com.aneeq.venuemanager.entity.Authorizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorizerResponseMapperTests {

    @InjectMocks
    AuthorizerResponseMapperImpl authorizerResponseMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthorizersToAuthorizerResponses() {
        List<Authorizer> authorizers = MockAuthorizer.generateAuthorizerList(3);
        List<AuthorizerResponse> authorizerResponses = authorizerResponseMapper.authorizersToAuthorizerResponses(authorizers);

        assertEquals(authorizers.get(2).getId(), authorizerResponses.get(2).getId());
        assertEquals(authorizers.get(0).getDesignation(), authorizerResponses.get(0).getDesignation());
    }
}

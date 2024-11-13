package com.aneeq.venuemanager.service;

import com.aneeq.venuemanager.MockAuthorizerRequest;
import com.aneeq.venuemanager.dto.mapper.request.AuthorizerRequestMapperImpl;
import com.aneeq.venuemanager.dto.model.request.AuthorizerRequest;
import com.aneeq.venuemanager.repository.AuthorizerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AuthorizerServiceTests {

    @InjectMocks
    private AuthorizerService authorizerService;

    @Mock
    private AuthorizerRepository authorizerRepository;

    @Mock
    private AuthorizerRequestMapperImpl authorizerRequestMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAuthorizer() {
        AuthorizerRequest authorizerRequest = MockAuthorizerRequest.generateAuthorizerRequest();
        authorizerService.saveAuthorizer(authorizerRequest);

        verify(authorizerRepository, times(1)).save(authorizerRequestMapper.authorizerRequestToAuthorizer(authorizerRequest));
    }
}

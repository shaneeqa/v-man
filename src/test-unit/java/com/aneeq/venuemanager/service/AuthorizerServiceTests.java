package com.aneeq.venuemanager.service;

import com.aneeq.venuemanager.MockAuthorizer;
import com.aneeq.venuemanager.MockAuthorizerRequest;
import com.aneeq.venuemanager.dto.mapper.request.AuthorizerRequestMapperImpl;
import com.aneeq.venuemanager.dto.mapper.response.AuthorizerResponseMapperImpl;
import com.aneeq.venuemanager.dto.model.request.AuthorizerRequest;
import com.aneeq.venuemanager.dto.model.response.AuthorizerResponse;
import com.aneeq.venuemanager.entity.Authorizer;
import com.aneeq.venuemanager.repository.AuthorizerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthorizerServiceTests {

    @InjectMocks
    private AuthorizerService authorizerService;

    @Mock
    private AuthorizerRepository authorizerRepository;

    @Mock
    private AuthorizerRequestMapperImpl authorizerRequestMapper;

    @Spy
    private AuthorizerResponseMapperImpl authorizerResponseMapper;

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

    @Test
    void testGetAllAuthorizers(){
        List<Authorizer> authorizers = MockAuthorizer.generateAuthorizerList(3);
        when(authorizerRepository.findAll()).thenReturn(authorizers);

        List<AuthorizerResponse> allAuthorizers = authorizerService.getAllAuthorizers();

        verify(authorizerRepository, times(1)).findAll();
        assertEquals(3,allAuthorizers.size());

        for(int i =0; i<allAuthorizers.size();i++){
            assertAuthorizerObject(allAuthorizers.get(i),authorizers.get(i));
        }
    }

    private void assertAuthorizerObject(AuthorizerResponse authorizerResponse, Authorizer authorizer){
        assertEquals(authorizerResponse.getId(), authorizer.getId());
        assertEquals(authorizerResponse.getName(), authorizer.getName());
        assertEquals(authorizerResponse.getDesignation(), authorizer.getDesignation());
    }
}

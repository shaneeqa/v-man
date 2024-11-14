package com.aneeq.venuemanager.service;

import com.aneeq.venuemanager.MockAuthorizer;
import com.aneeq.venuemanager.MockAuthorizerRequest;
import com.aneeq.venuemanager.dto.mapper.request.AuthorizerRequestMapperImpl;
import com.aneeq.venuemanager.dto.mapper.response.AuthorizerResponseMapperImpl;
import com.aneeq.venuemanager.dto.model.request.AuthorizerRequest;
import com.aneeq.venuemanager.dto.model.response.AuthorizerResponse;
import com.aneeq.venuemanager.entity.Authorizer;
import com.aneeq.venuemanager.exception.AuthorizerNotFoundException;
import com.aneeq.venuemanager.repository.AuthorizerRepository;
import com.aneeq.venuemanager.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void testGetAllAuthorizers_isEmpty(){
        when(authorizerRepository.findAll()).thenReturn(Collections.emptyList());
        assertTrue(authorizerService.getAllAuthorizers().isEmpty());
    }

    @Test
    void testGetAuthorizerById() throws AuthorizerNotFoundException {
        Authorizer authorizer = MockAuthorizer.generateAuthorizer();
        when(authorizerRepository.findById(2)).thenReturn(Optional.of(authorizer));

        AuthorizerResponse authorizerResponse = authorizerService.getAuthorizerById(2);

        verify(authorizerRepository, times(1)).findById(2);
        verify(authorizerResponseMapper, times(1)).authorizerToAuthorizerResponse(authorizer);
        assertNotNull(authorizerResponse);
        assertAuthorizerObject(authorizerResponse, authorizer);
    }

    @Test
    void testGetAuthorizerById_AuthorizerNotFound(){
        when(authorizerRepository.findById(1)).thenReturn(Optional.empty());
        AuthorizerNotFoundException authorizerNotFoundException = assertThrows(AuthorizerNotFoundException.class, () -> authorizerService.getAuthorizerById(1));
        assertEquals(Util.AUTHORIZER_NOT_FOUND_EXCEPTION_MSG, authorizerNotFoundException.getMessage());
    }

    private void assertAuthorizerObject(AuthorizerResponse authorizerResponse, Authorizer authorizer){
        assertEquals(authorizerResponse.getId(), authorizer.getId());
        assertEquals(authorizerResponse.getName(), authorizer.getName());
        assertEquals(authorizerResponse.getDesignation(), authorizer.getDesignation());
    }
}

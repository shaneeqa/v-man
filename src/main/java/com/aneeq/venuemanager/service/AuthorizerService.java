package com.aneeq.venuemanager.service;

import com.aneeq.venuemanager.dto.mapper.request.AuthorizerRequestMapper;
import com.aneeq.venuemanager.dto.mapper.response.AuthorizerResponseMapper;
import com.aneeq.venuemanager.dto.model.request.AuthorizerRequest;
import com.aneeq.venuemanager.dto.model.response.AuthorizerResponse;
import com.aneeq.venuemanager.entity.Authorizer;
import com.aneeq.venuemanager.exception.AuthorizerNotFoundException;
import com.aneeq.venuemanager.repository.AuthorizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorizerService {

    private AuthorizerRepository authorizerRepository;
    private AuthorizerRequestMapper authorizerRequestMapper;
    private AuthorizerResponseMapper authorizerResponseMapper;

    @Autowired
    public void setAuthorizerRepository(AuthorizerRepository authorizerRepository) {
        this.authorizerRepository = authorizerRepository;
    }

    @Autowired
    public void setAuthorizerRequestMapper(AuthorizerRequestMapper authorizerRequestMapper) {
        this.authorizerRequestMapper = authorizerRequestMapper;
    }

    @Autowired
    public void setAuthorizerResponseMapper(AuthorizerResponseMapper authorizerResponseMapper) {
        this.authorizerResponseMapper = authorizerResponseMapper;
    }

    public Authorizer saveAuthorizer(AuthorizerRequest authorizerRequest) {
        return authorizerRepository.save(authorizerRequestMapper.authorizerRequestToAuthorizer(authorizerRequest));
    }

    public List<AuthorizerResponse> getAllAuthorizers() {
        return authorizerResponseMapper.authorizersToAuthorizerResponses(authorizerRepository.findAll());
    }

    public AuthorizerResponse getAuthorizerById(Integer id) throws AuthorizerNotFoundException {
        Optional<Authorizer> authorizerById = authorizerRepository.findById(id);
        if (authorizerById.isEmpty())
            throw new AuthorizerNotFoundException();

        return authorizerResponseMapper.authorizerToAuthorizerResponse(authorizerById.get());
    }

    public void updateAuthorizerById(Integer id, AuthorizerRequest authorizerRequest) throws AuthorizerNotFoundException {
        Optional<Authorizer> authorizer = authorizerRepository.findById(id);

        if (authorizer.isEmpty())
            throw new AuthorizerNotFoundException();

        authorizerRequestMapper.authorizerRequestToAuthorizer(authorizer.get(), authorizerRequest);
        authorizerRepository.save(authorizer.get());
    }
}

package com.aneeq.venuemanager.service;

import com.aneeq.venuemanager.dto.mapper.request.AuthorizerRequestMapper;
import com.aneeq.venuemanager.dto.model.request.AuthorizerRequest;
import com.aneeq.venuemanager.entity.Authorizer;
import com.aneeq.venuemanager.repository.AuthorizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizerService {

    private AuthorizerRepository authorizerRepository;
    private AuthorizerRequestMapper authorizerRequestMapper;

    @Autowired
    public void setAuthorizerRepository(AuthorizerRepository authorizerRepository) {
        this.authorizerRepository = authorizerRepository;
    }

    @Autowired
    public void setAuthorizerRequestMapper(AuthorizerRequestMapper authorizerRequestMapper) {
        this.authorizerRequestMapper = authorizerRequestMapper;
    }

    public Authorizer saveAuthorizer(AuthorizerRequest authorizerRequest) {
        return authorizerRepository.save(authorizerRequestMapper.authorizerRequestToAuthorizer(authorizerRequest));
    }
}

package com.aneeq.venuemanager.dto.mapper.response;


import com.aneeq.venuemanager.dto.model.response.AuthorizerResponse;
import com.aneeq.venuemanager.entity.Authorizer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AuthorizerResponseMapper {
    public abstract List<AuthorizerResponse> authorizersToAuthorizerResponses(List<Authorizer> authorizer);
    public abstract AuthorizerResponse authorizerToAuthorizerResponse(Authorizer authorizer);
}

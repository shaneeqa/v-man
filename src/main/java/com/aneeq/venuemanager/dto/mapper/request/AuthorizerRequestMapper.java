package com.aneeq.venuemanager.dto.mapper.request;

import com.aneeq.venuemanager.dto.model.request.AuthorizerRequest;
import com.aneeq.venuemanager.entity.Authorizer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class AuthorizerRequestMapper {

    @Mapping(target = "id", ignore = true)
    public abstract Authorizer authorizerRequestToAuthorizer(AuthorizerRequest authorizerRequest);
}

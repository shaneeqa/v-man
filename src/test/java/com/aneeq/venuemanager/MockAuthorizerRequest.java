package com.aneeq.venuemanager;

import com.aneeq.venuemanager.dto.model.request.AuthorizerRequest;
import com.github.javafaker.Faker;

public class MockAuthorizerRequest {
    private static final Faker FAKER = new Faker();

    public static AuthorizerRequest generateAuthorizerRequest() {
        AuthorizerRequest authorizerRequest = new AuthorizerRequest();

        authorizerRequest.setName(FAKER.ancient().hero());
        authorizerRequest.setDesignation(FAKER.backToTheFuture().character());

        return authorizerRequest;
    }

}

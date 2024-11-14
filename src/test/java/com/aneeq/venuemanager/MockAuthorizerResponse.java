package com.aneeq.venuemanager;

import com.aneeq.venuemanager.dto.model.response.AuthorizerResponse;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class MockAuthorizerResponse {

    private static final Faker FAKER = new Faker();

    public static AuthorizerResponse generateAuthorizerResponse() {
        AuthorizerResponse authorizerResponse = new AuthorizerResponse();

        authorizerResponse.setId(FAKER.random().nextInt(1, 100000));
        authorizerResponse.setName(FAKER.ancient().hero());
        authorizerResponse.setDesignation(FAKER.backToTheFuture().character());

        return authorizerResponse;
    }

    public static List<AuthorizerResponse> generateAuthorizeResponseList(int num) {
        ArrayList<AuthorizerResponse> authorizerArrayList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            authorizerArrayList.add(generateAuthorizerResponse());
        }

        return authorizerArrayList;

    }
}

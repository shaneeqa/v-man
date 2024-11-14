package com.aneeq.venuemanager;

import com.aneeq.venuemanager.entity.Authorizer;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class MockAuthorizer {
    private static final Faker FAKER = new Faker();

    public static Authorizer generateAuthorizer() {
        Authorizer authorizer = new Authorizer();

        authorizer.setId(FAKER.random().nextInt(1, 100000));
        authorizer.setName(FAKER.ancient().hero());
        authorizer.setDesignation(FAKER.backToTheFuture().character());

        return authorizer;
    }

    public static List<Authorizer> generateAuthorizerList(int num) {
        ArrayList<Authorizer> authorizerArrayList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            authorizerArrayList.add(generateAuthorizer());
        }

        return authorizerArrayList;
    }
}

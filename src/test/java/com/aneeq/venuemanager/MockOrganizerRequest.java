package com.aneeq.venuemanager;

import com.aneeq.venuemanager.dto.model.request.OrganizerRequest;
import com.github.javafaker.Faker;

public class MockOrganizerRequest {
    private static final Faker FAKER = new Faker();

    public static OrganizerRequest generateOrganizerRequest(){
        OrganizerRequest organizerRequest = new OrganizerRequest();
        organizerRequest.setName(FAKER.ancient().hero());
        return organizerRequest;
    }
}

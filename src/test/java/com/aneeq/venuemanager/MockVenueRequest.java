package com.aneeq.venuemanager;

import com.aneeq.venuemanager.dto.model.request.VenueRequest;
import com.github.javafaker.Faker;

public class MockVenueRequest {

    private static final Faker FAKER = new Faker();

    public static VenueRequest generateVenueRequest() {
        VenueRequest venueRequest = new VenueRequest();

        venueRequest.setName(FAKER.ancient().titan());
        venueRequest.setLocation(FAKER.address().city());
        return venueRequest;
    }
}

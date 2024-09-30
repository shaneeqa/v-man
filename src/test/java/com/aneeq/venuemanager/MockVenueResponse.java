package com.aneeq.venuemanager;

import com.aneeq.venuemanager.dto.model.response.VenueResponse;
import com.aneeq.venuemanager.entity.Venue;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class MockVenueResponse {
    private static final Faker FAKER = new Faker();

    public static VenueResponse generateVenueResponse() {
        VenueResponse venueResponse = new VenueResponse();

        venueResponse.setId(FAKER.random().nextInt(1, 100000));
        venueResponse.setName(FAKER.ancient().titan());
        venueResponse.setLocation(FAKER.address().city());

        return venueResponse;
    }

    public static List<VenueResponse> generateVenueResponseList(int num) {
        ArrayList<VenueResponse> venueArrayList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            venueArrayList.add(generateVenueResponse());
        }
        return venueArrayList;
    }
}

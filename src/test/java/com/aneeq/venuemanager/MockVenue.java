package com.aneeq.venuemanager;

import com.aneeq.venuemanager.entity.Venue;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class MockVenue {
    private static final Faker FAKER = new Faker();

    public static Venue generateVenue() {
        Venue venue = new Venue();

        venue.setId(FAKER.random().nextInt(1, 100000));
        venue.setName(FAKER.ancient().titan());
        venue.setLocation(FAKER.address().city());

        return venue;
    }

    public static List<Venue> generateVenueList(int num) {
        ArrayList<Venue> venueArrayList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            venueArrayList.add(generateVenue());
        }
        return venueArrayList;
    }
}

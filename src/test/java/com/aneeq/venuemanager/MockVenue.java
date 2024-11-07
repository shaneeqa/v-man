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

    /**
     *  to generate list of venues with the name 'auditorium'
     * @param num
     * @return list of auditoriums
     */
    public static List<Venue> generateAuditoriumList(int num){
        ArrayList<Venue> auditoriums = new ArrayList<>();

        for (int o=0; o<num; o++){
            Venue venue = new Venue();

            venue.setId(FAKER.random().nextInt(1, 100000));
            venue.setName(FAKER.ancient().titan() + "Auditorium");
            venue.setLocation(FAKER.address().city());
            auditoriums.add(venue);
        }
        return  auditoriums;
    }
}

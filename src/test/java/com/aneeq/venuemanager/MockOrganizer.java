package com.aneeq.venuemanager;

import com.aneeq.venuemanager.entity.Organizer;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class MockOrganizer {
    private static final Faker FAKER = new Faker();

    public static Organizer generateOrganizer() {
        Organizer organizer = new Organizer();

        organizer.setId(FAKER.random().nextInt(1, 100000));
        organizer.setName(FAKER.ancient().hero());

        return organizer;
    }

    public static List<Organizer> generateOrganizerList(int num) {
        ArrayList<Organizer> organizerArrayList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            organizerArrayList.add(generateOrganizer());
        }
        return organizerArrayList;
    }
}

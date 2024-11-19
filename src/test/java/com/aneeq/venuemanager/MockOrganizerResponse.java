package com.aneeq.venuemanager;

import com.aneeq.venuemanager.dto.model.response.OrganizerResponse;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class MockOrganizerResponse {
    private static final Faker FAKER = new Faker();

    public static OrganizerResponse generateOrganizerResponse() {
        OrganizerResponse organizerResponse = new OrganizerResponse();

        organizerResponse.setId(FAKER.random().nextInt(1, 100000));
        organizerResponse.setName(FAKER.ancient().hero());
        return organizerResponse;
    }

    public static List<OrganizerResponse> generateOrganizerResponseList(int num) {
        ArrayList<OrganizerResponse> organizerArrayList = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            organizerArrayList.add(generateOrganizerResponse());
        }
        return organizerArrayList;
    }
}

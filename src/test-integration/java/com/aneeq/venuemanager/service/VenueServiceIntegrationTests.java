package com.aneeq.venuemanager.service;

import com.aneeq.venuemanager.MockVenueRequest;
import com.aneeq.venuemanager.dto.model.request.VenueRequest;
import com.aneeq.venuemanager.entity.Venue;
import com.aneeq.venuemanager.repository.VenueRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class VenueServiceIntegrationTests {

    @Autowired
    VenueService venueService;

    @Autowired
    VenueRepository venueRepository;

    @Test
    void testSaveVenue(){
        VenueRequest venueRequest = MockVenueRequest.generateVenueRequest();
        venueService.saveVenue(venueRequest);
        List<Venue> venues = venueRepository.findAll();

        assertVenueObject(venues.get(0),venueRequest);
    }


    private void assertVenueObject(Venue venue, VenueRequest venueRequest){
        assertEquals(venue.getName(), venueRequest.getName());
        assertEquals(venue.getLocation(), venueRequest.getLocation());
    }
}

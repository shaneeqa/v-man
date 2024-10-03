package com.aneeq.venuemanager.service;

import com.aneeq.venuemanager.MockVenue;
import com.aneeq.venuemanager.MockVenueRequest;
import com.aneeq.venuemanager.dto.model.request.VenueRequest;
import com.aneeq.venuemanager.dto.model.response.VenueResponse;
import com.aneeq.venuemanager.entity.Venue;
import com.aneeq.venuemanager.exception.VenueNotFoundException;
import com.aneeq.venuemanager.repository.VenueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class VenueServiceIntegrationTests {

    @Autowired
    VenueService venueService;

    @Autowired
    VenueRepository venueRepository;

    @BeforeEach
    void cleanup() {
        venueRepository.deleteAll();
    }

    @Test
    void testSaveVenue() {
        VenueRequest venueRequest = MockVenueRequest.generateVenueRequest();
        venueService.saveVenue(venueRequest);
        List<Venue> venues = venueRepository.findAll();

        assertVenueRequestObject(venues.get(0), venueRequest);
    }

    @Test
    void testGetAllVenues() {
        List<Venue> venues = MockVenue.generateVenueList(3);
        venueRepository.saveAll(venues);
        List<VenueResponse> venueResponses = venueService.getAllVenues();

        assertEquals(3, venueResponses.size());
        assertVenueResponseList(venues, venueResponses);

    }


    @Test
    void testGetVenueById() throws VenueNotFoundException {
        Venue venue = venueRepository.save(MockVenue.generateVenue());
        VenueResponse venueById = venueService.getVenueById(venue.getId());

        assertNotNull(venueById);
        assertVenueResponseObject(venue, venueById);
    }


    private void assertVenueRequestObject(Venue venue, VenueRequest venueRequest) {
        assertEquals(venue.getName(), venueRequest.getName());
        assertEquals(venue.getLocation(), venueRequest.getLocation());
    }

    private void assertVenueResponseObject(Venue venue, VenueResponse venueResponse) {
//        assertEquals(venue.getId(), venueResponse.getId());
        assertEquals(venue.getName(), venueResponse.getName());
        assertEquals(venue.getLocation(), venueResponse.getLocation());
    }

    private void assertVenueResponseList(List<Venue> venueList, List<VenueResponse> venueResponseList) {
        for (int i = 0; i < venueList.size(); i++) {
            assertVenueResponseObject(venueList.get(i), venueResponseList.get(i));
        }
    }
}

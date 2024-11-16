package com.aneeq.venuemanager.dto.mapper.request;

import com.aneeq.venuemanager.MockVenue;
import com.aneeq.venuemanager.MockVenueRequest;
import com.aneeq.venuemanager.dto.model.request.VenueRequest;
import com.aneeq.venuemanager.entity.Venue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VenueRequestMapperTests {

    @InjectMocks
    VenueRequestMapperImpl venueRequestMapper;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testVenueRequestToVenue(){
        VenueRequest venueRequest = MockVenueRequest.generateVenueRequest();
        Venue venue = venueRequestMapper.venueRequestToVenue(venueRequest);
        assertVenueObject(venueRequest, venue);
    }

    @Test
    void testVenueRequestToVenue_updateVenue(){
        Venue venue = MockVenue.generateVenue();
        VenueRequest venueRequest = MockVenueRequest.generateVenueRequest();
        venueRequestMapper.venueRequestToVenue(venue, venueRequest);

        assertVenueObject(venueRequest, venue);
    }

    private void assertVenueObject(VenueRequest venueRequest, Venue venue) {
        assertEquals(venueRequest.getName(), venue.getName());
        assertEquals(venueRequest.getLocation(), venue.getLocation());
    }

}

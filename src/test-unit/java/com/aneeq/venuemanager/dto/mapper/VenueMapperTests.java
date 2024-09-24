package com.aneeq.venuemanager.dto.mapper;

import com.aneeq.venuemanager.MockVenueRequest;
import com.aneeq.venuemanager.dto.model.request.VenueRequest;
import com.aneeq.venuemanager.entity.Venue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VenueMapperTests {

    @InjectMocks
    VenueMapperImpl venueMapper;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testVenueRequestToVenue(){
        VenueRequest venueRequest = MockVenueRequest.generateVenueRequest();
        Venue venue = venueMapper.venueRequestToVenue(venueRequest);
        assertEquals(venue.getName(),venueRequest.getName());
    }
}

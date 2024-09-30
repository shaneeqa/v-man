package com.aneeq.venuemanager.dto.mapper.response;

import com.aneeq.venuemanager.MockVenue;
import com.aneeq.venuemanager.dto.model.response.VenueResponse;
import com.aneeq.venuemanager.entity.Venue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VenueResponseMapperTests {

    @InjectMocks
    VenueResponseMapperImpl venueResponseMapper;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testVenuesToVenueResponses(){
        List<Venue> venues = MockVenue.generateVenueList(5);
        List<VenueResponse> venueResponses = venueResponseMapper.venuesToVenueResponses(venues);

        assertEquals(venues.get(3).getId(), venueResponses.get(3).getId());
        assertEquals(venues.get(1).getName(), venueResponses.get(1).getName());
    }
}

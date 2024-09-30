package com.aneeq.venuemanager.service;

import com.aneeq.venuemanager.MockVenue;
import com.aneeq.venuemanager.MockVenueRequest;
import com.aneeq.venuemanager.dto.mapper.request.VenueRequestMapperImpl;
import com.aneeq.venuemanager.dto.mapper.response.VenueResponseMapperImpl;
import com.aneeq.venuemanager.dto.model.request.VenueRequest;
import com.aneeq.venuemanager.dto.model.response.VenueResponse;
import com.aneeq.venuemanager.entity.Venue;
import com.aneeq.venuemanager.repository.VenueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VenueServiceTests {

    @InjectMocks
    VenueService venueService;

    @Mock
    VenueRepository venueRepository;

    @Mock
    VenueRequestMapperImpl venueRequestMapper;

    @Spy
    VenueResponseMapperImpl venueResponseMapper;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveVenue() {
        VenueRequest venueRequest = MockVenueRequest.generateVenueRequest();
        venueService.saveVenue(venueRequest);

        verify(venueRepository, times(1)).save(venueRequestMapper.venueRequestToVenue(venueRequest));
    }

    @Test
    void testGetAllVenues(){
        List<Venue> venues = MockVenue.generateVenueList(2);
        when(venueRepository.findAll()).thenReturn(venues);

        List<VenueResponse> venueResponses = venueService.getAllVenues();

        verify(venueRepository, times(1)).findAll();
        assertEquals(2, venueResponses.size());

        for(int i=0;i<venueResponses.size();i++){
            assertVenueObject(venueResponses.get(i),venues.get(i));
        }
    }

    private void assertVenueObject(VenueResponse venueResponse, Venue venue){
        assertEquals(venueResponse.getId(), venue.getId());
        assertEquals(venueResponse.getName(), venue.getName());
        assertEquals(venueResponse.getLocation(), venue.getLocation());
    }
}

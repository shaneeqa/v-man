package com.aneeq.venuemanager.service;

import com.aneeq.venuemanager.MockVenue;
import com.aneeq.venuemanager.MockVenueRequest;
import com.aneeq.venuemanager.dto.mapper.request.VenueRequestMapperImpl;
import com.aneeq.venuemanager.dto.mapper.response.VenueResponseMapperImpl;
import com.aneeq.venuemanager.dto.model.request.VenueRequest;
import com.aneeq.venuemanager.dto.model.response.VenueResponse;
import com.aneeq.venuemanager.entity.Venue;
import com.aneeq.venuemanager.exception.VenueNotFoundException;
import com.aneeq.venuemanager.repository.VenueRepository;
import com.aneeq.venuemanager.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveVenue() {
        VenueRequest venueRequest = MockVenueRequest.generateVenueRequest();
        venueService.saveVenue(venueRequest);

        verify(venueRepository, times(1)).save(venueRequestMapper.venueRequestToVenue(venueRequest));
    }

    @Test
    void testGetAllVenues() {
        List<Venue> venues = MockVenue.generateVenueList(2);
        when(venueRepository.findAll()).thenReturn(venues);

        List<VenueResponse> venueResponses = venueService.getAllVenues();

        verify(venueRepository, times(1)).findAll();
        assertEquals(2, venueResponses.size());

        for (int i = 0; i < venueResponses.size(); i++) {
            assertVenueObject(venueResponses.get(i), venues.get(i));
        }
    }

    @Test
    void testGetAllVenues_isEmpty(){
        when(venueRepository.findAll()).thenReturn(Collections.emptyList());
        assertTrue(venueService.getAllVenues().isEmpty());
    }

    @Test
    void testGetVenueById() throws VenueNotFoundException {
        Venue venue = MockVenue.generateVenue();
        when(venueRepository.findById(anyInt())).thenReturn(Optional.of(venue));

        VenueResponse venueResponse = venueService.getVenueById(1);

        verify(venueRepository, times(1)).findById(1);
        verify(venueResponseMapper, times(1)).venueToVenueResponse(venue);
        assertNotNull(venueResponse);
        assertVenueObject(venueResponse, venue);
    }

    @Test
    void testGetVenueById_VenueNotFound() {
        when(venueRepository.findById(anyInt())).thenReturn(Optional.empty());
        VenueNotFoundException venueNotFoundException = assertThrows(VenueNotFoundException.class,
                () -> venueService.getVenueById(1));
        assertEquals(Util.VENUE_NOT_FOUND_EXCEPTION_MSG, venueNotFoundException.getMessage());
    }

    @Test
    void testGetVenueByName() throws VenueNotFoundException {
        List<Venue> venues = MockVenue.generateVenueList(3);
        when(venueRepository.findByNameIgnoreCaseContaining(anyString())).thenReturn(venues);

        List<VenueResponse> venueResponses = venueService.getVenueByName(anyString());

        verify(venueRepository, times(1)).findByNameIgnoreCaseContaining(anyString());
        verify(venueResponseMapper, times(1)).venuesToVenueResponses(venues);

        for (int i = 0; i < venueResponses.size(); i++) {
            assertVenueObject(venueResponses.get(i), venues.get(i));
        }
    }

    @Test
    void testGetVenueByName_VenueNotFound() {
        when(venueRepository.findByNameIgnoreCaseContaining(anyString())).thenReturn(Collections.emptyList());
        VenueNotFoundException venueNotFoundException = assertThrows(VenueNotFoundException.class, () -> venueService.getVenueByName("SS"));
        assertEquals(Util.VENUE_NOT_FOUND_EXCEPTION_MSG, venueNotFoundException.getMessage());
    }

    @Test
    void testDeleteVenueById() throws VenueNotFoundException {
        //assume
        Venue venue = new Venue();
        when(venueRepository.findById(2)).thenReturn(Optional.of(venue));
        doNothing().when(venueRepository).deleteById(2);
        //act
        venueService.deleteVenueById(2);
        //assert
        verify(venueRepository, times(1)).findById(2);
        verify(venueRepository, times(1)).deleteById(2);
    }

    @Test
    void testDeleteById_VenueNotFound() {
        //assume
        when(venueRepository.findById(2)).thenReturn(Optional.empty());

        //act
        VenueNotFoundException venueNotFoundException = assertThrows(VenueNotFoundException.class, () -> venueService.deleteVenueById(2));

        //assert
        verify(venueRepository, times(1)).findById(2);
        verify(venueRepository, never()).deleteById(2);
        assertEquals(Util.VENUE_NOT_FOUND_EXCEPTION_MSG, venueNotFoundException.getMessage());
    }


    @Test
    void testUpdateVenueById() throws VenueNotFoundException {
        Venue venue = new Venue();
        when(venueRepository.findById(2)).thenReturn(Optional.of(venue));
        venueService.updateVenueById(2, MockVenueRequest.generateVenueRequest());

        verify(venueRepository, times(1)).save(venue);
    }

    @Test
    void testUpdateVenueById_VenueNotFound() {
        when(venueRepository.findById(2)).thenReturn(Optional.empty());
        VenueNotFoundException venueNotFoundException = assertThrows(VenueNotFoundException.class,
                () -> venueService.updateVenueById(2, MockVenueRequest.generateVenueRequest()));

        verify(venueRepository, times(1)).findById(2);
        verify(venueRepository, never()).save(any());
        assertEquals(Util.VENUE_NOT_FOUND_EXCEPTION_MSG, venueNotFoundException.getMessage());
    }

    private void assertVenueObject(VenueResponse venueResponse, Venue venue) {
        assertEquals(venueResponse.getId(), venue.getId());
        assertEquals(venueResponse.getName(), venue.getName());
        assertEquals(venueResponse.getLocation(), venue.getLocation());
    }
}

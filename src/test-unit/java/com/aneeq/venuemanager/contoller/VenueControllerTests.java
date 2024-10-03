package com.aneeq.venuemanager.contoller;

import com.aneeq.venuemanager.MockVenueRequest;
import com.aneeq.venuemanager.MockVenueResponse;
import com.aneeq.venuemanager.controller.VenueController;
import com.aneeq.venuemanager.dto.model.request.VenueRequest;
import com.aneeq.venuemanager.dto.model.response.VenueResponse;
import com.aneeq.venuemanager.exception.VenueNotFoundException;
import com.aneeq.venuemanager.service.VenueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class VenueControllerTests {

    @InjectMocks
    private VenueController venueController;
    @Mock
    private VenueService venueService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateVenue() {
        VenueRequest venueRequest = MockVenueRequest.generateVenueRequest();
        ResponseEntity<VenueRequest> response = venueController.createVenue(venueRequest);

        verify(venueService).saveVenue(venueRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testViewAllVenues() {
        List<VenueResponse> venueResponses = MockVenueResponse.generateVenueResponseList(2);
        when(venueService.getAllVenues()).thenReturn(venueResponses);
        ResponseEntity<List<VenueResponse>> venueResponseList = venueController.viewAllVenues();

        assertEquals(HttpStatus.OK, venueResponseList.getStatusCode());
        assertEquals(venueResponses, venueResponseList.getBody());
    }

    @Test
    void testViewVenueById() throws VenueNotFoundException {
        VenueResponse venueResponse = MockVenueResponse.generateVenueResponse();
        when(venueService.getVenueById(1)).thenReturn(venueResponse);
        ResponseEntity<VenueResponse> response = venueController.viewVenueById(1);

        assertEquals(venueResponse, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testViewVenueById_VenueNotFoundException() throws VenueNotFoundException {
        when(venueService.getVenueById(anyInt())).thenThrow(VenueNotFoundException.class);
        ResponseEntity<VenueResponse> response = venueController.viewVenueById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testViewVenueById_CommonException() throws Exception {
        when(venueService.getVenueById(anyInt())).thenThrow(NullPointerException.class);
        ResponseEntity<VenueResponse> response = venueController.viewVenueById(1);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}


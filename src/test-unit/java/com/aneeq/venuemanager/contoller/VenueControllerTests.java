package com.aneeq.venuemanager.contoller;

import com.aneeq.venuemanager.MockVenueRequest;
import com.aneeq.venuemanager.controller.VenueController;
import com.aneeq.venuemanager.dto.model.request.VenueRequest;
import com.aneeq.venuemanager.service.VenueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

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
}

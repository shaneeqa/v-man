package com.aneeq.venuemanager.service;

import com.aneeq.venuemanager.MockVenueRequest;
import com.aneeq.venuemanager.dto.mapper.VenueMapperImpl;
import com.aneeq.venuemanager.dto.model.request.VenueRequest;
import com.aneeq.venuemanager.repository.VenueRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class VenueServiceTests {

    @InjectMocks
    VenueService venueService;

    @Mock
    VenueRepository venueRepository;

    @Mock
    VenueMapperImpl venueMapper;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testSaveVenue() {
        VenueRequest venueRequest = MockVenueRequest.generateVenueRequest();
        venueService.saveVenue(venueRequest);

        verify(venueRepository, times(1)).save(venueMapper.venueRequestToVenue(venueRequest));

    }
}

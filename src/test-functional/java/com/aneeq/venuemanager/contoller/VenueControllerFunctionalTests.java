package com.aneeq.venuemanager.contoller;

import com.aneeq.venuemanager.MockVenueRequest;
import com.aneeq.venuemanager.dto.model.request.VenueRequest;
import com.aneeq.venuemanager.repository.VenueRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VenueControllerFunctionalTests {
    @Autowired
    VenueRepository venueRepository;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void testCreateVenue() {
        VenueRequest venueRequest = MockVenueRequest.generateVenueRequest();
        ResponseEntity<Void> voidResponseEntity = testRestTemplate.postForEntity("/venues", venueRequest, Void.class);
        assertEquals(HttpStatus.CREATED, voidResponseEntity.getStatusCode());

    }
}

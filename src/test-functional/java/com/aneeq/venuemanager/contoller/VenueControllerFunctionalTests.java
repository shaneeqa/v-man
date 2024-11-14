package com.aneeq.venuemanager.contoller;

import com.aneeq.venuemanager.MockVenue;
import com.aneeq.venuemanager.MockVenueRequest;
import com.aneeq.venuemanager.dto.model.request.VenueRequest;
import com.aneeq.venuemanager.dto.model.response.VenueResponse;
import com.aneeq.venuemanager.entity.Venue;
import com.aneeq.venuemanager.repository.VenueRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

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

    @Test
    void testViewAllVenues() {
        ResponseEntity<VenueResponse[]> venueResponses = testRestTemplate.getForEntity("/venues", VenueResponse[].class);
        assertEquals(HttpStatus.OK, venueResponses.getStatusCode());
    }

    @Test
    void testViewVenueById() {
        Venue venue = venueRepository.save(MockVenue.generateVenue());
        ResponseEntity<VenueResponse> venueResponse = testRestTemplate.getForEntity("/venues/{id}", VenueResponse.class, venue.getId());
        assertEquals(HttpStatus.OK, venueResponse.getStatusCode());
    }

    @Test
    void testViewVenueByName() {
        String searchString = "audit";
        venueRepository.saveAll(MockVenue.generateAuditoriumList(2));
        ResponseEntity<VenueResponse[]> venueResponses = testRestTemplate.getForEntity("/venues/search?name=" + searchString, VenueResponse[].class);
        assertEquals(HttpStatus.OK, venueResponses.getStatusCode());
    }

    @Test
    void testDeleteVenueById() {
        Venue venue = venueRepository.save(MockVenue.generateVenue());
        ResponseEntity<VenueResponse> response = testRestTemplate.exchange(
                "/venues/{id}",
                HttpMethod.DELETE,
                new HttpEntity<>(venue),
                VenueResponse.class,
                venue.getId()
        );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testUpdateVenueById(){
        Venue venue = venueRepository.save(MockVenue.generateVenue());
        ResponseEntity<VenueResponse> response = testRestTemplate.exchange(
                "/venues/{id}",
                HttpMethod.PUT,
                new HttpEntity<>(venue),
                VenueResponse.class,
                venue.getId()
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}

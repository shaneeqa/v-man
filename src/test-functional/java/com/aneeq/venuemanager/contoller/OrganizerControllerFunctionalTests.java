package com.aneeq.venuemanager.contoller;

import com.aneeq.venuemanager.MockOrganizer;
import com.aneeq.venuemanager.MockOrganizerRequest;
import com.aneeq.venuemanager.dto.model.request.OrganizerRequest;
import com.aneeq.venuemanager.dto.model.response.OrganizerResponse;
import com.aneeq.venuemanager.entity.Organizer;
import com.aneeq.venuemanager.repository.OrganizerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrganizerControllerFunctionalTests {

    @Autowired
    OrganizerRepository organizerRepository;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void testCreateOrganizer() {
        OrganizerRequest organizerRequest = MockOrganizerRequest.generateOrganizerRequest();
        ResponseEntity<Void> response = testRestTemplate.postForEntity("/organizers", organizerRequest, Void.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testViewAllOrganizers() {
        ResponseEntity<OrganizerResponse[]> organizerResponses = testRestTemplate.getForEntity("/organizers", OrganizerResponse[].class);
        assertEquals(HttpStatus.OK, organizerResponses.getStatusCode());
    }

    @Test
    void testViewOrganizerById() {

        Organizer organizer = organizerRepository.save(MockOrganizer.generateOrganizer());
        ResponseEntity<OrganizerResponse> organizerResponse = testRestTemplate.getForEntity("/organizers/{id}", OrganizerResponse.class, organizer.getId());
        assertEquals(HttpStatus.OK, organizerResponse.getStatusCode());
    }
}

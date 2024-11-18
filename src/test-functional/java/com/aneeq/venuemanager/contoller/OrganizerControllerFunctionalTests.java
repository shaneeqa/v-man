package com.aneeq.venuemanager.contoller;

import com.aneeq.venuemanager.MockOrganizerRequest;
import com.aneeq.venuemanager.dto.model.request.OrganizerRequest;
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
    TestRestTemplate testRestTemplate;

    @Test
    void testCreateOrganizer() {
        OrganizerRequest organizerRequest = MockOrganizerRequest.generateOrganizerRequest();
        ResponseEntity<Void> response = testRestTemplate.postForEntity("/organizers", organizerRequest, Void.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}

package com.aneeq.venuemanager.contoller;

import com.aneeq.venuemanager.MockOrganizerRequest;
import com.aneeq.venuemanager.MockOrganizerResponse;
import com.aneeq.venuemanager.controller.OrganizerController;
import com.aneeq.venuemanager.dto.model.request.OrganizerRequest;
import com.aneeq.venuemanager.dto.model.response.OrganizerResponse;
import com.aneeq.venuemanager.service.OrganizerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrganizerControllerTests {

    @InjectMocks
    private OrganizerController organizerController;

    @Mock
    private OrganizerService organizerService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrganizer() {
        OrganizerRequest organizerRequest = MockOrganizerRequest.generateOrganizerRequest();
        ResponseEntity<OrganizerResponse> response = organizerController.createOrganizer(organizerRequest);

        verify(organizerService).saveOrganizer(organizerRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testViewAllOrganizers(){
        List<OrganizerResponse> organizerResponses = MockOrganizerResponse.generateOrganizerResponseList(5);
        when(organizerService.getAllOrganizers()).thenReturn(organizerResponses);
        ResponseEntity<List<OrganizerResponse>> organizerResponseList = organizerController.viewAllOrganizers();

        assertEquals(HttpStatus.OK, organizerResponseList.getStatusCode());
        assertEquals(organizerResponses, organizerResponseList.getBody());
    }
}

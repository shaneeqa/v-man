package com.aneeq.venuemanager.contoller;

import com.aneeq.venuemanager.MockOrganizerRequest;
import com.aneeq.venuemanager.MockOrganizerResponse;
import com.aneeq.venuemanager.controller.OrganizerController;
import com.aneeq.venuemanager.dto.model.request.OrganizerRequest;
import com.aneeq.venuemanager.dto.model.response.OrganizerResponse;
import com.aneeq.venuemanager.exception.OrganizerNotFoundException;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

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

    @Test
    void testViewOrganizerById() throws OrganizerNotFoundException {
        OrganizerResponse organizerResponse = MockOrganizerResponse.generateOrganizerResponse();
        when(organizerService.getOrganizerById(2)).thenReturn(organizerResponse);
        ResponseEntity<OrganizerResponse> response = organizerController.viewOrganizerById(2);

        assertEquals(organizerResponse,response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testViewOrganizerById_OrganizerNotFound() throws OrganizerNotFoundException {
        when(organizerService.getOrganizerById(anyInt())).thenThrow(OrganizerNotFoundException.class);
        ResponseEntity<OrganizerResponse> response = organizerController.viewOrganizerById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testViewOrganizerById_CommonException() throws OrganizerNotFoundException {
        when(organizerService.getOrganizerById(anyInt())).thenThrow(NullPointerException.class);
        ResponseEntity<OrganizerResponse> response = organizerController.viewOrganizerById(1);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testUpdateOrganizerById() throws OrganizerNotFoundException {
        ResponseEntity<OrganizerResponse> response = organizerController.updateOrganizerById(anyInt(), any());
        verify(organizerService, times(1)).updateOrganizerById(anyInt(), any());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}

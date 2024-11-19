package com.aneeq.venuemanager.service;

import com.aneeq.venuemanager.MockOrganizer;
import com.aneeq.venuemanager.MockOrganizerRequest;
import com.aneeq.venuemanager.dto.mapper.request.OrganizerRequestMapperImpl;
import com.aneeq.venuemanager.dto.mapper.response.OrganizerResponseMapperImpl;
import com.aneeq.venuemanager.dto.model.request.OrganizerRequest;
import com.aneeq.venuemanager.dto.model.response.OrganizerResponse;
import com.aneeq.venuemanager.entity.Organizer;
import com.aneeq.venuemanager.repository.OrganizerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class OrganizerServiceTests {

    @InjectMocks
    private OrganizerService organizerService;

    @Mock
    private OrganizerRepository organizerRepository;

    @Mock
    private OrganizerRequestMapperImpl organizerRequestMapper;

    @Spy
    private OrganizerResponseMapperImpl organizerResponseMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveOrganizer() {
        OrganizerRequest organizerRequest = MockOrganizerRequest.generateOrganizerRequest();
        organizerService.saveOrganizer(organizerRequest);
        verify(organizerRepository, times(1)).save(organizerRequestMapper.organizerRequestToOrganizer(organizerRequest));
    }

    @Test
    void testGetAllOrganizers() {
        List<Organizer> organizers = MockOrganizer.generateOrganizerList(3);
        when(organizerRepository.findAll()).thenReturn(organizers);

        List<OrganizerResponse> allOrganizers = organizerService.getAllOrganizers();

        verify(organizerRepository, times(1)).findAll();
        assertEquals(3, allOrganizers.size());

        for(int i=0;i<allOrganizers.size(); i++){
            assertOrganizerObject(allOrganizers.get(i), organizers.get(i));
        }
    }

    @Test
    void testGetAllOrganizers_isEmpty(){
        when(organizerRepository.findAll()).thenReturn(Collections.emptyList());
        assertTrue(organizerService.getAllOrganizers().isEmpty());
    }

    private void assertOrganizerObject(OrganizerResponse organizerResponse, Organizer organizer) {
        assertEquals(organizerResponse.getId(), organizer.getId());
        assertEquals(organizerResponse.getName(), organizer.getName());
    }
}

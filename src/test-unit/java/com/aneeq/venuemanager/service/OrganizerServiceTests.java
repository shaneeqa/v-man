package com.aneeq.venuemanager.service;

import com.aneeq.venuemanager.MockOrganizer;
import com.aneeq.venuemanager.MockOrganizerRequest;
import com.aneeq.venuemanager.dto.mapper.request.OrganizerRequestMapperImpl;
import com.aneeq.venuemanager.dto.mapper.response.OrganizerResponseMapperImpl;
import com.aneeq.venuemanager.dto.model.request.OrganizerRequest;
import com.aneeq.venuemanager.dto.model.response.OrganizerResponse;
import com.aneeq.venuemanager.entity.Organizer;
import com.aneeq.venuemanager.exception.OrganizerNotFoundException;
import com.aneeq.venuemanager.repository.OrganizerRepository;
import com.aneeq.venuemanager.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

        for (int i = 0; i < allOrganizers.size(); i++) {
            assertOrganizerObject(allOrganizers.get(i), organizers.get(i));
        }
    }

    @Test
    void testGetAllOrganizers_isEmpty() {
        when(organizerRepository.findAll()).thenReturn(Collections.emptyList());
        assertTrue(organizerService.getAllOrganizers().isEmpty());
    }

    @Test
    void testGetOrganizerById() throws OrganizerNotFoundException {
        Organizer organizer = MockOrganizer.generateOrganizer();
        when(organizerRepository.findById(2)).thenReturn(Optional.of(organizer));

        OrganizerResponse organizerResponse = organizerService.getOrganizerById(2);

        verify(organizerRepository, times(1)).findById(2);
        verify(organizerResponseMapper, times(1)).organizerToOrganizerResponse(organizer);
        assertNotNull(organizerResponse);
        assertOrganizerObject(organizerResponse, organizer);
    }

    @Test
    void testGetOrganizerById_OrganizerNotFound() {
        when(organizerRepository.findById(3)).thenReturn(Optional.empty());
        OrganizerNotFoundException organizerNotFoundException = assertThrows(OrganizerNotFoundException.class, () -> organizerService.getOrganizerById(3));
        assertEquals(Util.ORGANIZER_NOT_FOUND_EXCEPTION_MSG, organizerNotFoundException.getMessage());
    }

    @Test
    void testUpdateOrganizerById() throws OrganizerNotFoundException {
        Organizer organizer = MockOrganizer.generateOrganizer();
        when(organizerRepository.findById(5)).thenReturn(Optional.of(organizer));
        organizerService.updateOrganizerById(5, MockOrganizerRequest.generateOrganizerRequest());

        verify(organizerRepository, times(1)).save(organizer);
    }

    @Test
    void testUpdateOrganizerById_OrganizerNotFound() {
        when(organizerRepository.findById(2)).thenReturn(Optional.empty());
        OrganizerNotFoundException organizerNotFoundException = assertThrows(OrganizerNotFoundException.class,
                () -> organizerService.updateOrganizerById(5, MockOrganizerRequest.generateOrganizerRequest()));

        verify(organizerRepository, times(1)).findById(2);
        verify(organizerRepository, never()).save(any());
        assertEquals(Util.ORGANIZER_NOT_FOUND_EXCEPTION_MSG, organizerNotFoundException.getMessage());
    }

    @Test
    void testDeleteOrganizerById() throws OrganizerNotFoundException {
        Organizer organizer = new Organizer();
        when(organizerRepository.findById(6)).thenReturn(Optional.of(organizer));
        doNothing().when(organizerRepository).deleteById(6);

        organizerService.deleteOrganizerById(6);

        verify(organizerRepository, times(1)).findById(6);
        verify(organizerRepository, times(1)).deleteById(6);
    }

    @Test
    void testDeleteOrganizerById_OrganizerNotFound() {
        when(organizerRepository.findById(2)).thenReturn(Optional.empty());
        OrganizerNotFoundException organizerNotFoundException = assertThrows(OrganizerNotFoundException.class,
                () -> organizerService.deleteOrganizerById(2));

        verify(organizerRepository, times(1)).findById(2);
        verify(organizerRepository, never()).deleteById(2);
        assertEquals(Util.ORGANIZER_NOT_FOUND_EXCEPTION_MSG, organizerNotFoundException.getMessage());
    }

    private void assertOrganizerObject(OrganizerResponse organizerResponse, Organizer organizer) {
        assertEquals(organizerResponse.getId(), organizer.getId());
        assertEquals(organizerResponse.getName(), organizer.getName());
    }

}

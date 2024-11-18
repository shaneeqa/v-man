package com.aneeq.venuemanager.service;

import com.aneeq.venuemanager.MockOrganizerRequest;
import com.aneeq.venuemanager.dto.mapper.request.OrganizerRequestMapperImpl;
import com.aneeq.venuemanager.dto.model.request.OrganizerRequest;
import com.aneeq.venuemanager.repository.OrganizerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class OrganizerServiceTests {

    @InjectMocks
    private OrganizerService organizerService;

    @Mock
    private OrganizerRepository organizerRepository;

    @Mock
    private OrganizerRequestMapperImpl organizerRequestMapper;

    @BeforeEach
    void setup(){
    MockitoAnnotations.openMocks(this);
    }
    @Test
    void testSaveOrganizer() {
        OrganizerRequest organizerRequest = MockOrganizerRequest.generateOrganizerRequest();
        organizerService.saveOrganizer(organizerRequest);
        verify(organizerRepository, times(1)).save(organizerRequestMapper.organizerRequestToOrganizer(organizerRequest));
    }
}

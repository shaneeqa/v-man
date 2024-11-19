package com.aneeq.venuemanager.dto.mapper.request;

import com.aneeq.venuemanager.MockOrganizer;
import com.aneeq.venuemanager.MockOrganizerRequest;
import com.aneeq.venuemanager.dto.model.request.OrganizerRequest;
import com.aneeq.venuemanager.entity.Organizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrganizerRequestMapperTests {

    @InjectMocks
    OrganizerRequestMapperImpl organizerRequestMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testOrganizerRequestToOrganizer() {
        OrganizerRequest organizerRequest = MockOrganizerRequest.generateOrganizerRequest();
        Organizer organizer = organizerRequestMapper.organizerRequestToOrganizer(organizerRequest);
        assertEquals(organizerRequest.getName(), organizer.getName());
    }

    @Test
    void testOrganizerRequestToOrganizer_updateOrganizer() {
        Organizer organizer = MockOrganizer.generateOrganizer();
        OrganizerRequest organizerRequest = MockOrganizerRequest.generateOrganizerRequest();
        organizerRequestMapper.organizerRequestToOrganizer(organizer, organizerRequest);

        assertEquals(organizerRequest.getName(), organizer.getName());
    }
}

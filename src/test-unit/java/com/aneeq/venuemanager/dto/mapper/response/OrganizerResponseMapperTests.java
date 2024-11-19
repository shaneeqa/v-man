package com.aneeq.venuemanager.dto.mapper.response;

import com.aneeq.venuemanager.MockOrganizer;
import com.aneeq.venuemanager.dto.model.response.OrganizerResponse;
import com.aneeq.venuemanager.entity.Organizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrganizerResponseMapperTests {

    @InjectMocks
    OrganizerResponseMapperImpl organizerResponseMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testOrganizersToOrganizerResponses() {
        List<Organizer> organizers = MockOrganizer.generateOrganizerList(3);
        List<OrganizerResponse> organizerResponses = organizerResponseMapper.organizersToOrganizerResponses(organizers);

        assertEquals(organizers.get(2).getId(), organizerResponses.get(2).getId());
        assertEquals(organizers.get(0).getName(), organizerResponses.get(0).getName());
    }

}

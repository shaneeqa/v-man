package com.aneeq.venuemanager.service;

import com.aneeq.venuemanager.MockOrganizer;
import com.aneeq.venuemanager.MockOrganizerRequest;
import com.aneeq.venuemanager.dto.model.request.OrganizerRequest;
import com.aneeq.venuemanager.dto.model.response.OrganizerResponse;
import com.aneeq.venuemanager.entity.Organizer;
import com.aneeq.venuemanager.repository.OrganizerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OrganizerServiceIntegrationTests {

    @Autowired
    OrganizerService organizerService;

    @Autowired
    OrganizerRepository organizerRepository;

    @BeforeEach
    void cleanup() {
        organizerRepository.deleteAll();
    }

    @Test
    void testSaveOrganizer() {
        OrganizerRequest organizerRequest = MockOrganizerRequest.generateOrganizerRequest();
        organizerService.saveOrganizer(organizerRequest);
        List<Organizer> organizers = organizerRepository.findAll();

        assertEquals(organizers.get(0).getName(), organizerRequest.getName());
    }

    @Test
    void testGetAllOrganizers(){
        List<Organizer> organizers = MockOrganizer.generateOrganizerList(4);
        organizerRepository.saveAll(organizers);
        List<OrganizerResponse> organizerResponses = organizerService.getAllOrganizers();

        assertEquals(4,organizerResponses.size());

        for(int i =0; i<organizers.size();i++){
            assertEquals(organizers.get(i).getName(), organizerResponses.get(i).getName());
        }
    }

}

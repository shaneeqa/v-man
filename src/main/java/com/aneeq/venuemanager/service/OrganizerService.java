package com.aneeq.venuemanager.service;

import com.aneeq.venuemanager.dto.mapper.request.OrganizerRequestMapper;
import com.aneeq.venuemanager.dto.mapper.response.OrganizerResponseMapper;
import com.aneeq.venuemanager.dto.model.request.OrganizerRequest;
import com.aneeq.venuemanager.dto.model.response.OrganizerResponse;
import com.aneeq.venuemanager.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizerService {

    private OrganizerRepository organizerRepository;
    private OrganizerRequestMapper organizerRequestMapper;
    private OrganizerResponseMapper organizerResponseMapper;

    @Autowired
    public void setOrganizerRepository(OrganizerRepository organizerRepository) {
        this.organizerRepository = organizerRepository;
    }

    @Autowired
    public void setOrganizerRequestMapper(OrganizerRequestMapper organizerRequestMapper) {
        this.organizerRequestMapper = organizerRequestMapper;
    }

    @Autowired
    public void setOrganizerResponseMapper(OrganizerResponseMapper organizerResponseMapper) {
        this.organizerResponseMapper = organizerResponseMapper;
    }

    public void saveOrganizer(OrganizerRequest organizerRequest) {
        organizerRepository.save(organizerRequestMapper.organizerRequestToOrganizer(organizerRequest));

    }

    public List<OrganizerResponse> getAllOrganizers() {
        return organizerResponseMapper.organizersToOrganizerResponses(organizerRepository.findAll());
    }
}

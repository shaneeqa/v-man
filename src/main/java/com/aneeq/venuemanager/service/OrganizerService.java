package com.aneeq.venuemanager.service;

import com.aneeq.venuemanager.dto.mapper.request.OrganizerRequestMapper;
import com.aneeq.venuemanager.dto.model.request.OrganizerRequest;
import com.aneeq.venuemanager.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizerService {

    private OrganizerRepository organizerRepository;
    private OrganizerRequestMapper organizerRequestMapper;

    @Autowired
    public void setOrganizerRepository(OrganizerRepository organizerRepository) {
        this.organizerRepository = organizerRepository;
    }

    @Autowired
    public void setOrganizerRequestMapper(OrganizerRequestMapper organizerRequestMapper) {
        this.organizerRequestMapper = organizerRequestMapper;
    }

    public void saveOrganizer(OrganizerRequest organizerRequest) {
        organizerRepository.save(organizerRequestMapper.organizerRequestToOrganizer(organizerRequest));

    }
}

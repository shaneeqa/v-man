package com.aneeq.venue_manager.service;

import com.aneeq.venue_manager.dto.mapper.VenueMapper;
import com.aneeq.venue_manager.dto.model.request.VenueRequest;
import com.aneeq.venue_manager.entity.Venue;
import com.aneeq.venue_manager.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenueService {

    private VenueRepository venueRepository;
    private VenueMapper venueMapper;

    @Autowired
    public void setVenueRepository(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Autowired
    public void setVenueMapper(VenueMapper venueMapper) {
        this.venueMapper = venueMapper;
    }

    public Venue saveVenue(VenueRequest venueRequest) {
        return venueRepository.save(venueMapper.venueRequestToVenue(venueRequest));
    }

}

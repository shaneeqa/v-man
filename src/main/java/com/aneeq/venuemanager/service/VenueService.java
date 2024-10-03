package com.aneeq.venuemanager.service;

import com.aneeq.venuemanager.dto.mapper.request.VenueRequestMapper;
import com.aneeq.venuemanager.dto.mapper.response.VenueResponseMapper;
import com.aneeq.venuemanager.dto.model.request.VenueRequest;
import com.aneeq.venuemanager.dto.model.response.VenueResponse;
import com.aneeq.venuemanager.entity.Venue;
import com.aneeq.venuemanager.exception.VenueNotFoundException;
import com.aneeq.venuemanager.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenueService {

    private VenueRepository venueRepository;
    private VenueRequestMapper venueRequestMapper;
    private VenueResponseMapper venueResponseMapper;

    @Autowired
    public void setVenueRepository(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Autowired
    public void setVenueRequestMapper(VenueRequestMapper venueMapper) {
        this.venueRequestMapper = venueMapper;
    }

    @Autowired
    public void setVenueResponseMapper(VenueResponseMapper venueResponseMapper){
        this.venueResponseMapper = venueResponseMapper;
    }

    public Venue saveVenue(VenueRequest venueRequest) {
        return venueRepository.save(venueRequestMapper.venueRequestToVenue(venueRequest));
    }

    public List<VenueResponse> getAllVenues() {
        return venueResponseMapper.venuesToVenueResponses(venueRepository.findAll());
    }

    public VenueResponse getVenueById(Integer id) throws VenueNotFoundException {
        Optional<Venue> venue = venueRepository.findById(id);

        if(venue.isEmpty())
            throw new VenueNotFoundException();

        return venueResponseMapper.venueToVenueResponse(venue.get());
    }
}

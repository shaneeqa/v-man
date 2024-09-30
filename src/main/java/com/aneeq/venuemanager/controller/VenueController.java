package com.aneeq.venuemanager.controller;

import com.aneeq.venuemanager.dto.model.request.VenueRequest;
import com.aneeq.venuemanager.dto.model.response.VenueResponse;
import com.aneeq.venuemanager.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venues")
public class VenueController {

    private VenueService venueService;

    @Autowired
    public void setVenueService(VenueService venueService) {
        this.venueService = venueService;
    }

    @PostMapping
    public ResponseEntity<VenueRequest> createVenue(@RequestBody VenueRequest venueRequest) {
        venueService.saveVenue(venueRequest);
        return new ResponseEntity<>(venueRequest, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VenueResponse>> viewAllVenues(){
        venueService.getAllVenues();
        return new ResponseEntity<>(venueService.getAllVenues(), HttpStatus.OK);
    }
}

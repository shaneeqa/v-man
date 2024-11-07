package com.aneeq.venuemanager.controller;

import com.aneeq.venuemanager.dto.model.request.VenueRequest;
import com.aneeq.venuemanager.dto.model.response.VenueResponse;
import com.aneeq.venuemanager.exception.VenueNotFoundException;
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
    public ResponseEntity<List<VenueResponse>> viewAllVenues() {
        venueService.getAllVenues();
        return new ResponseEntity<>(venueService.getAllVenues(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenueResponse> viewVenueById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(venueService.getVenueById(id), HttpStatus.OK);
        } catch (VenueNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        /**
         * the following is for catching other unidentified exceptions
         */
        catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<VenueResponse>> viewVenueByName(@RequestParam String name) {
        try {
            return new ResponseEntity<>(venueService.getVenueByName(name), HttpStatus.OK);
        } catch (VenueNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

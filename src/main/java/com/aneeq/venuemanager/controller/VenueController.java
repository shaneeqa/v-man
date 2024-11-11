package com.aneeq.venuemanager.controller;

import com.aneeq.venuemanager.dto.model.request.VenueRequest;
import com.aneeq.venuemanager.dto.model.response.VenueResponse;
import com.aneeq.venuemanager.exception.VenueNotFoundException;
import com.aneeq.venuemanager.service.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venues")
@Tag(name = "Venue Controller", description = "Venue related endpoints")
public class VenueController {

    private VenueService venueService;

    @Autowired
    public void setVenueService(VenueService venueService) {
        this.venueService = venueService;
    }

    @Operation(summary = "Create a venue", description = "Creating a new venue date in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venue created successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid Request", content = @Content)
    })
    @PostMapping
    public ResponseEntity<VenueRequest> createVenue(@RequestBody VenueRequest venueRequest) {
        venueService.saveVenue(venueRequest);
        return new ResponseEntity<>(venueRequest, HttpStatus.CREATED);
    }

    @Operation(summary = "View all venues", description = "View all available venues stored in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all the venues", content = @Content),
            @ApiResponse(responseCode = "404", description = "Venues are not found", content = @Content)
    }
    )
    @GetMapping
    public ResponseEntity<List<VenueResponse>> viewAllVenues() {
        venueService.getAllVenues();
        return new ResponseEntity<>(venueService.getAllVenues(), HttpStatus.OK);
    }

    @Operation(summary = "View a venue through ID", description = "Find a venue using its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the relevant venue for the given ID", content = @Content),
            @ApiResponse(responseCode = "404", description = "Venue not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<VenueResponse> viewVenueById(@Parameter(
            description = "ID of the venue to be retrieved") @PathVariable Integer id) {
        try {
            return new ResponseEntity<>(venueService.getVenueById(id), HttpStatus.OK);
        } catch (VenueNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        /**
         * the following is for catching other unidentified exceptions
         */ catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "View venues through name", description = "Find venues that contain the specified name, case-insensitive")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found list of venues matching the search criteria", content = @Content),
            @ApiResponse(responseCode = "404", description = "Venue not found", content = @Content)
    })
    @GetMapping("/search")
    public ResponseEntity<List<VenueResponse>> viewVenueByName(
            @Parameter(description = "Partial or full name to search venue") @RequestParam String name) {
        try {
            return new ResponseEntity<>(venueService.getVenueByName(name), HttpStatus.OK);
        } catch (VenueNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a venue through ID", description = "Delete a venue by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Venue got deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Venue not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<VenueResponse> deleteVenueById(
            @Parameter(description = "ID of the venue to delete") @PathVariable Integer id)
            throws VenueNotFoundException {
        venueService.deleteVenueById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Update a venue through ID", description = "Update details of an existing venue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venue updated successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Venue not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<VenueResponse> updateVenueById(
            @Parameter(description = "ID of the venue to update") @PathVariable Integer id,
            @RequestBody VenueRequest venueRequest)
            throws VenueNotFoundException {
        venueService.updateVenueById(id, venueRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

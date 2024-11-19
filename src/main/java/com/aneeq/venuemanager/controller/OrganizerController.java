package com.aneeq.venuemanager.controller;

import com.aneeq.venuemanager.dto.model.request.OrganizerRequest;
import com.aneeq.venuemanager.dto.model.response.OrganizerResponse;
import com.aneeq.venuemanager.exception.OrganizerNotFoundException;
import com.aneeq.venuemanager.service.OrganizerService;
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
@RequestMapping("/organizers")
@Tag(name = "Organizer Controller", description = "Organizer related endpoints")
public class OrganizerController {

    private OrganizerService organizerService;

    @Autowired
    public void setOrganizerService(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @Operation(summary = "Create an organizer", description = "Creating a new organizer data in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Organizer created successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid Request", content = @Content)
    })
    @PostMapping
    public ResponseEntity<OrganizerResponse> createOrganizer(OrganizerRequest organizerRequest) {
        organizerService.saveOrganizer(organizerRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "View all organizers", description = "View all available organizers stored in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all the organizers", content = @Content),
            @ApiResponse(responseCode = "404", description = "Organizers are not found", content = @Content)
    }
    )
    @GetMapping
    public ResponseEntity<List<OrganizerResponse>> viewAllOrganizers() {
        organizerService.getAllOrganizers();
        return new ResponseEntity<>(organizerService.getAllOrganizers(), HttpStatus.OK);
    }

    @Operation(summary = "View a organizer through ID", description = "Find an organizer using its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the relevant authorizer for the given ID", content = @Content),
            @ApiResponse(responseCode = "404", description = "Organizer not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrganizerResponse> viewOrganizerById(@Parameter(
            description = "ID of the organizer to be retrieved") @PathVariable Integer id) {
        try {
            return new ResponseEntity<>(organizerService.getOrganizerById(id), HttpStatus.OK);
        } catch (OrganizerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Update an organizer through ID", description = "Update details of an existing organizer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Organizer updated successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Organizer not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<OrganizerResponse> updateOrganizerById(@Parameter(
            description = "ID of the authorizer to update") @PathVariable Integer id, @RequestBody OrganizerRequest organizerRequest)
            throws OrganizerNotFoundException {
        organizerService.updateOrganizerById(id, organizerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Delete an organizer through ID", description = "Delete an organizer by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Organizer got deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Organizer not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<OrganizerResponse> deleteOrganizerById(
            @Parameter(description = "ID of the venue to delete") @PathVariable Integer id)
            throws OrganizerNotFoundException {
        organizerService.deleteOrganizerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

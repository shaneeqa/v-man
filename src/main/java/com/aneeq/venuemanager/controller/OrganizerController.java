package com.aneeq.venuemanager.controller;

import com.aneeq.venuemanager.dto.model.request.OrganizerRequest;
import com.aneeq.venuemanager.dto.model.response.OrganizerResponse;
import com.aneeq.venuemanager.service.OrganizerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<OrganizerResponse> createOrganizer(OrganizerRequest organizerRequest){
        organizerService.saveOrganizer(organizerRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

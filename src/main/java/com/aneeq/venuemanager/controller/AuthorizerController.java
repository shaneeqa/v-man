package com.aneeq.venuemanager.controller;

import com.aneeq.venuemanager.dto.model.request.AuthorizerRequest;
import com.aneeq.venuemanager.dto.model.response.AuthorizerResponse;
import com.aneeq.venuemanager.exception.AuthorizerNotFoundException;
import com.aneeq.venuemanager.service.AuthorizerService;
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
@RequestMapping("/authorizers")
@Tag(name = "Authorizer Controller", description = "Authorizer related endpoints")
public class AuthorizerController {

    private AuthorizerService authorizerService;

    @Autowired
    public void setAuthorizerService(AuthorizerService authorizerService) {
        this.authorizerService = authorizerService;
    }

    @Operation(summary = "Create an authorizer", description = "Creating a new authorizer data in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Authorizer created successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid Request", content = @Content)
    })
    @PostMapping
    public ResponseEntity<AuthorizerRequest> createAuthorizer(@RequestBody AuthorizerRequest authorizerRequest) {
        authorizerService.saveAuthorizer(authorizerRequest);
        return new ResponseEntity<>(authorizerRequest, HttpStatus.CREATED);
    }

    @Operation(summary = "View all authorizers", description = "View all available authorizers stored in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all the authorizers", content = @Content),
            @ApiResponse(responseCode = "404", description = "Authorizers are not found", content = @Content)
    }
    )
    @GetMapping
    public ResponseEntity<List<AuthorizerResponse>> viewAllAuthorizers() {
        authorizerService.getAllAuthorizers();
        return new ResponseEntity<>(authorizerService.getAllAuthorizers(), HttpStatus.OK);
    }

    @Operation(summary = "View a authorizer through ID", description = "Find a venue using its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the relevant authorizer for the given ID", content = @Content),
            @ApiResponse(responseCode = "404", description = "authorizer not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AuthorizerResponse> viewAuthorizerById(@Parameter(
            description = "ID of the authorizer to be retrieved") @PathVariable Integer id) {
        try {
            return new ResponseEntity<>(authorizerService.getAuthorizerById(id), HttpStatus.OK);
        } catch (AuthorizerNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Update an authorizer through ID", description = "Update details of an existing authorizer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authorizer updated successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Authorizer not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<AuthorizerResponse> updateAuthorizerById(@Parameter(
            description = "ID of the authorizer to update")@PathVariable Integer id, @RequestBody AuthorizerRequest authorizerRequest) throws AuthorizerNotFoundException {
        authorizerService.updateAuthorizerById(id,authorizerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update an authroizer through ID", description = "Update details of an existing authorizer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authorizer updated successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Authorizer not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorizerResponse> deleteAuthorizerById(@Parameter(
            description = "ID of the authorizer to delete")@PathVariable Integer id)
            throws AuthorizerNotFoundException {

        authorizerService.deleteAuthorizerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

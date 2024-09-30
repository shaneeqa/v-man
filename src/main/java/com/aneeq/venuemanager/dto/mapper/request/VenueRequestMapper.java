package com.aneeq.venuemanager.dto.mapper.request;

import com.aneeq.venuemanager.dto.model.request.VenueRequest;
import com.aneeq.venuemanager.entity.Venue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class VenueRequestMapper {
    @Mapping(target = "id", ignore = true)
    public abstract Venue venueRequestToVenue(VenueRequest venueRequest);
}

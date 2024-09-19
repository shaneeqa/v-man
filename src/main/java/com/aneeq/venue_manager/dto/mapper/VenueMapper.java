package com.aneeq.venue_manager.dto.mapper;

import com.aneeq.venue_manager.dto.model.request.VenueRequest;
import com.aneeq.venue_manager.entity.Venue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class VenueMapper {
    @Mapping(target = "id", ignore = true)
    public abstract Venue venueRequestToVenue(VenueRequest venueRequest);
}

package com.aneeq.venuemanager.dto.mapper.response;

import com.aneeq.venuemanager.dto.model.response.VenueResponse;
import com.aneeq.venuemanager.entity.Venue;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class VenueResponseMapper {

    public abstract VenueResponse venueToVenueResponse(Venue venue);

    public abstract List<VenueResponse> venuesToVenueResponses(List<Venue> venue);
}

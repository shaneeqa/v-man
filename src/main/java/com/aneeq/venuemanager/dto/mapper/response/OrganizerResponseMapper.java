package com.aneeq.venuemanager.dto.mapper.response;

import com.aneeq.venuemanager.dto.model.response.OrganizerResponse;
import com.aneeq.venuemanager.entity.Organizer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrganizerResponseMapper {
    public abstract List<OrganizerResponse> organizersToOrganizerResponses(List<Organizer> organizers);
    public abstract OrganizerResponse organizerToOrganizerResponse(Organizer organizer);
}

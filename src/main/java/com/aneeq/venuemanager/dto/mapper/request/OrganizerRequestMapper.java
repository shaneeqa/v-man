package com.aneeq.venuemanager.dto.mapper.request;

import com.aneeq.venuemanager.dto.model.request.OrganizerRequest;
import com.aneeq.venuemanager.entity.Organizer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class OrganizerRequestMapper {
    @Mapping(target = "id", ignore = true)
    public abstract Organizer organizerRequestToOrganizer(OrganizerRequest organizerRequest);
}

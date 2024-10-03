package com.aneeq.venuemanager.exception;


import com.aneeq.venuemanager.util.Util;

public class VenueNotFoundException extends DataNotFoundException {
public String getMessage(){
return Util.VENUE_NOT_FOUND_EXCEPTION_MSG;
}
}

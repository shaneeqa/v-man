package com.aneeq.venuemanager.exception;

import com.aneeq.venuemanager.util.Util;

public class OrganizerNotFoundException extends DataNotFoundException {
    public String getMessage() {
        return Util.ORGANIZER_NOT_FOUND_EXCEPTION_MSG;
    }
}

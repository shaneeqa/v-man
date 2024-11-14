package com.aneeq.venuemanager.exception;

import com.aneeq.venuemanager.util.Util;

public class AuthorizerNotFoundException extends DataNotFoundException {
    public String getMessage() {
        return Util.AUTHORIZER_NOT_FOUND_EXCEPTION_MSG;
    }
}

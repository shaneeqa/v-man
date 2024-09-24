package com.aneeq.venuemanager.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusCodeEnum {
    CANCELLED,
    YET_TO_BE_APPROVED,
    BOOKED;
}

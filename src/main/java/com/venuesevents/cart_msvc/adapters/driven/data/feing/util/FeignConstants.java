package com.venuesevents.cart_msvc.adapters.driven.data.feing.util;

public class FeignConstants {
    private FeignConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String EVENT_SERVICE_NOT_FOUND_MESSAGE = "Event service not found";
    public static final String EVENT_SERVICE_UNAVAILABLE_MESSAGE = "Event service is currently unavailable.";
    public static final String TOO_MANY_REQUEST_EVENT_SERVICE_MESSAGE = "Event rate limit exceeded";
    public static final String EVENT_COMMUNICATION_FAILED_MESSAGE = "Failed to communicate with Event service";
}

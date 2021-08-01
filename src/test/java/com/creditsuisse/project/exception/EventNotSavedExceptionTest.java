package com.creditsuisse.project.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.creditsuisse.project.common.constants.AppConstants.EVENT_NOT_SAVED_EXCEPTION;

public class EventNotSavedExceptionTest {

    @Test
    public void testEventNotSavedException() {
        try {
            throwException();
        } catch (EventNotSavedException eventNotSavedException) {
            Assertions.assertEquals(eventNotSavedException.getMessage(), EVENT_NOT_SAVED_EXCEPTION);
        }
    }

    private void throwException() {
        throw new EventNotSavedException(EVENT_NOT_SAVED_EXCEPTION);
    }
}

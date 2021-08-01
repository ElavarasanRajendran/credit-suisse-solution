package com.creditsuisse.project.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.creditsuisse.project.common.constants.AppConstants.EVENT_CONVERSION_ERROR_MESSAGE;

public class EventProcessingExceptionTest {

    @Test
    public void testEventProcessingException() {
        try {
            throwException();
        } catch (EventProcessingException exception) {
            Assertions.assertEquals(exception.getMessage(), EVENT_CONVERSION_ERROR_MESSAGE);
        }
    }

    private void throwException() {
        throw new EventProcessingException(EVENT_CONVERSION_ERROR_MESSAGE);
    }
}

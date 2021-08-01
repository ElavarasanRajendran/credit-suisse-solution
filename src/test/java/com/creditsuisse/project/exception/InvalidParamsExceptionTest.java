package com.creditsuisse.project.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.creditsuisse.project.common.constants.AppConstants.INVALID_PARAMS_ERROR_MESSAGE;

public class InvalidParamsExceptionTest {

    @Test
    public void testInvalidParamsException() {
        try {
            throwException();
        } catch (InvalidParamsException invalidParamsException) {
            Assertions.assertEquals(invalidParamsException.getMessage(), INVALID_PARAMS_ERROR_MESSAGE);
        }
    }

    private void throwException() {
        throw new InvalidParamsException(INVALID_PARAMS_ERROR_MESSAGE);
    }
}

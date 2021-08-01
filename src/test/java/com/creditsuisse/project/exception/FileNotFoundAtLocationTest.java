package com.creditsuisse.project.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.creditsuisse.project.common.constants.AppConstants.FILE_NOT_FOUND_ERROR_MESSAGE;

public class FileNotFoundAtLocationTest {
    @Test
    public void testFileNotFoundException() {
        try {
            throwException();
        } catch (FileNotFoundAtLocation fileNotFoundAtLocation) {
            Assertions.assertEquals(fileNotFoundAtLocation.getMessage(), FILE_NOT_FOUND_ERROR_MESSAGE);
        }
    }

    private void throwException() {
        throw new FileNotFoundAtLocation(FILE_NOT_FOUND_ERROR_MESSAGE);
    }
}

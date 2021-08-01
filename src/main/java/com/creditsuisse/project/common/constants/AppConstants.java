package com.creditsuisse.project.common.constants;

public class AppConstants {
    private AppConstants() {
        // Prevent Instantiation
    }

    //Exception Messages
    public static final String INVALID_PARAMS_ERROR_MESSAGE = "Please provide the correct log file Path";
    public static final String FILE_NOT_FOUND_ERROR_MESSAGE = "File not found at the provided location. " +
            "Please check the location and try again";
    public static final String EVENT_CONVERSION_ERROR_MESSAGE = "There was  error mapping the event log line from the file: {}";
    public static final String EVENT_NOT_SAVED_EXCEPTION = "Unable to save data to database";


    // SQL Constants
    public static final String CREATE_TABLE_STATEMENT = "CREATE TABLE IF NOT EXISTS event (id VARCHAR(20)," +
            " duration INTEGER, type VARCHAR(50), host VARCHAR(50), alert BOOLEAN)";
}

package com.creditsuisse.project;

import com.creditsuisse.project.exception.InvalidParamsException;
import com.creditsuisse.project.service.EventFileProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import static com.creditsuisse.project.common.constants.AppConstants.INVALID_PARAMS_ERROR_MESSAGE;

@Service
@Slf4j
public class Client implements CommandLineRunner {

    private final EventFileProcessor eventFileProcessor;

    public Client(EventFileProcessor eventFileProcessor) {
        this.eventFileProcessor = eventFileProcessor;
    }


    @Override
    public void run(String... args) throws Exception {
        if (args.length != 1 || args[0].isEmpty()) {
            throw new InvalidParamsException(INVALID_PARAMS_ERROR_MESSAGE);
        }
        String fileLocation = args[0];
        log.info("The Location is {}", fileLocation);
        eventFileProcessor.processEventFileByLocation(fileLocation);
    }
}

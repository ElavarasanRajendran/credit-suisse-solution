package com.creditsuisse.project.service;

import com.creditsuisse.project.common.dto.Event;
import com.creditsuisse.project.common.dto.EventLog;
import com.creditsuisse.project.common.enums.EventState;
import com.creditsuisse.project.exception.EventNotSavedException;
import com.creditsuisse.project.exception.FileNotFoundAtLocation;
import com.creditsuisse.project.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.creditsuisse.project.common.constants.AppConstants.EVENT_NOT_SAVED_EXCEPTION;
import static com.creditsuisse.project.common.constants.AppConstants.FILE_NOT_FOUND_ERROR_MESSAGE;

@Service
@Slf4j
public class EventFileProcessor {

    private final JsonConvertor jsonConvertor;
    private final EventRepository eventRepository;

    private final Map<String, Event> eventMap = new HashMap<>();

    public EventFileProcessor(final JsonConvertor jsonConvertor, EventRepository eventRepository) {
        this.jsonConvertor = jsonConvertor;
        this.eventRepository = eventRepository;
    }

    public void processEventFileByLocation(String location) {
        File file = new File(location);
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            bufferedReader.lines().forEach( line -> {
                Optional<Event> event = this.jsonConvertor.convertJsonStringToEvent(line);
                event.ifPresent(this::addEventToMap);
            });
        }
        catch (IOException ioException) {
            log.info(ioException.toString());
            throw new FileNotFoundAtLocation(FILE_NOT_FOUND_ERROR_MESSAGE);
        }
    }

    private void addEventToMap(Event event) {
        log.info("The Event is: {}", event);
        if(!eventMap.containsKey(event.getId()) &&
                event.getState().equals(EventState.STARTED)) {
            eventMap.put(event.getId(), event);
        } else {
            EventLog eventLog = EventLog.newInstance(eventMap.get(event.getId()), event);
            boolean response = eventRepository.saveEvent(eventLog);
            log.info("The response from database is {}", response);
            if(!response) {
                throw new EventNotSavedException(EVENT_NOT_SAVED_EXCEPTION);
            }
        }
    }
}

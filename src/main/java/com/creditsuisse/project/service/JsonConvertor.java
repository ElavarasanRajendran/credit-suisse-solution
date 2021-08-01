package com.creditsuisse.project.service;

import com.creditsuisse.project.common.dto.Event;
import com.creditsuisse.project.exception.EventProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

import static com.creditsuisse.project.common.constants.AppConstants.EVENT_CONVERSION_ERROR_MESSAGE;

@Component
@Slf4j
public class JsonConvertor {

    private final ObjectMapper objectMapper;

    public JsonConvertor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Optional<Event> convertJsonStringToEvent(String jsonString) {
        try {
            return Optional.of(
                    objectMapper.readValue(jsonString, Event.class));
        } catch (IOException e) {
            log.error(EVENT_CONVERSION_ERROR_MESSAGE, jsonString, e);
            throw new EventProcessingException(EVENT_CONVERSION_ERROR_MESSAGE);
        }
    }
}

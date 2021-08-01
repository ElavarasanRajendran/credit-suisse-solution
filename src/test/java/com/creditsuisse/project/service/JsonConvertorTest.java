package com.creditsuisse.project.service;

import com.creditsuisse.project.common.dto.Event;
import com.creditsuisse.project.common.enums.EventState;
import com.creditsuisse.project.common.enums.EventType;
import com.creditsuisse.project.exception.EventProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;

import java.io.IOException;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JsonConvertorTest {

    @InjectMocks
    private JsonConvertor jsonConvertor;

    @Mock
    private ObjectMapper objectMapper;

    @BeforeAll
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void convertJsonStringToEventTest() throws IOException {
        String json = "{\"id\":\"scsmbstgra\", \"state\":\"STARTED\", \"type\":\"APPLICATION_LOG\", \"host\":\"12345\", \"timestamp\":1491377495212L}";
        Event event = getEvent();
        Mockito.when(objectMapper.readValue(json, Event.class)).thenReturn(event);
        Optional<Event> returnValue = jsonConvertor.convertJsonStringToEvent(json);
        returnValue.ifPresent(event1 -> {
            Assertions.assertEquals(event1.getId(), event.getId());
            Assertions.assertEquals(event1.getState(), event.getState());
            Assertions.assertEquals(event1.getType(), event.getType());
            Assertions.assertEquals(event1.getHost(), event.getHost());
            Assertions.assertEquals(event1.getTimestamp(), event.getTimestamp());
        });
    }

    @Test
    public void convertJsonStringToEventExceptionTest() throws IOException {
        String json = "{\"id\":\"scsmbstgra\", \"state\":\"STARTED\", \"type\":\"APPLICATION_LOG\", \"host\":\"12345\", \"timestamp\":1491377495212}";
        Mockito.when(objectMapper.readValue(json, Event.class)).
                thenThrow(new IOException("Error reading json string"));
        Assertions.assertThrows(EventProcessingException.class, () -> jsonConvertor.convertJsonStringToEvent(json));
    }

    private Event getEvent() {
        Event event = new Event();
        event.setId("scsmbstgra");
        event.setState(EventState.STARTED);
        event.setType(EventType.APPLICATION_LOG);
        event.setHost("12345");
        event.setTimestamp(1491377495212L);
        return event;
    }
}

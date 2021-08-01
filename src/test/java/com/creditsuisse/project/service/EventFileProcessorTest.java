package com.creditsuisse.project.service;

import com.creditsuisse.project.common.dto.Event;
import com.creditsuisse.project.common.dto.EventLog;
import com.creditsuisse.project.common.enums.EventState;
import com.creditsuisse.project.common.enums.EventType;
import com.creditsuisse.project.exception.EventNotSavedException;
import com.creditsuisse.project.exception.FileNotFoundAtLocation;
import com.creditsuisse.project.repository.EventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EventFileProcessorTest {

    @InjectMocks
    private EventFileProcessor eventFileProcessor;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private JsonConvertor jsonConvertor;

    @BeforeAll
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void processEventFileByLocationTest() throws FileNotFoundException {
        Mockito.when(jsonConvertor.convertJsonStringToEvent(anyString())).thenReturn(getEvent());
        Mockito.when(eventRepository.saveEvent(any())).thenReturn(true);
        File file = ResourceUtils.getFile("classpath:events.txt");
        eventFileProcessor.processEventFileByLocation(file.getAbsolutePath());
        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);

        Mockito.verify(jsonConvertor, Mockito.times(6))
                .convertJsonStringToEvent(argument.capture());

        ArgumentCaptor<EventLog> eventLogArgumentCaptor = ArgumentCaptor.forClass(EventLog.class);
        Mockito.verify(eventRepository, Mockito.times(5))
                .saveEvent(eventLogArgumentCaptor.capture());
    }

    @Test
    public void processEventFileByLocationExceptionTest() throws FileNotFoundException {
        Mockito.when(jsonConvertor.convertJsonStringToEvent(anyString())).thenReturn(getEvent());
        Assertions.assertThrows(FileNotFoundAtLocation.class, () -> {
            eventFileProcessor.processEventFileByLocation("classpath:event.txt");
        });
    }

    @Test
    public void eventSaveExceptionTest() throws FileNotFoundException {
        Mockito.when(jsonConvertor.convertJsonStringToEvent(anyString())).thenReturn(getEvent());
        Mockito.when(eventRepository.saveEvent(any())).thenReturn(false);
        File file = ResourceUtils.getFile("classpath:events.txt");
        Assertions.assertThrows(EventNotSavedException.class, () -> {
            eventFileProcessor.processEventFileByLocation(file.getAbsolutePath());
        });
    }

    private Optional<Event> getEvent() {
        Event event = new Event();
        event.setId("scsmbstgra");
        event.setState(EventState.STARTED);
        event.setType(EventType.APPLICATION_LOG);
        event.setHost("12345");
        event.setTimestamp(1491377495212L);
        return Optional.of(event);
    }
}

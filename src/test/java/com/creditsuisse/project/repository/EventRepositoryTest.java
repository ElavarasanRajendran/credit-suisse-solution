package com.creditsuisse.project.repository;

import com.creditsuisse.project.common.dto.Event;
import com.creditsuisse.project.common.dto.EventLog;
import com.creditsuisse.project.common.enums.EventState;
import com.creditsuisse.project.common.enums.EventType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EventRepositoryTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement statement;

    @InjectMocks
    private EventRepository eventRepository;

    @BeforeAll
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveEventTest() throws SQLException {
        Mockito.when(statement.executeUpdate()).thenReturn(1);
        Mockito.when(connection.prepareStatement(ArgumentMatchers.anyString())).thenReturn(statement);

        Event startEvent = new Event();
        startEvent.setId("Test ID");
        startEvent.setType(EventType.APPLICATION_LOG);
        startEvent.setHost("Test Host");
        startEvent.setState(EventState.STARTED);
        startEvent.setTimestamp(1235544432);

        Event endEvent = new Event();
        endEvent.setId("Test ID");
        endEvent.setType(EventType.APPLICATION_LOG);
        endEvent.setHost("Test Host");
        endEvent.setState(EventState.FINISHED);
        endEvent.setTimestamp(178886432);

        EventLog eventLog = EventLog.newInstance(startEvent,endEvent);

        boolean result = eventRepository.saveEvent(eventLog);
        assertTrue(result);
    }

    @Test
    public void closeTest() throws SQLException {
        eventRepository.close();
        Mockito.verify(connection, times(1)).close();
    }
}

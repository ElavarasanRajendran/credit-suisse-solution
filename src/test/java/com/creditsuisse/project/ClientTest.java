package com.creditsuisse.project;

import com.creditsuisse.project.exception.InvalidParamsException;
import com.creditsuisse.project.service.EventFileProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClientTest {

    @InjectMocks
    private Client client;

    @Mock
    private EventFileProcessor eventFileProcessor;

    @BeforeAll
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void runTest() throws Exception {
        String args[] = {"testLocation"};
        client.run(args);
        Mockito.verify(eventFileProcessor, times(1)).processEventFileByLocation("testLocation");
    }

    @Test
    public void runTestException() throws Exception {
        String args[] = {"testLocation", "anotherLocation"};
        Assertions.assertThrows(InvalidParamsException.class, () -> {
            client.run(args);
        });
    }
}

package com.surowka.services;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.TransportException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SensorServiceTest {

    private SensorService sensorService;
    private final String TEST_URL = "https://github.com/relayr/pdm-test/blob/master/sensors.yml";
    private final String TEST_URL_INVALID = "https://gitAAAhub.com/relBBBayr/pdmCCC-test/blob/master/sensors.yml";


    @Test
    void should_fail_due_to_invalid_giturl() throws IOException {
        sensorService = new SensorService(TEST_URL_INVALID);
        assertThrows(TransportException.class, ()-> { sensorService.getSensorList(); });
    }

    @Test
    void should_receive_sensor_valid_size_of_list() throws IOException, GitAPIException {
        sensorService = new SensorService(TEST_URL);
        assertEquals(5, sensorService.getSensorList().size());
    }
}

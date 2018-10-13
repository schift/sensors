package com.surowka.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.surowka.model.OperationPayload;
import com.surowka.model.Sensor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SensorActionHandlerServiceTest {

    private SensorActionHandlerService sensorActionHandlerService = new SensorActionHandlerService();
    private List<Sensor> listOfSensors = new ArrayList<>();

    @BeforeEach
    void init() {
        listOfSensors.add(new Sensor.SensorBuilder("1001").engine("556").type("pressure").value(70).min_value(33).max_value(86).build());
        listOfSensors.add(new Sensor.SensorBuilder("1002").masterSensorId("1001").type("temperature").value(95).min_value(20).max_value(110).build());
        listOfSensors.add(new Sensor.SensorBuilder("1003").engine("486").type("pressure").value(50).min_value(12).max_value(88).build());
        listOfSensors.add(new Sensor.SensorBuilder("1004").masterSensorId("1003").type("temperature").value(108).min_value(150).max_value(111).build());
        listOfSensors.add(new Sensor.SensorBuilder("1005").engine("211").type("pressure").value(39).min_value(30).max_value(60).build());
        listOfSensors.add(new Sensor.SensorBuilder("1006").masterSensorId("1005").type("temperature").value(54).min_value(60).max_value(79).build());
    }

    @Test
    void should_return_valid_values_in_return_array() {
        SensorActionHandlerService sensorActionHandlerService = new SensorActionHandlerService();
        Map<String, Integer> requestParams = new HashMap<>();
        requestParams.put("pressureThreshold", 40);
        requestParams.put("tempThreshold", 100);
        Set<String> retList = sensorActionHandlerService.getImproperlyWorkingEngines(listOfSensors, requestParams);
        assertTrue(retList.contains("211"));
        assertTrue(retList.contains("486"));
    }

    @Test
    void should_fail_incrementing_value_for_sensor() {
        String sensorID = "1002";
        ObjectMapper mapper = new ObjectMapper();
        OperationPayload operationPayload = new OperationPayload();
        operationPayload.setOperation("increment");
        operationPayload.setValue(20);
        assertEquals("sensor's value has been exceeded", sensorActionHandlerService.performOperationOnSensor(listOfSensors, sensorID, operationPayload));
    }

    @Test
    void should_fail_due_to_sensor_not_found() {
        String sensorID = "9999";
        ObjectMapper mapper = new ObjectMapper();
        OperationPayload operationPayload = new OperationPayload();
        operationPayload.setOperation("increment");
        operationPayload.setValue(20);
        assertEquals("sensor not found", sensorActionHandlerService.performOperationOnSensor(listOfSensors, sensorID, operationPayload));
    }

    @Test
    void should_increment_value_with_success() {
        String sensorID = "1002";
        ObjectMapper mapper = new ObjectMapper();
        OperationPayload operationPayload = new OperationPayload();
        operationPayload.setOperation("increment");
        operationPayload.setValue(5);
        assertEquals("success", sensorActionHandlerService.performOperationOnSensor(listOfSensors, sensorID, operationPayload));
    }
}

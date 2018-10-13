package com.surowka.services;

import com.surowka.enums.OperationType;
import com.surowka.enums.SensorType;
import com.surowka.model.OperationPayload;
import com.surowka.model.Sensor;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class SensorActionHandlerService {
    Set<String> returnList = new HashSet<>();

    public Set<String> getImproperlyWorkingEngines(List<Sensor> sensorList, Map<String, Integer> requestParams) {
        returnList.clear();
        for (Sensor sensor : sensorList) {
            if (sensor.getType().equals(SensorType.PRESSURE.toString()) && sensor.getValue() < requestParams.get("pressureThreshold")) {
                findEngineNumberAndAddToList(sensorList, sensor);
            }
            if (sensor.getType().equals(SensorType.TEMPERATURE.toString()) && sensor.getValue() > requestParams.get("tempThreshold")) {
                findEngineNumberAndAddToList(sensorList, sensor);
            }
        }
        return returnList;
    }

    public String performOperationOnSensor(List<Sensor> sensorList, String sensorID, OperationPayload operationPayload) {
        Optional<Sensor> sensor = sensorList.stream().filter(s -> s.getId().equals(sensorID)).findFirst();
        if (StringUtils.isBlank(operationPayload.getOperation()) || operationPayload.getValue() == null) {
            throw new IllegalArgumentException("no operation or value defined");
        }
        if (sensor.isPresent()) {
            int val = 0;
            if (OperationType.INCREMENT.toString().equals(operationPayload.getOperation())) {
                val = sensor.get().getValue() + operationPayload.getValue();
            } else if (OperationType.DECREMENT.toString().equals(operationPayload.getOperation())) {
                val = sensor.get().getValue() - operationPayload.getValue();
            }
            if (val > sensor.get().getMax_value() || val < sensor.get().getMin_value()) {
                return "sensor's value has been exceeded";
            }
            sensor.get().setValue(val);
        } else {
            return "sensor not found";
        }
        return "success";
    }

    private void findEngineNumberAndAddToList(List<Sensor> sensorList, Sensor sensor) {
        if (StringUtils.isBlank(sensor.getEngine()) && StringUtils.isNotBlank(sensor.getMaster_sensor_id())) {
            Optional<Sensor> masterSensor = sensorList.stream().filter(s -> s.getId().equals(sensor.getMaster_sensor_id())).findAny();
            if (masterSensor.isPresent()) {
                returnList.add(masterSensor.get().getEngine());
            }
        } else {
            returnList.add(sensor.getEngine());
        }
    }
}

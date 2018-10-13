package com.surowka.services;

import com.surowka.model.Sensor;
import org.apache.commons.lang3.StringUtils;
import spark.Request;
import spark.Response;

import java.util.*;

public class EngineService {
    Set<String> returnList = new HashSet<>();
    List<Sensor> sensorList;

    public Set<String> getImproperlyWorkingEngines(List<Sensor> p_SensorList, Request req, Response res) {
        sensorList = p_SensorList;
        int pressureThreshold = Integer.parseInt(req.queryParams("pressure_threshold"));
        int tempThreshold = Integer.parseInt(req.queryParams("temp_threshold"));
        returnList.clear();
        for (Sensor sensor : sensorList) {
            if (sensor.getType().equals("pressure") && Integer.parseInt(sensor.getValue()) < pressureThreshold) {
                findEngineNumberAndAddtoList(sensor);
            }
            if (sensor.getType().equals("temperature") && Integer.parseInt(sensor.getValue()) > tempThreshold) {
                findEngineNumberAndAddtoList(sensor);
            }
        }
        return returnList;
    }
    private void findEngineNumberAndAddtoList(Sensor sensor) {
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

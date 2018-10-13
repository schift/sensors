package com.surowka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.surowka.model.OperationPayload;
import com.surowka.model.Sensor;
import com.surowka.services.SensorActionHandlerService;
import com.surowka.services.SensorInitialListService;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static spark.Spark.*;

public class SensorController {

    private static List<Sensor> sensorList;
    public static final boolean isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;;
    private static final Logger LOGGER = Logger.getLogger(SensorController.class.getName());

    public static void main(String[] args) throws IOException, GitAPIException {
        if (args.length == 0) {
            System.out.println("Warning, input parameter required. \nPlease provide URL to get sensor list.");
        }
        String sensorListURL = args[0];

        final SensorInitialListService sensorInitialListService = new SensorInitialListService(sensorListURL);
        final SensorActionHandlerService sensorActionHandlerService = new SensorActionHandlerService();
        sensorList = sensorInitialListService.getSensorList();

        // change default spark port from 4567 to 8080
        port(8080);
        LOGGER.info("Service started.");
        /*
            API :: routers
        */
        // get list of improperly working engines
        get("/engines", (req, res) -> {
            LOGGER.info("incoming http get request");
            Map<String, Integer> requestParams = new HashMap<>();
            requestParams.put("pressureThreshold", Integer.parseInt(req.queryParams("pressure_threshold")));
            requestParams.put("tempThreshold", Integer.parseInt(req.queryParams("temp_threshold")));
            return sensorActionHandlerService.getImproperlyWorkingEngines(sensorList, requestParams);
        });

        // update sensor's value
        post("/sensors/:sensorID", (req, res) -> {
            String sensorID = req.params("sensorID");
            ObjectMapper mapper = new ObjectMapper();
            OperationPayload operationPayload = mapper.readValue(req.body(), OperationPayload.class);
            LOGGER.info("incoming http post request");
            return sensorActionHandlerService.performOperationOnSensor(sensorList, sensorID, operationPayload);
        });
    }
}

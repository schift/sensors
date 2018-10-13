package com.surowka;

import com.surowka.model.Sensor;
import com.surowka.services.EngineService;
import com.surowka.services.SensorService;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.IOException;
import java.util.List;

import static spark.Spark.*;

public class SensorController {

    private static List<Sensor> sensorList;
    public static final boolean isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;;

    public static void main(String[] args) throws IOException, GitAPIException {
        if (args.length == 0) {
            System.out.println("Warning, input parameter required. \nPlease provide URL to get sensor list.");
        }
        String sensorListURL = args[0];

        final EngineService engineService = new EngineService();
        final SensorService sensorService = new SensorService(sensorListURL);
        sensorList = sensorService.getSensorList();

        // change default spark port from 4567 to 8080
        port(8080);

        /*
            API :: routers
        */
        // get list of improperly working engines
        get("/engines", (req, res) -> {
            return engineService.getImproperlyWorkingEngines(sensorList, req, res);
        });

        // update sensor's value
        post("/users", (request, response) -> {
            return null;
        });
    }
}

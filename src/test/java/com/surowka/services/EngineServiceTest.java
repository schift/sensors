package com.surowka.services;

import com.surowka.SensorController;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.Asserts;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import spark.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EngineServiceTest {

    private EngineService engineService = new EngineService();

	@Disabled
    @Test
    void should_return_expected_list_of_values() throws IOException, GitAPIException {

        SensorController sensorController = new SensorController();
        SensorController.main(new String[] {"https://github.com/relayr/pdm-test/blob/master/sensors.yml"});

        URL url = new URL("http://localhost:8080/engines?pressure_threshold=70&temp_threshold=100");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        assertEquals("[123]", content.toString());
    }
}

package com.surowka.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.surowka.SensorController;
import com.surowka.model.Sensor;
import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class SensorInitialListService {

    private final String repoName;
    private final String repoGitUrl;

    public SensorInitialListService(String url) {
        String tempURL = url.substring(0, url.indexOf("/blob"));
        repoName = tempURL.substring(tempURL.lastIndexOf("/") + 1);
        repoGitUrl = tempURL.concat(".git");
    }

    public List<Sensor> getSensorList() throws GitAPIException, IOException {
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        if (SensorController.isDebug && Files.exists(Paths.get(repoName))) {
            FileUtils.deleteDirectory(new File(repoName));
        }
        if (Files.notExists(Paths.get(repoName))) {
            Git git = Git.cloneRepository().setURI(repoGitUrl).call();
            git.getRepository().close();
        }
        return Arrays.asList(mapper.readValue(new FileReader(repoName.concat("/sensors.yml")), Sensor[].class));
    }
}

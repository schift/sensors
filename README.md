#RUN BUILD
mvn clean install

#RUN TEST
mvn clean test

#DOCKER DEPLOY
docker build -t giantswarm/sensors .
docker run -d -p 8080:8080 giantswarm/sensors
docker container ls
docker stop <image_id>

#RUM FROM COMMANDLINE
java -jar sensors-jar-with-dependencies.jar https://github.com/relayr/pdm-test/blob/master/sensors.yml


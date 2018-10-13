#RUN BUILD
mvn clean install

#RUN TEST
mvn clean test

#DOCKER DEPLOY
docker build -t giantswarm/sensors .
docker run -d -p 8080:8080 giantswarm/sensors
docker container ls
docker stop <image_id>


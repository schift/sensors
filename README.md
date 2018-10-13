docker build -t giantswarm/sensors .
docker run -d -p 4567:4567 giantswarm/sensors
docker container ls
docker stop <image_id>
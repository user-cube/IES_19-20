docker image build -t iesservlet/lab02 ./ &&
docker container run -it --publish 8089:8080 iesservlet/lab02

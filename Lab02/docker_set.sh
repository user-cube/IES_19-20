docker image pull tomcat:8.0 &&
docker image ls &&
docker container create --publish 8280:8080 --name iesServlet container tomcat:8.0 &&
docker container ls -a &&
docker container start ieslab02

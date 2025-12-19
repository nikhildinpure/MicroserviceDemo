docker commands
1. docker --version
2. docker info
3. docker container ls
4. docker run -d -p 80:80 docker/getting-started 
5. docker run hello-world 
6. docker info | findstr "Operating System"
7. docker build context (.) must contain Dockerfile 
8. docker build -t hello-world-python:0.0.1.RELEASE . 
9. docker run -d -p 5000:5000 hello-world-python:0.0.1.RELEASE 
10. docker ps

spring boot maven plugin -
1. mvn spring-boot:repackage
2. mvn spring-boot:run
3. mvn spring-boot:stop
4. mvn spring-boot:build-image

Zipkin
docker run -d -p 9411:9411 openzipkin/zipkin:2.23.16
docker-compose config
docker network ls
docker network inspect currency-network

docker build -t nikinp/currency-exchange-service:0.0.1 .
docker build -t nikinp/currency-conversion-service:0.0.1 .
docker build -t nikinp/api-gateway:0.0.1 .
docker build -t nikinp/naming-server:0.0.1 .

docker-compose up -d
docker-compose down
docker-compose down -v
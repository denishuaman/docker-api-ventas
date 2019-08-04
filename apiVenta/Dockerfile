### CREACION DE JAR ###
FROM maven:3.6-jdk-8-alpine AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn -e -B dependency:go-offline
COPY src ./src
RUN cd /app && mvn -e -B package -Dmaven.test.skip=true

#### EJECUCION DEL JAR ####
FROM openjdk:8-jdk-alpine

ENV DOCKERIZE_VERSION v0.6.1
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz

#COPY ./target/api*.jar /app.jar
COPY --from=builder /app/target/*.jar /app.jar

ENV hostapi="http://apipersona:8081"
ENV host="mysql_server"
ENV port="3306"
ENV database="ventas"
ENV username="root"
ENV password="root"

ENTRYPOINT exec java -jar /app.jar
EXPOSE 8082
FROM gradle:jdk21 AS build

ENV BUILD_PATH=/usr/build
ENV MODULE_PATH=server/discovery-server

USER root
WORKDIR $BUILD_PATH

COPY gradle.properties .
COPY gradle/ gradle/

COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY $MODULE_PATH $MODULE_PATH

RUN gradle clean :server:discovery-server:build --no-daemon

FROM openjdk:21-jdk

ENV BUILD_PATH=/usr/build
ENV MODULE_PATH=server/discovery-server

ENV APP_PATH=/usr/app
ENV JAR_NAME=service.jar
ENV PORT=8080

EXPOSE $PORT
EXPOSE $DB_PORT

RUN mkdir $APP_PATH
WORKDIR $APP_PATH

COPY --from=build $BUILD_PATH/$MODULE_PATH/build/libs/*.jar $APP_PATH/$JAR_NAME

ENTRYPOINT exec java -jar $APP_PATH/$JAR_NAME

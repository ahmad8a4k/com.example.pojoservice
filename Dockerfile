FROM gradle:7-jdk11 AS build

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN bash gradlew shadowJar

FROM openjdk:11-jdk-slim

EXPOSE 8080:8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/pojoservice.jar

ENTRYPOINT ["java","-jar","/app/pojoservice.jar"]
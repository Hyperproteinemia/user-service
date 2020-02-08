FROM openjdk:8-jdk-alpine

VOLUME /tmp

EXPOSE 8081

ARG JAR_FILE=target/users-service-1.0.jar
ADD ${JAR_FILE} users-service.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","users-service.jar"]
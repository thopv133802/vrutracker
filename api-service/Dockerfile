FROM openjdk:11
COPY build/libs/api-service-1.0.0-SNAPSHOT.jar /app/api-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/api-service.jar"]
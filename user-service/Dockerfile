FROM openjdk:11
COPY build/libs/user-service-1.0.0-SNAPSHOT.jar /app/user-service.jar
EXPOSE 18082
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/user-service.jar"]
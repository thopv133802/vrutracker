FROM openjdk:11
COPY build/libs/organization-service-1.0.0-SNAPSHOT.jar /app/organization-service.jar
EXPOSE 18083
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/organization-service.jar"]
FROM openjdk:11
COPY build/libs/statistics-service-1.0.0-SNAPSHOT.jar /app/statistics-service.jar
EXPOSE 18081
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/statistics-service.jar"]
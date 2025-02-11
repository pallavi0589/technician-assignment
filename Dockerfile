#FROM openjdk:8-jdk-alpine
#WORKDIR /app
#COPY target/assignment-api.jar assignment-api.jar
#CMD ["java", "-jar", "assignment-api.jar"]
#EXPOSE 8080
#ENV SPRING_PROFILES_ACTIVE=default

# Use an official OpenJDK runtime as a parent image
#FROM openjdk:17-jdk-alpine
#FROM eclipse-temurin:17-jdk-alpine
#FROM openjdk:8-jdk-alpine
FROM amazoncorretto:17-alpine



# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file into the container
#COPY target/assignment-api.jar assignment-api.jar
COPY target/assignment-api-*.jar assignment-api.jar


# Expose the application port
EXPOSE 8080

# Run the JAR file
CMD ["java", "-jar", "assignment-api.jar"]


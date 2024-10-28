# Use a base image with JDK 17
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /mengistuRestDemo

# Copy the built JAR file into the container
COPY target/restDemo-0.0.1-SNAPSHOT.jar mengistu-rest-demo-app.jar

# Expose the default port for Spring Boot
EXPOSE 9090

# Define default environment variables if not given during commandline parameters
ENV SERVER_PORT=9090
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/db_name
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=root

# Run the application with dynamic environment variables
ENTRYPOINT ["java", "-jar", "mengistu-rest-demo-app.jar", "--server.port=${SERVER_PORT}", \
            "--spring.datasource.url=${SPRING_DATASOURCE_URL}", \
            "--spring.datasource.username=${SPRING_DATASOURCE_USERNAME}", \
            "--spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}"]

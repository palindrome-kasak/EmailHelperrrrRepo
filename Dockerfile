# Use OpenJDK base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file to the container
COPY target/*.jar app.jar

# Expose the port (Render uses PORT environment variable)
EXPOSE 8081

# Run the application
CMD ["java", "-jar", "app.jar"]
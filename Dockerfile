# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:17-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/EmailHelper-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on
EXPOSE 8081

# Define environment variables (these can be overridden when running the container)
ENV GEMINI_URL="https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key="
ENV GEMINI_KEY="AIzaSyCh1tNXSgUQOTYdlSqtPyQBzCX2Co9UBTw"

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

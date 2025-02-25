# Use a build stage to compile the application
FROM eclipse-temurin:17-jdk as builder

# Set working directory
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the application
RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests

# Use a new stage for the final image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/EmailHelper-0.0.1-SNAPSHOT.jar app.jar

# Expose the application's port
EXPOSE 8081

# Define environment variables (these can be overridden when running the container)
ENV GEMINI_URL="https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key="
ENV GEMINI_KEY="AIzaSyCh1tNXSgUQOTYdlSqtPyQBzCX2Co9UBTw"

# Run the application
CMD ["java", "-jar", "app.jar"]

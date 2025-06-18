# Use Eclipse Temurin OpenJDK 21 as base image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Ensure the Maven wrapper has execute permission (important for Render)
RUN chmod +x mvnw

# Build the application (skip tests)
RUN ./mvnw clean package -DskipTests

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the Spring Boot app
CMD ["java", "-jar", "target/whatsapp-0.0.1-SNAPSHOT.jar"]

# Use OpenJDK 21 as base
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy all files
COPY . .

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose port
EXPOSE 8080

# Start the app
CMD ["java", "-jar", "target/whatsapp-0.0.1-SNAPSHOT.jar"]

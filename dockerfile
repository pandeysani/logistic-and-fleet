# Stage 1: Build the application
FROM maven:3.8.7-eclipse-temurin-17 AS builder
WORKDIR /workspace

# Copy pom.xml and download dependencies (caching)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy jar from the builder stage
COPY --from=builder /workspace/target/*.jar app.jar

# Expose the port your app will listen on locally by default
# On Render, the $PORT env variable overrides this at runtime
EXPOSE 8090

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]

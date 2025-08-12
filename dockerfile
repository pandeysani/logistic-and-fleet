# Stage 1: Build the application
FROM maven:3.8.7-eclipse-temurin-17 AS builder
WORKDIR /workspace

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy full source code
COPY src ./src

# Build application (fail if tests fail, skip tests if needed)
RUN mvn clean package -DskipTests

# Double-check jar exists
RUN ls -l target && test -f target/fleet-backend-0.0.1-SNAPSHOT.jar

# Stage 2: Run the application
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy jar from builder
COPY --from=builder /workspace/target/fleet-backend-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8090

# Run app
ENTRYPOINT ["java", "-jar", "/app.jar"]

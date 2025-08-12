# Stage 1: Build the application
FROM maven:3.8.7-eclipse-temurin-17 AS builder
WORKDIR /workspace

# Copy Maven config and wrapper
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src ./src

# Build application (skip tests for speed)
RUN ./mvnw clean package -DskipTests

# Fail if no JAR is created
RUN test -f target/*.jar

# Stage 2: Run the application
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the built JAR (wildcard will match the only jar in target)
COPY --from=builder /workspace/target/*.jar app.jar

# Expose local dev port (Render overrides with $PORT)
EXPOSE 8090

# Start application
ENTRYPOINT ["java", "-jar", "/app.jar"]

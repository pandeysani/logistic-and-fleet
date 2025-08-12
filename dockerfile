# ============================
# Stage 1: Build the application
# ============================
FROM maven:3.8.7-eclipse-temurin-17 AS builder
WORKDIR /workspace

# Copy Maven config first for caching
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .

# Make mvnw executable
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src ./src

# Build application (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# Check jar is created
RUN ls -l target && test -f target/*.jar

# ============================
# Stage 2: Run the application
# ============================
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy runnable jar from builder
COPY --from=builder /workspace/target/*.jar /app.jar

# Expose the port Render will set dynamically
EXPOSE 8080

# Run the application, passing PORT from environment
ENTRYPOINT ["java", "-jar", "/app.jar"]

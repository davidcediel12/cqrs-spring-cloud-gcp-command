# ===============================
# Stage 1 - Build the application
# ===============================
FROM gradle:8.10-jdk21 AS builder
WORKDIR /app

# Copy gradle files
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle

# Download dependencies (caches for faster builds)
RUN ./gradlew dependencies --no-daemon || return 0

# Copy the rest of the project
COPY . .

# Build the application
RUN ./gradlew bootJar --no-daemon

# ===============================
# Stage 2 - Run the application
# ===============================
FROM eclipse-temurin:21-jdk AS runtime


RUN mkdir -p /opt/cprof && \
  wget -q -O- https://storage.googleapis.com/cloud-profiler/java/latest/profiler_java_agent.tar.gz \
  | tar xzv -C /opt/cprof


WORKDIR /app

# Copy jar from builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose port (adjust if your app runs on a different port)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

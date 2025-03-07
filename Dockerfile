# Use a Maven image that includes OpenJDK 11
FROM maven:3.8.6-openjdk-11 AS build

WORKDIR /app

# Copy custom settings.xml for better dependency resolution
COPY maven-settings.xml /root/.m2/settings.xml

# Copy pom.xml separately for better caching
COPY pom.xml ./

# Download dependencies first
RUN mvn -s /root/.m2/settings.xml dependency:go-offline

# Copy the rest of the source code
COPY . .

# Compile and package the application
RUN mvn -s /root/.m2/settings.xml clean package -DskipTests

# Use a minimal JRE image for runtime
FROM openjdk:11-jre-slim

WORKDIR /app

# Copy only the JAR from the build stage
COPY --from=build /app/target/weather-docker.jar weather-docker.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/weather-docker.jar"]

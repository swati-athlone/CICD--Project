# Use a lightweight Java image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the built jar
COPY /Users/swati/deploy/wine-manager.jar wine-manager.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "wine-manager.jar"]

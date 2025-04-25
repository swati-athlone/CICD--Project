FROM openjdk:17-jdk-slim
WORKDIR /app
COPY wine-manager.jar /app/wine-manager.jar
CMD ["java", "-jar", "wine-manager.jar"]
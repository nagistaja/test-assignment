# Stage 1: Build the application using Gradle with JDK 21
FROM gradle:8.12-jdk21 AS builder
WORKDIR /home/app
ENV GRADLE_USER_HOME=/home/app/.gradle
RUN mkdir -p /home/app/.gradle && chown -R gradle:gradle /home/app/.gradle
COPY . .
RUN chown -R gradle:gradle /home/app
RUN gradle clean build -x test --no-daemon --stacktrace

# Stage 2: Run the application using OpenJDK 21
FROM openjdk:21-jdk-slim
WORKDIR /home/app
COPY --from=builder /home/app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

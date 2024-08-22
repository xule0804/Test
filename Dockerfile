# The official OpenJDK 17 image is used as the base image.
FROM openjdk:17-jdk

#  set workdir
WORKDIR /app

# Create a log directory and set permissions.
RUN mkdir -p /app/logs && chmod -R 755 /app/logs

# copy test.jar from application dir to workdir app
COPY target/test.jar /app/test.jar

# Expose port.
EXPOSE 8080

# Run application.
CMD ["java", "-jar", "test.jar"]
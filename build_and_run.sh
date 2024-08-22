# Stop and remove existing Docker containers, if any.
docker stop testapp-container && docker rm testapp-container

# Build and package the Spring Boot application.
echo "Building the application with Maven..."
mvn clean package -DskipTests

# Build the Docker image.
echo "Building Docker image..."
docker build -t testapp:latest .

# Run the Docker container.
echo "Running Docker container..."
docker run -d -p 8080:8080 --name testapp-container testapp:latest

echo "Application is now running at http://localhost:8080"

#!/bin/bash

# stop Docker Compose 
echo "Stopping Docker Compose services..."
docker-compose down

# check
if [ $? -eq 0 ]; then
  echo "Docker Compose services stopped successfully."
else
  echo "Failed to stop Docker Compose services."
  exit 1
fi

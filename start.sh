#!/bin/bash

# start Docker Compose 
echo "Starting Docker Compose services..."
docker-compose up -d

# check
if [ $? -eq 0 ]; then
  echo "Docker Compose services started successfully."
else
  echo "Failed to start Docker Compose services."
  exit 1
fi

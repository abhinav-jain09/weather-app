#!/bin/bash

# Check if an argument is supplied
if [ $# -eq 0 ]; then
    echo "No arguments supplied. Possible arguments are: local, test, acc, prod."
    echo "Example: sh startapp.sh local"
    exit 1
fi

# Assign the first argument to denv
export denv="$1"

echo "Stopping and removing existing containers..."
docker-compose down -v --remove-orphans  # Stops containers and removes volumes

echo "Removing unused Docker images..."
docker image prune -af  # Removes all unused images

echo "Building the application..."
mvn clean install -Denv=docker

echo "Building and starting fresh containers..."
docker-compose up --build -d  # Forces a fresh build

echo "Showing running containers..."
docker ps

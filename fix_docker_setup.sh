#!/bin/bash

echo "🚀 Starting Fix Docker Setup Script 🚀"

# Stop and remove all running containers
echo "🛑 Stopping and removing all containers..."
docker ps -aq | xargs docker stop
docker ps -aq | xargs docker rm -f

# Remove old networks
echo "�� Removing unused networks..."
docker network prune -f

# Remove old volumes
echo "🗑 Removing unused volumes..."
docker volume prune -f

# Check if the 'mongodb' container exists and remove it
if [ "$(docker ps -aq -f name=mongodb)" ]; then
    echo "🔄 Removing existing MongoDB container..."
    docker rm -f mongodb
fi

# Check if the 'assignment-container' container exists and remove it
if [ "$(docker ps -aq -f name=assignment-container)" ]; then
    echo "🔄 Removing existing assignment-api container..."
    docker rm -f assignment-container
fi

# Ensure correct docker-compose.yml exists
echo "📂 Checking docker-compose.yml file..."
if [ ! -f "docker-compose.yml" ]; then
    echo "❌ Error: docker-compose.yml not found!"
    exit 1
fi

# Rebuild and start the containers
echo "⚡ Rebuilding and starting Docker containers..."
docker-compose up --build -d

# Wait for MongoDB to initialize
echo "⏳ Waiting for MongoDB to start..."
sleep 10

# Check logs to confirm MongoDB is running
echo "📜 Checking MongoDB logs..."
docker logs mongodb --tail 20

# Check if MongoDB is accessible inside the container
echo "🛠 Testing MongoDB connection inside the container..."
docker exec -it mongodb mongosh --eval "show dbs;"

# Check assignment-api logs
echo "📜 Checking assignment-api logs..."
docker logs assignment-container --tail 20

echo "✅ Docker Setup Fix Completed Successfully!"


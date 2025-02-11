# technician-assignment
 
Assignment API - Spring Boot & MongoDB

# Project Overview

This is a Spring Boot-based REST API for managing assignments and technicians. The application uses MongoDB as the database and runs inside Docker for easy deployment.

# Features

Technician Management: Create, update, and delete technicians.

Assignment Management: Assign tasks to technicians, ensuring a valid technician ID.

MongoDB Integration: Uses MongoDB as the database.

Docker Support: Runs via docker-compose for containerized deployment.

Swagger UI: API documentation available via Swagger.

Validation: Ensures assignments can only be created for existing technicians.


## Setup & Installation

# 1 Prerequisite

Java 17+

Maven

Docker & Docker Compose

# 2 Running the Application

# Option 1: Using Docker (Recommended)

docker-compose up --build -d

This will start MongoDB and the Spring Boot application inside containers.

# Option 2: Running Manually (Without Docker)

Start MongoDB manually.

Run the Spring Boot application:

mvn clean install
mvn spring-boot:run

# 3 Access the API

Swagger UI: http://localhost:8080/swagger-ui/index.html

MongoDB Console:

docker exec -it mongodb mongosh

# API Endpoints:

POST /technicians → Create Technician

GET /technicians/{id} → Fetch Technician

DELETE /technicians/{id} → Delete Technician

POST /assignments → Create Assignment (Ensures valid technician)

GET /assignments/{id} → Fetch Assignment


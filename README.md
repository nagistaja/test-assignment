# Helmes Fullstack Sectors App

A combined repository for a **Spring Boot** backend and a **Next.js** frontend. This project includes:

- **Backend** (Spring Boot): REST API for sector and user management
- **Frontend** (Next.js): User interface for selecting sectors and editing user data
- **DB Dump**: Located in project root 

## Repository Structure

```
test-assignnment/ 
├── backend/
│ ├── Dockerfile
│ ├── build.gradle
│ └── src/...
├── frontend/ 
│ ├── Dockerfile 
│ ├── package.json 
│ └── ... 
├── docker-compose.yml
└── db_dump.sql
```

- **backend/**: The Spring Boot application
- **frontend/**: The Next.js application
- **docker-compose.yml**: Orchestrates Docker containers for backend, frontend, and Postgres DB

## Prerequisites

- **Docker** and **Docker Compose** installed
- (Optional) **Java 21+** and **Node 22+** if you want to run services locally outside of Docker

## Running with Docker Compose

### 1. **Build and run all services**:
   ```bash
   docker-compose up --build
   ````

This will:

* Build the backend image (runs on port 8080)
* Build the frontend image (runs on port 3000)
* Build the Postgres DB image (runs on port 5432)

### 2. **Access**:

- **Backend**: http://localhost:8080
- **Frontend**: http://localhost:3000
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html

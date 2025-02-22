# Assignment Task Application for Helmes

This is a Spring Boot application that uses PostgreSQL and Flyway for database management, and provides endpoints for managing users and sectors.

## Prerequisites

* Docker
* Java 21+
* Gradle

## Setup & Build

1. Clone the Repository
   ``` bash
   git clone https://github.com/your-username/helmes-task.git
   cd helmes-task
   ```
2. Build the Project
    ``` bash
    ./gradlew clean build
    ```
   This compiles all sources, runs tests, and creates a runnable jar in build/libs/.

## Running the Application

### Option 1: Docker Compose
1. **Run docker compose in the project root:**

``` bash
docker-compose up --build
```
This starts both the PostgreSQL database and the application. By default, the application listens on port 8080 and the database on port 5432.

2. **Check Logs**

* Application logs appear in your terminal.
* By default, the endpoints are available at: http://localhost:8080

### Option 2: Local Execution
1. Start PostgreSQL (manually or via Docker, if you prefer).
2. Configure Data Source in application.properties (or override via environment variables).
3. Run the Jar
```bash
java -jar build/libs/helmes-task-<version>.jar
```
## Testing
We use JUnit 5 and Testcontainers for integration tests. The key test tasks:

* **Unit Tests and Integration Tests:**

```bash
./gradlew test
```

* **Full Build (including tests):**

```bash
./gradlew clean build
```

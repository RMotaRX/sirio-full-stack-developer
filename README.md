# Execution Steps

1. Clone the Project.
2. Run the command `make mysql-up` or execute `docker-compose -f ./docker-compose.yml up -d`.
3. Open the project in an IDE.
4. Access the Swagger URL: [http://localhost:8091/api/fullstack/test/swagger-ui/index.html](http://localhost:8091/api/fullstack/test/swagger-ui/index.html).

# Required Software to Run the Application

1. Docker
2. JDK 17
3. Maven
4. Make
5. MySQL v8

# .env File

Place the following constants inside the `.env` file located at `src/main/resources/`:

- `MYSQL_ROOT_PASSWORD=toor`
- `MYSQL_DATABASE=sirio`
- `MYSQL_USER=admin`
- `MYSQL_PASSWORD=admin`

# Running Tests

- Run the command `make check` or execute `mvn clean install` to build the project and execute all tests.

# Application Usage

After successfully starting the application, you can interact with the API via Swagger at [http://localhost:8091/api/fullstack/test/swagger-ui/index.html](http://localhost:8091/api/fullstack/test/swagger-ui/index.html).

# Database Configuration

The application connects to a MySQL database. Ensure the database is up by using the `make mysql-up` command or by manually running the docker-compose file (`docker-compose -f ./docker-compose.yml up -d`).

# Project Structure

- **src/main/java**: Contains the main application code and packages.
- **src/main/resources**: Holds configuration files, including the `.env` file for environment variables.
- **src/test**: Contains unit and integration tests for the application.

# Additional Commands

- `make mysql-down`: Stops and removes the MySQL container.
- `make clean`: Cleans up any compiled files and target directories.

# Makefile Commands

- **`make mysql-up`**: Create and start containers with MySQL 8 and Localstack.
- **`make check`**: Build the full application, running all tests and checks.
- **`make clean`**: Clean up generated files and temporary containers.
- **`make mysql-down`**: Stop and remove the containers.
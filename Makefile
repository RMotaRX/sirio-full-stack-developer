# Create the container with MySQL 8 and Localstack using path .docker-compose.yml
mysql-up:
	docker-compose -f ./docker-compose.yml up -d

# Build the full application running all tests and checks
check:
	mvn clean install

# Clean up generated files and temporary containers
clean:
	mvn clean

# Stop and remove the containers
mysql-down:
	docker-compose -f ./docker-compose.yml down
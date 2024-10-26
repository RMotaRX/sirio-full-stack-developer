# create the container with mysql 8 and localstack using path .docker-compose.yml
mysql-up:
	docker-compose -f ./docker-compose.yml up -d

# build the full application running all test and checks
check:
	mvn clean install
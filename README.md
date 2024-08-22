#  Test Project


## Introduction
This is a Java project for a coding test, based on SpringBoot 3.3.2, Mybatis 3.0.3, Maven 3.8.6, and MySQL 8.x.


## Features
It implements user registration, retrieval, update, and soft delete functionalities, following the RESTful style.


## Installation


### Prerquisites
`Java 17`, `Maven3.8.6 `, `Docker version 27.1.1`.


### Steps
1. Start Spring Boot and MySQL using Docker Compose.  
chmod +x start.sh  
./start.sh

2. Check db.  
mysql -h 127.0.0.1 -P 3307 -u test -ptest123  
show databases;

3. Create the tables required for the application.  
Execute the table creation statements in /Test/src/db-migrates/test.sql in the database.

4. View application logs  
docker exec -it testapp-container /bin/sh  
cd /app/logs

5. Shut down all services.  
chmod +x stop.sh  
./stop.sh

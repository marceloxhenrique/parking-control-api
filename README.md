# Parking Control API

This project provides a REST API for managing parking spot availability and access control systems. It is built using the following technologies:

* Java: As the primary programming language.
* Spring Boot: For rapid application development and simplified configuration.
* Spring Security: For user authentication and authorization.
* JUnit5: To test API endpoints.
* Swagger: To generate interactive API documentation.

## Prerequisites
* Java 17.0.10
* Maven

## Getting Started

First, clone the repository

```shell
git clone https://git@github.com:marceloxhenrique/parking-control-api.git
```

## Navigate to the project directory

```shell
cd <your-project-name>
```

## Install the project
```shell
mvn install
```

## Setup application.properties
```shell
spring.application.name=parking-control
spring.datasource.platform=postgres
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=yourUsername
spring.datasource.password=yourPassword
spring.jpa.hibernate.ddl-auto=update

spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
```

## Running teh application
```shell
mvn spring-boot:run
```
- The app run on port 8080 by default.

## API documentation
```shell
http://localhost:8080/swagger-ui/index.html
```

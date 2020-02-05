# URL Shortner

Spring Boot based REST API that takes a URL and returns a shortened URL and uses MySQL to persist data.

# Getting Started

## Dependencies

This project depends on 
* spring-boot-starter-web (Spring boot framework)
* spring-boot-starter-data-jpa (for data persistence)
* spring-boot-starter-actuator (for API statistics)
* commons-validator:1.6 (for URL validation)
* h2 (for tests)
* spring-boot-starter-test (for testss)

## Project Build 

To build this project, run

```
git clone https://github.com/zeeshaanahmad/url-shortener.git
cd url-shortener
gradle clean build
```

## Deployment

Project build can be deployed using docker-compose.yml which sets up two containers for
* MySql
* REST API

To deploy the project, run

```
docker-compose up --build
```

### db.Dockerfile
`db.Dockerfile` builds the docker image for MySql using MySql version 8 as the base image. It uses `schema.sql` at startup to set up the database schema.

### api.Dockerfile
`api.Dockerfile` sets up an image to deploy the project's jar file generated above from `build/libs/url-shortener-0.0.1-SNAPSHOT.jar`. It exposes the API on port `8080`

### docker-compose.yml
Provides the configuration for containers to host API and MySql. It sets up two services; `api-server` and `api-db` with container names `urlshortener-springboot` and `mysqlurldb` respectively. 
The datasource url is being set in the `api-server` configuration so that it points to the MySql container.
Both `api-server` and `api-db` are linked together through the `urlshortener-mysql-network` docker network. The network enables both the containers to communicate together.

## API Endpoints

There are 2 API endpoints

### POST `/shorten`
It takes a JSON object in the following format as payload

```json
{
  "fullUrl":"<The URL to be shortened>"
}
```

Response:

```json
{
  "shortUrl": "<shortened url for the fullUrl provided in the request payload>"
}
```

Please note that API works only with valid HTTP or HTTPS Urls. In case of malformed Url, it returns `400 Bad Request` error with response body containing a JSON object in the following format

```json
{
  "field":"fullUrl",
  "value":"<Malformed Url provided in the request>",
  "message":"<Exception message>"
}
```

### GET `/<shortened_text>`

This endpoint redirects to the corresponding fullUrl.


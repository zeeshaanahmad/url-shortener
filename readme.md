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

```shell script
git clone https://github.com/zeeshaanahmad/url-shortener.git
cd url-shortener
gradle clean build
```

## Deployment

Project build can be deployed using docker-compose.yml which sets up two containers for
* MySql
* REST API

To deploy the project, run

```shell script
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

### GET `/actuator/health`

Included the spring boot actuator dependency for API metrics. You can try this endpoint for health checks.

## Undeploy

To undeploy the containers, run

```shell script
docker-compose down
```

# Url Shortening Algorithm

I thought of two approaches
1. Generating hashes for the fullUrl and storing them as key value pairs in redis cache or in mysql database
2. Performing a Base62 conversion from Base10 on the id of stored fullUrl

Tested both of the approaches but in case of hashes, sometimes the hashes were longer than actual URL. Another issue was the readability and ease of remembering. So, I went with the second approach. With the Base conversion approach, even the maximum value of Long produces 10 characters which is still somewhat easy to remember. 
> There is a dependency from Google named Guava that could be used here to generate hashes. Although murmur_3_32 hash implemented in Guava was generating up to 10 characters long string, I left it for future testing and evaluation.

# Future Enhancements / Known Issues
* Since the project is for demo purpose only, Passwords are in plaintext. Will consider using Jasypt to encrypt the password in future
* Haven't implemented Front-end application yet
* Faced issues with auto schema generation through JPA, so delegated the schema creation to Docker container
* Faced issues with api container not being able to get connection while mysql container was being set up, so added `?autoReconnect=true&failOverReadOnly=false&maxReconnects=10&useSSL=false` to datasource url in application.properties. It slows down the application startup. You may remove that part if you want.

# Contributors
email: ahmad.zeeshaan@gmail.com

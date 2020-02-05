# URL Shortner
Spring Boot based REST API that takes a URL and returns a shortened URL and uses MySQL to persist data.

# Getting Started

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
Provides the configuration for containers to host API and MySql.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/gradle-plugin/reference/html/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data Redis (Access+Driver)](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/htmlsingle/#boot-features-redis)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)


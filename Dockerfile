From openjdk:8
MAINTAINER ahmad.zeeshaan@gmail.com
ADD build/libs/url-shortener-0.0.1-SNAPSHOT.jar url-shortener.jar
EXPOSE 8080
CMD ["java","-jar","url-shortener.jar"]
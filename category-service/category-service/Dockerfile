FROM java:8-jdk-alpine

COPY ./target/category-service-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch category-service-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","category-service-0.0.1-SNAPSHOT.jar"]  
FROM java:8-jdk-alpine

COPY ./target/oauth-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch oauth-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","oauth-server-0.0.1-SNAPSHOT.jar"]  

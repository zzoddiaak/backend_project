FROM maven:3.9.6-sapmachine-17 as build

COPY pom.xml .
COPY src src

RUN mvn clean install -DskipTests

FROM tomcat:jdk17-openjdk-slim-buster

COPY --from=build target/Senla-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

CMD ["catalina.sh","run"]
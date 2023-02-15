FROM maven:3-openjdk-17 as build
WORKDIR /home/app
COPY pom.xml pom.xml
COPY api/pom.xml api/pom.xml
COPY application/pom.xml application/pom.xml
COPY infrastructure/pom.xml infrastructure/pom.xml
COPY domain/pom.xml domain/pom.xml
RUN mvn clean install -DskipTests
COPY . .
RUN mvn package -DskipTests

FROM openjdk:17-alpine as runtime
COPY --from=build /home/app/api/target/api-1.0-exec.jar /usr/local/lib/app.jar
CMD ["java","-jar","/usr/local/lib/app.jar"]
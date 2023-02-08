from maven:3-openjdk-17 as build
WORKDIR /home/app
COPY pom.xml /home/app/pom.xml
RUN mvn clean install -DskipTests
COPY src /home/app/src
RUN mvn package -Dmaven.test.skip

FROM openjdk:17-alpine as runtime
COPY --from=build /home/app/target/advanced-challenge-1.0.jar /usr/local/lib/advanced-challenge.jar
CMD ["java","-jar","/usr/local/lib/advanced-challenge.jar"]
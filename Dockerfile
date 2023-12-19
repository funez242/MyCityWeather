FROM openjdk:17-oracle AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:17-oracle
EXPOSE 8080
WORKDIR app-wd
COPY --from=build target/*.jar myweatherapp.jar
ENTRYPOINT ["java","-jar","myweatherapp.jar"]
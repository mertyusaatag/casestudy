FROM amazoncorretto:17 AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package


FROM amazoncorretto:17
WORKDIR casestudy
COPY --from=build target/* casestudy.jar
ENTRYPOINT["java","-jar","casestudy.jar"]

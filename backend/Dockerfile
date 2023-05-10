FROM amazoncorretto:17 AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package -Dmaven.test.skip=true




FROM amazoncorretto:17
WORKDIR casestudy
COPY --from=build target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

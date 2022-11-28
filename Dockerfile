# FROM openjdk:17
# ARG JAR_FILE=out/artifacts/jcrm_dnio_project_2_jar/jcrm_dnio_project_2.jar
# COPY ${JAR_FILE} app.jar
# ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM eclipse-temurin:19-jdk-jammy

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]
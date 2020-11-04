## docker build -t count-server-3 .
## docker run -d -p 8080:8080 --name count-server count-server-3
FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
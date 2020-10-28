## docker build -t count-server-1 .
## docker run -d -p 8081:8080 count-server-1
FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
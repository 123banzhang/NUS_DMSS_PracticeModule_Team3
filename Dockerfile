FROM openjdk:17
ARG JAR_FILE
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
RUN ls /
RUN echo "JAR_FILE is set to: $JAR_FILE"
FROM openjdk:11
WORKDIR /user/src/nabe
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/user/src/nabe/app.jar"]
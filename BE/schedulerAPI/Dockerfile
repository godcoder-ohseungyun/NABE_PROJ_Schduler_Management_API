FROM openjdk:11
WORKDIR /user/src/navi
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/user/src/navi/app.jar"]
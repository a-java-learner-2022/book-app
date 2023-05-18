FROM openjdk:8-jdk-alpine

#pulling maven in container from docker hub
FROM maven:alpine

LABEL java learner <ajavalearner2022@gmail.com>

#making work directory in docker container
WORKDIR /rab3tech

#copying all project files inside docker container (rab3tech)
COPY . /rab3tech

#it will generate a clean package of the project 
CMD mvn clean package -DskipTests

#copying the jar file from host machine to the docker container
#COPY target/book-app-0.0.1-SNAPSHOT.jar /rab3tech/book-app-0.0.1-SNAPSHOT.jar

RUN CP target/book-app-0.0.1-SNAPSHOT.jar book-app-0.0.1-SNAPSHOT.jar

EXPOSE 8080

#to run the project in docker container, we have to use ENTRYPOINT keyword. 
ENTRYPOINT ["java","-jar","book-app-0.0.1-SNAPSHOT.jar"]
FROM openjdk:17-alpine

MAINTAINER Kenan Atasoy kenanatasoy@outlook.com

EXPOSE 8081

ADD target/studentgrademanagement-0.0.1-SNAPSHOT.jar student-grade-app.jar

ENTRYPOINT ["java","-jar","student-grade-app.jar"]

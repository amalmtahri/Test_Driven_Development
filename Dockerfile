FROM openjdk:11
EXPOSE 8080
ADD target/TDD.jar TDD.jar
ENTRYPOINT ["java","-jar","/TDD.jar"]
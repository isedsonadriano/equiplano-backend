FROM openjdk:8-jdk-alpine
ADD target/secPlano-0.0.1-SNAPSHOT.jar springboot.jar
ENTRYPOINT ["java", "-jar", "springboot.jar"]
FROM openjdk:24
COPY ./target/TestProject-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "TestProject-1.0-SNAPSHOT-jar-with-dependencies.jar"]
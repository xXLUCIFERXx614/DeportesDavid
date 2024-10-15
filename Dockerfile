FROM openjdk:17
COPY ./target/MongoProyectoDavidDeportes-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8034
ENTRYPOINT [ "java", "-jar", "app.jar" ]
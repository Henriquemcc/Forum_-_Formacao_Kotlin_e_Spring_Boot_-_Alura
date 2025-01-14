FROM eclipse-temurin:21
EXPOSE 8080
ADD target/forum-0.0.1-SNAPSHOT.jar forum.jar
ENTRYPOINT ["java", "-jar", "forum.jar", "-Dspring.profiles.active=prod", "-Dserver.port=$PORT"]
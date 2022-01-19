FROM openjdk:11
WORKDIR /app
COPY target/learning_english_appv1-0.0.1-SNAPSHOT.jar app/app.jar
ENTRYPOINT ["java","-jar","app/app.jar"]

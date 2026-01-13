FROM eclipse-temurin:21-jdk

COPY target/aalife-api-0.0.1-SNAPSHOT.jar /api/aalife.jar

ENTRYPOINT ["java","-jar","/api/aalife.jar"]
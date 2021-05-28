FROM openjdk:11-jdk-slim

ADD ./target/follow-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/follow-service.jar"]

EXPOSE 8066
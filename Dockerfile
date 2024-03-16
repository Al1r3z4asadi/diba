FROM openjdk:18-jdk-alpine3.14

WORKDIR /app

COPY . .

RUN ./gradlew clean build  --


EXPOSE 9092

CMD ["java", "-jar", "build/libs/beneficiary.jar"]



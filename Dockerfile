FROM ubuntu:latest
LABEL authors="slippery"

WORKDIR /app
COPY src ./src
COPY .mvnw/ .mvn
RUN ./mvnw dependency:go-offline

CMD["./mvnw","spring-boot:run"]

ENTRYPOINT ["top", "-b"]
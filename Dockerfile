FROM eclipse-temurin:11-jdk AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:17-jdk-alpine
COPY --from=build /home/app/target/challenge-1.0.0.jar challenge-1.0.0.jar

# Install Flyway
ENV FLYWAY_VERSION 7.14.0
RUN apk add --no-cache bash curl && \
    mkdir -p /flyway && \
    curl -L https://repo1.maven.org/maven2/org/flywaydb/flyway-commandline/${FLYWAY_VERSION}/flyway-commandline-${FLYWAY_VERSION}.tar.gz | tar xvz --strip-components=1 -C /flyway && \
    ln -s /flyway/flyway /usr/local/bin/flyway && \
    apk del curl && \
    rm -rf /var/cache/apk/*

ENTRYPOINT ["sh", "-c", "flyway migrate && java -jar challenge-1.0.0.jar && mvn -f /home/app/pom.xml test"]
EXPOSE 8080



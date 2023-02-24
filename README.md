# Challenge

In this repo you will find all of the projects code and associated files. Unfortunatly due to time constraint i wasn't able to fullfill all of the requirements of the challenge ..which i will go more in depth in a bit.

# How to run

Like mentioned previously i wasn't able to fullfill all of the requirements, one of them being able to run the entire project out of one command, i am using flyway to execute the database migrations, and although i tinkered with some settings in the dockerfile and docker-compose.yml i wasn´t able to get it running properly. I will leave some samples of what i tried at the end of this file.

Run the following commands in the command line:

    - mvn clean install 
    - docker build -t challenge-1.0.0.jar .
    - docker-compose up
    
After booting up the project, Hibernate should automatically create the tables based on the entity classes (models) that I have defined in the application.  
 
    - mvn flyway:migrate
   
This is the necessary command to execute the database migration through flyway, since i wasn't able to do it automatically in docker. After this the database should be populated with data.

Also for running tests, this is the correct command

    - mvn test


# curl instructions

For the endpoints specified in the challenge sheet:

Returns a list of alll of the consultations a patient has, and relative data
    - curl http://localhost:8090/challenge/consultations/patientConsultations?patientId=1
    
Schedules a consultation between a doctor with a determined speciality and a patient
    - curl http://localhost:8090/challenge/consultations/addConsultation?patientId=6&doctorId=3&specialtyId=3
    
Retrieves specialities that have more than 2 unique patients
    - curl http://localhost:8090/challenge/consultations/specialtiesWithMoreThan2Patients
    
Retrieves the data of all the patients with the requested features (example sorting by age = 25)
    - curl http://localhost:8090/challenge/patients/allPatients?page=0&size=5&&sort=-name&age<24
    
    
All the other endpoint work aswell, i wrote the entire 4 CRUD operations only for the Patients class, the others would have been the same, altough most of them have a get/{id} and a post endpoint.


# Difficulties during the challenge

By far the most hindering part of the challenge was the time constraint, i feel like i could have implemented everything in it's own time but since i haven't touched java spring in 2 years and some of the requirements we're some stuff i haven't had experience yet i was a bit slow in setting up tools and technologies.

My first blockade was when setting up flyway. It couldn't reach my migrations/db directory because of a dependency it had in pom.xml that was replicated by a line i can't remember in my application.properties. The error message wasn't descriptive enough so it took me some precious time to find the problem (it was one of those annoying bugs to find for sure), and even after fixing it at first it didn't migrate my data as it thought my schema was always unchanged. so i had to change some stuff with hibernate too.

In the end i think i shouldn't have elaborated on those extra endpoint that exist in every controller and just focused on the 4 ones required, so i could have more time to implement the rest of the requirements, but at the time i didn't think it would take me so long and too much trouble to integrate them into my project.

Like i mentioned previously docker also took up a lot of my time, and in the end i wasn't able to fix it in time, in the config i was writing for the docker file i was trying to set up flyway and mvn so it could run the migrations and the tests when booting up, i will leave that file below

I also started with the Open API documentation by adding the dependency to the pom.xml but i didn't get too far as i still wanted to clean up the project a bit, i undertand that i could use spring annotations in my endpoints to generate the documentation that then could accessed through swagger.

For the containerized centralized logging i was thinking of using graylog with log4j as the logging framework, which could configure all log messages with INFO level or higher to go to the Graylog appender, which in turn would send the log messages to the Graylog server. But alas i didn't get that far in the project before time ran out.

#Docker file sample

    FROM eclipse-temurin:11-jdk AS build
    COPY src /home/app/src
    COPY pom.xml /home/app
    RUN mvn -f /home/app/pom.xml clean package

    FROM openjdk:17-jdk-alpine
    COPY --from=build /home/app/target/challenge-1.0.0.jar challenge-1.0.0.jar

    Install Flyway
    ENV FLYWAY_VERSION 7.14.0
    RUN apk add --no-cache bash curl && \
        mkdir -p /flyway && \
        curl -L https://repo1.maven.org/maven2/org/flywaydb/flyway-commandline/${FLYWAY_VERSION}/flyway-commandline-${FLYWAY_VERSION}.tar.gz | tar xvz --strip-       components=1 -C /flyway && \
        ln -s /flyway/flyway /usr/local/bin/flyway && \
        apk del curl && \
        rm -rf /var/cache/apk/*

    ENTRYPOINT ["sh", "-c", "flyway migrate && java -jar challenge-1.0.0.jar && mvn -f /home/app/pom.xml test"]
     EXPOSE 8080

# Database ER diagram

![image](https://user-images.githubusercontent.com/38355244/221296793-fed705c2-9221-44be-b49c-029b5889af4c.png)

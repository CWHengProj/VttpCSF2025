#Stage 1 - creation of the angular package
FROM node:23 AS angularpackage
#create a work directory to use - set the current position to here
WORKDIR /client
# get the necessary files to build

COPY client/src src
COPY client/*.json .

RUN npm ci
RUN npm i -g @angular/cli
RUN ng build

#Stage 2 - creation of the spring boot package
FROM openjdk:23 AS springbootpackage
WORKDIR /server
#copy from angular package the built package into the static folder
COPY --from=angularpackage client/dist/client/browser src/main/resources/static
COPY server/src src
COPY server/mvnw .
COPY server/pom.xml .
COPY server/.mvn .mvn
COPY server/mvnw .
RUN chmod a+x mvnw
RUN mvnw package -Dmaven.test.skip=true

#Stage 3 - execution of the jar file
FROM openjdk:23
WORKDIR /app

COPY --from=springbootpackage /server/target/server-0.0.1-SNAPSHOT.jar app.jar
COPY data data

ENV PORT=8080
EXPOSE ${PORT}
ENTRYPOINT java -jar app.jar
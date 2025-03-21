FROM node:20-alpine AS angularpackage
WORKDIR /client
RUN npm i -g @angular/cli@19.1.8
COPY client/angular.json .
COPY client/package.json .
COPY client/tsconfig.json .
COPY client/tsconfig.app.json .
COPY client/src src
RUN npm i
RUN npm ci
RUN ng build

FROM eclipse-temurin:23-jdk-alpine AS springbootpackage
WORKDIR /server
COPY server/pom.xml .
COPY server/.mvn .mvn
COPY server/mvnw .
COPY server/mvnw.cmd .
COPY server/src src
RUN mkdir -p src/main/resources/static
COPY --from=angularpackage /client/dist/client/browser src/main/resources/static/
RUN chmod a+x mvnw
RUN ./mvnw package -Dmaven.test.skip=true

FROM eclipse-temurin:23-jre-alpine
WORKDIR /app
COPY --from=springbootpackage /server/target/*.jar app.jar
ENV PORT=8080
EXPOSE ${PORT}
ENTRYPOINT [ "java", "-jar", "app.jar"]
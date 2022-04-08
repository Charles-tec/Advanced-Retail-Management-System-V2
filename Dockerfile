ARG BUILD_IMAGE=maven:3.8.4
ARG RUNTIME_IMAGE=openjdk:17-slim-buster@sha256:5841b892346ab01d429b6f252be2f7a0aebb7de4531e3121806beee9886e7277
#############################################################################################
###                Stage where Docker is pulling all maven dependencies                   ###
#############################################################################################
FROM ${BUILD_IMAGE} as dependencies
ARG PROXY_SET=false
ARG PROXY_HOST=
ARG PROXY_PORT=
COPY pom.xml ./
RUN mvn -B dependency:go-offline \
        -DproxySet=${PROXY_SET} \
        -DproxyHost=${PROXY_HOST} \
        -DproxyPort=${PROXY_PORT}
#############################################################################################
#############################################################################################
###              Stage where Docker is building spring boot app using maven               ###
#############################################################################################
FROM dependencies as build
ARG PROXY_SET=false
ARG PROXY_HOST=
ARG PROXY_PORT=
COPY src ./src
RUN mvn -B clean package \
        -DproxySet=${PROXY_SET} \
        -DproxyHost=${PROXY_HOST} \
        -DproxyPort=${PROXY_PORT}
#############################################################################################
#############################################################################################
###                Optional stage where Docker is running Sonar analysis                  ###
#############################################################################################
#############################################################################################
#############################################################################################
### Stage where Docker is running a java process to run a service built in previous stage ###
#############################################################################################
FROM ${RUNTIME_IMAGE}
COPY --from=build target/*.jar /app/service.jar
ENTRYPOINT ["java", "-jar", "/app/service.jar"]
EXPOSE 9191
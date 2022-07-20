### BUILD image
FROM maven:3-jdk-8 as builder
#Copy Custom Maven settings
#COPY settings.xml /root/.m2/
# create app folder for sources
RUN mkdir -p /build
WORKDIR /build
COPY pom.xml /build
#Copy source code
COPY src /build/src
# Build application
RUN mvn clean package 

FROM tomcat:8.0.51-jre8-alpine
EXPOSE 8080
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=builder /build/target/*.war /usr/local/tomcat/webapps/wowment.war
RUN mkdir -p /usr/local/tomcat/files/sample
CMD ["catalina.sh","run"]
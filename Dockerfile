FROM docker.io/openjdk:11-jre-slim
ENV JAVA_HOME /usr/local/openjdk-11
ENV PATH $PATH:$JAVA_HOME/bin/
EXPOSE 8080
ADD target/weather-docker.jar weather-docker.jar
ENTRYPOINT ["java","-jar","/weather-docker.jar"]

FROM docker.io/mysql:5.7.27
WORKDIR /
COPY my.cnf /tmp/
WORKDIR .
ADD init-weather-app.sql /docker-entrypoint-initdb.d/

RUN cat /tmp/my.cnf >> /etc/mysql/my.cnf && rm -f /tmp/my.cnf && ulimit -d 256000

EXPOSE 3306
MAINTAINER ABHINAV
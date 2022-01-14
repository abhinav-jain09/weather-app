@echo off
mvn clean install -Denv=docker & ^
set denv=%1 & ^
docker-compose up
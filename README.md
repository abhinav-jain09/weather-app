Spring Boot Coding Dojo
---

Welcome to the Spring Boot Coding Dojo!

## Introduction

This is a simple application that requests its data from [OpenWeather](https://openweathermap.org/) and stores the result in a database. 

## prerequisite 
 - Docker  version in https://docs.docker.com/desktop/ 
 - Maven https://maven.apache.org/download.cgi
 
 

## Code Source code location
Source code can be downloaded from[Git hub location](https://github.com/abhinav-jain09/coding-dojo-spring-boot.git)

###In the nutshell all you need just one commands from project directory

1. Mac or Unix user
 sh startapp.sh args
 -arg 
   
     --local
     --test
     --acc
     --prod
     
 Example sh startapp.sh local
 

2. Windows user
    startapp.bat args
    -arg 
       
         --local
         --test
         --acc
         --prod
  
  Example- startapp.bat local

Or if the above command does not work for any reason you can run the following two commands 
1. mvn clean install -Denv=docker
2. denv="local" docker-compose up

## How to Test it
- Hit the url on the bowser http://localhost:8080/weather?city=Amsterdam
you can replace Amsterdam with any city 
Or
-This application is configured with Swagger. you can access it via url http://localhost:8080/swagger-ui.html


  
##To build the source code there run the following command from the project directory
-mvn clean install -Denv=docker


Above command will build three docker images you dont have to do anything everything is configured by this command
1. Mysql image 
 -Create Database
 -Create user
 -grant permission to user.
 
2. Application Image
  -JDK 
  -11
  -Jar directory configuration
3. Soap Ui images - This image is not used in the project yet but its for future use to mock the response at the moment
mock server is used for integration testing.
  - This image will configure SoapUI and start the mock server inside it. you can test it by the following url http://localhost:8089/weather?city=Amsterdam This will give you response from the soapUI.
  

##To make the application up and running run the following command from the project  directory
 denv=arg docker-compose up
 
  -arg 
  
    --local
    --test
    --acc
    --prod
 Example : denv="local" docker-compose up

 The above command will do the following job
 
 1. Bring up the MySql Image
 2. Bring up the application
  - Application will run the liquibase script and create the database tables
  - Bring up the mockserver and run the Integration Test.
  - Bring up the application server 


##Basic troublesoting 

Port are not available error .To kill the port 8080 first we need to find the process id and then kill the process which is running on port
lsof -i tcp:8080 -s tcp:listen 
Kill -9

Sometime when you restart the application the script want to replace the container like this 
Continue with the new image? [yN] 

Please press  "y" to fix the problem 
##Enhancement 
-It is not know the where this application could be deployed so the drive to save the data of database is not mount.
-All the property file uses the same APPID which can be configured based on the requirment 
-The password and username of the database is defined in the property file. Based on the production enviroment it can be configured on keyvault.
-your suggestion is valuable feel free to send email abhinav.jain09@gmail.com

##Footnote
It's possible to generate the API key going to the [OpenWeather Sign up](https://openweathermap.org/appid) page.



@echo off
setlocal

:: Check if an argument is supplied
if "%~1"=="" (
    echo No arguments supplied. Possible arguments are: local, test, acc, prod.
    echo Example: startup.bat local
    exit /b 1
)

:: Assign the first argument to denv
set denv=%1

echo Stopping and removing existing containers...
docker-compose down -v --remove-orphans

:: Remove old weather-app container if it exists
docker ps -a --format "{{.Names}}" | findstr /C:"weather-app" >nul
if %ERRORLEVEL%==0 (
    echo Removing existing weather-app container...
    docker rm -f weather-app
)

echo Removing unused Docker images...
docker image prune -af

echo Building and starting all containers...
docker-compose up --build -d

if %ERRORLEVEL% NEQ 0 (
    echo Docker Compose build failed! Exiting...
    exit /b %ERRORLEVEL%
)

echo Waiting for MySQL to be ready...
timeout /t 20 /nobreak

echo Showing running containers...
docker ps

echo Showing application logs...
docker logs -f weather-app

endlocal

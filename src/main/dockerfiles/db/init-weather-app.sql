CREATE DATABASE IF NOT EXISTS weather;
USE weather;
CREATE USER 'appuser'@'%' IDENTIFIED BY '@carlo#100';

GRANT ALL PRIVILEGES ON  weather.* TO 'appuser'@'%';
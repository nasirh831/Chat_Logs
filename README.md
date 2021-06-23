# Chat_Logs
Chat Logs Server
The goal of the application is to create a web application with Spring boot, hibernate and MySQL.

Tools and Technologies used
Java 1.8
Spring boot
Hibernate 
MySQL 
Eclipse 
Steps to install
1. Clone the application

2. Create MySQL database

CREATE DATABASE chat

3. Create table or run the sql script file

CREATE DATABASE chat;

USE chat;

CREATE TABLE chats
(
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    USER VARCHAR(255) NOT NULL,
    message VARCHAR(255),
    issend VARCHAR(255),
    TIMESTAMP BIGINT
);


4. Change MySQL username and password as per your installation

open src/main/resources/application.properties file.

change spring.datasource.username and spring.datasource.password as per your installation

5. Run the app



# Spring Boot aplication that exposes Reactive REST APIs with mongoDb database
Sample CRUD application that exposes Reactive REST APIs, based on Spring Boot, MongoDb, Spring Boot WebFlux.
# Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
# Prerequisites
* Java JDK 17
* MongoDb database set up, connection and sample table for testing.
# Database Setup
* Install mongoDb on your machine.
* After installing, start mongoDb deamon.
* Server listens on port 27017.
* In the UI application that is going to be installed, create a new database, and create Table/Document called books.
* Enter some test data in the table.
# Demo
When application is started, the server will listen on port 8080. <br>
Reactive REST endpoints:
* GET http://localhost:8080/api/books <br>
It will list all the books in the table. <br>
* GET http://localhost:8080/api/books/{_id} <br>
_id field is the primary key in this demo. It is uuid for every record. This will list us one specific book. <br>
* POST http://localhost:8080/api/books <br>
This is for creating a new book. We need to provide fields: id, title and author. The uuid (_id) will be generated authomatically. <br>
* PUT http://localhost:8080/api/books/{_id} <br>
This is for updating one specific book. Same as above, provide id, title and author in body, and uuid in url. <br>
* DELETE http://localhost:8080/api/books/{_id} <br>
This is for deleting one specific book. <br>

# Portfolio Project: office-creation-reservation

## Description
This application can be used by companies, which have one or more offices 
and need a reservation system for its rooms.

The application provides the functionality of creating multiple offices and
to each office one or more rooms can be added. A room can then be reserved by 
selecting a date and choosing a period of time.

## Tech Stack
- Java
- Spring Boot
- JPA/Hibernate
- MySQL
- HTML
- Thymeleaf
- CSS
- Maven

## Running the application
### Prerequisites
- MySQL (Community) Server
- MySQL Workbench or MySQL Shell

### Steps
1. Clone the repository
2. Setup for connection between MySQL database and application
   1. Open the project file `src/main/resources/application.properties`
   2. Give the username and password of the MySQL Connection by replacing the values of the
      properties `spring.datasource.username` and `spring.datasource.password`
   3. If the port of the MySQL Connection is another than the default port `3306`, adapt the value
      of the property `spring.datasource.url` by replacing `3306` with the port of the MySQL Connection
3. Create the database `db_office_creation_reservation` by running the following command

       create database db_office_creation_reservation;
4. To build and run the application continue either with Option 1, using an IDE or with Option 2, using
   the Command Line

   #### Option 1: IDE
      1. Open the project in your favourite IDE
      2. Run the application (the main() method is located in the file OfficeCreationReservationApplication)
      3. Visit the following URL `http://localhost:8080` and create your first office

   #### Option 2: Command Line
      1. Open the Command Prompt and move into the project directory
      2. Run the application by running the following command 

             mvnw spring-boot:run
      3. Visit the following URL `http://localhost:8080` and create your first office

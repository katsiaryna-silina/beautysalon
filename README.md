# Beauty salon for cats

This is the last project of EPAM java web development course.

---

## Technologies

- Java version: 17
- Maven
- Tomcat 10
- MySQL database
- Liquibase
- JUnit 5 with Mockito
- Docker & docker-compose
- JavaDock
- MVC
- Servlet
- JSP
- JS

---

## Roles and functionality

| Action                                                                    | GUEST | USER | ADMIN |
|:--------------------------------------------------------------------------|:-----:|:----:|:-----:|
| View main page                                                            |   +   |  +   |   +   |
| View information about services.                                          |   +   |  +   |   +   |
| Change language.                                                          |   +   |  +   |   +   |
| Registration                                                              |   +   |  -   |   -   |
| LogIn                                                                     |   +   |  -   |   -   |
| LogOut                                                                    |   -   |  +   |   +   |
| View profile.                                                             |   -   |  +   |   +   |
| Delete registered user if there are no orders.                            |   -   |  +   |   +   |
| Change password                                                           |   -   |  +   |   +   |
| Update feedback                                                           |   -   |  +   |   +   |
| Create new order:<br/>pick services, date, visit time.                    |   -   |  +   |   +   |
| View my orders                                                            |   -   |  +   |   +   |
| View all users orders                                                     |   -   |  -   |   +   |
| Update order status on "declined by client".                              |   -   |  +   |   -   |
| Update order status on "declined by admin",<br/>"confirmed", "completed". |   -   |  +   |   -   |
| View all users information.                                               |   -   |  -   |   +   |
| Change user status.                                                       |   -   |  -   |   +   |
| Delete services from user's select.                                       |   -   |  -   |   +   |

---

## Setup

- Install JRE
- Install Maven
- Install Tomcat 10
- Install Docker + docker-compose and run the following command **docker-compose up -d**
- Build the project with maven with the following command **mvn clean install**
- To update database state run liquibase command **mvn liquibase:update**
- For javadoc generation **mvn site**
- Go to **target** directory and copy beautysalon.war to the Apache/Tomcat/webapps directory
- Run Tomcat 10 server if it is not running:
    - open console in directory Apache\Tomcat\bin
    - execute following command **startup.bat**
- Go to the address in your browser **localhost:8080/beautysalon/**

```
docker-compose up -d
mvn clean install
mvn liquibase:update

startup.bat
```

---

## Database schema

Scheme based on MySQL

![database](https://github.com/katsiaryna-silina/beautysalon/blob/develop/src/main/webapp/image/salon_database.png?raw=true)

---

## Fulfilled requirements

+ #### Database Requirements:
    + Part of the data in the database is stored in Cyrillic, it is recommended to use utf-8 encoding.
    + JDBC-only database access technology.
    + To work with the database, the application must implement a thread-safe pool connections, the use of synchronized
      and volatile is prohibited.
    + It is recommended to use at least 6 tables.
    + Work with data in the application through DAO templates.
    + Implement protection against sql injection.


+ #### Basic application requirements:
    + The application interface must be localized; choice of languages: EN|RU etc.
    + The application must correctly handle the exceptions. Use Log4J2/SLF4J as a logger.
    + Classes and other entities of the application should be well structured according to packages and have a name
      reflecting their functionality.
    + When implementing the business logic of the application, you should, if necessary, use design patterns (GoF
      patterns: Command, Builder, Singleton, Proxy etc).
    + To store user information between requests, use the Session.
    + To intercept and correct request objects (request) and response (response) apply filters.
    + When implementing JSP pages, use JSTL library tags.
    + The use of scriptlets is prohibited.
    + When implementing the user interface, you are allowed to use any front-end development technologies (js, AJAX).
    + Implement protection against cross site scripting (xss).
    + Implement protection against repeated query execution by pressing F5.
    + Implement your own tags.
    + Viewing "long lists" should be organized in a page-by-page mode.
    + Validate input data on the client and on the server.
    + Documentation for the project must be prepared in accordance with the requirements of javadoc.
    + Code formatting must comply with the Java Code Convention.
    + When deploying an application, it is allowed to use Maven or Gradle technology.
    + The application must contain JUnit and Mockito tests.


+ #### Requirements for the functionality:
    + Authorization (sign in) and exit (sign out) to / from the system.
    + Registering a user and/or adding a subject.
    + Viewing information (for example: viewing all totalizator bets, statistics orders, invoices, etc.)
    + Removing information (for example: canceling an order, deleting an entity, etc.)
    + Adding and modifying information (for example: create and edit a product, create and edit an order, etc.)

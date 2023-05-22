# Employee CRM API

The back-end for a full-stack crm application that allows companies to keep track of their employees.

**Link to project:** https://employeecrm.vzmars.com/

**Front-End:** https://github.com/vzMars/employee-crm

![alt text](https://i.imgur.com/fgPhylk.png)

## How It's Made:

**Tech used:** Java, Spring Boot, Spring Security, Spring Session, Spring Data MongoDB

The back-end for this application was made using Java and Spring Boot. MongoDB was used as the database which stores all the users (companies) and their employees. I have created a User model that implements the UserDetails interface from Spring Security and an Employee Model both models use the @Document annotation and set the collection name in the database. I have also created a UserRepository and EmployeeRepository that both extend MongoRepository and provide methods that interact with MongoDB. I have also added my own methods to both repositories such as existsByUsernameOrEmail in the UserRepository which uses the @Query annotation that applies a collation with a locale of en and strength of 2 that helps prevent duplicate users from being added to the database.

Spring Security is used to help secure the application. The SecurityFilterChain sets up the application's cors configuration, and authorizes every request made to the application, and allows everyone to access /api/auth/** routes but requires users to be authenticated to access other routes. It also helps with session management and combined with Spring Session stores those sessions on MongoDB. I was also able to use Spring Security to configure a custom logout route that invalidates the http session and deletes the cookies from the client and removes the session from the database.

I have also added a global exception handler that can handle validation errors when the user signs up, when the user logs in, and when a user tries to add an employee to the database. The global exception handler can also handle custom exceptions that I have created such as when an invalid MongoDB id is provided, when an employee isn't found in the database, and when a user tries to sign up with a username that already exists in the database.

I have created an AuthService that is injected as a dependency into the AuthController and has methods for checking the authentication status which either sends a response with the current user or null, logging a user into the application and registering a new user. There is also an EmployeeService that is injected as a dependency into the EmployeeController and has methods for creating new employees, getting all employees that belong to the current user, getting a single employee that belongs to the current user, updating the status of an employee and deleting an employee.

## Optimizations:
I would like to try switching the database of this application from MongoDB to PostgreSQL to get more experience with using relational databases.

## Lessons Learned:
I learned how to create a secure Rest API using Java and Spring Boot and how to get it working with the front-end that was made using React. I also found data transfer objects (DTOs) to be really useful for validating what was sent by the client in the request body. DTOs were also really useful for making sure when the API sends a response back to the client no sensitive information is sent for example I created a UserInfo DTO that sends over JSON that has everything related to the user but with the password being removed.

## More Projects:

Take a look at these other projects that I have in my portfolio:

**GameBlog API:** https://github.com/vzMars/gameblog-api

**MyBookList API:** https://github.com/vzMars/mybooklist-api

**MangaNotifications:** https://github.com/vzMars/manga-notifications

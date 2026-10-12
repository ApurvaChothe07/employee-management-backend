\# Employee Management System



A REST API backend built with \*\*Java\*\* and \*\*Spring Boot\*\* to manage employee records, featuring JWT authentication and role-based access control.



\---



\## Features



\- Employee CRUD operations

\- User registration and login

\- JWT-based authentication

\- Password hashing using BCrypt

\- Role-based authorization

\- DTOs and request validation

\- Centralized exception handling

\- MySQL database integration

\- Environment-variable configuration for sensitive credentials



\---



\## Technologies Used



\- Java 21

\- Spring Boot

\- Spring Security

\- JSON Web Token (JWT)

\- Spring Data JPA and Hibernate

\- MySQL

\- Maven

\- Git and GitHub

\- Postman



\---



\## Prerequisites



\- JDK 21

\- Maven

\- MySQL Server

\- Postman or another API client



\---



\## Configuration



Set the following environment variables before running the application:



| Variable | Description |

|---|---|

| `DB\_URL` | MySQL JDBC URL |

| `DB\_USERNAME` | MySQL username |

| `DB\_PASSWORD` | MySQL password |

| `JWT\_SECRET` | Secret used to sign JWTs |



Default database URL:



```

jdbc:mysql://localhost:3306/employee\_management

```



Create the `employee\_management` database in MySQL if it does not already exist:



```sql

CREATE DATABASE employee\_management;

```



> \*\*Important:\*\* Never commit actual passwords or JWT secrets to GitHub.



\---



\## Running the Application



From the project root, run:



```bash

mvn spring-boot:run

```



The default server port is \*\*8081\*\*.



\---



\## API Endpoints



\### Authentication



| Method | Endpoint | Purpose |

|---|---|---|

| POST | `/api/auth/register` | Register a user |

| POST | `/api/auth/login` | Authenticate and obtain a JWT |



\### Employee Management



Employee operations are exposed through `/api/employees/\*\*` and are protected by Spring Security authorization rules.



> Use the exact routes implemented in the application's controllers.



For protected requests, send the token in the following header:



```

Authorization: Bearer <your-jwt-token>

```



\---



\## Authentication Flow



1\. A user registers with a username and password.

2\. The application hashes the password using BCrypt.

3\. The user logs in with valid credentials.

4\. The server generates a JWT.

5\. The client sends the token with protected requests.

6\. The JWT filter validates the token and establishes the authenticated security context.

7\. Spring Security checks authorization before allowing access.



\---



\## Error Handling



The application uses DTOs, request validation, and centralized exception handling to manage invalid requests and application errors.



\---



\## Future Improvements



\- \[ ] Automated unit and integration tests

\- \[ ] Swagger/OpenAPI documentation

\- \[ ] Docker deployment

\- \[ ] CI/CD pipeline

\- \[ ] Pagination and sorting



\---



\## Author



\*\*Apurva Chothe\*\*


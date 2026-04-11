# Banking Transaction API (Spring Boot)

A RESTful Banking Transaction API built with Java, Spring Boot and PostgreSQL.

This project demonstrates a clean backend architecture using layered design, DTO pattern, validation, transaction management, and global exception handling.

---

## Features

* Create bank accounts
* Deposit money into accounts
* Withdraw money from accounts
* Transfer money between accounts
* DTO-based request/response structure
* Input validation with Hibernate Validator
* Global exception handling
* Transaction management with `@Transactional`
* Swagger API documentation
* Clean layered architecture (Controller → Service → Repository)

---

## Architecture

The project follows a layered architecture:

Controller → Service → Repository → Entity → Database

* **Controller Layer:** Handles HTTP requests and API endpoints
* **Service Layer:** Contains business logic
* **Repository Layer:** Handles database operations via Spring Data JPA
* **Entity Layer:** Represents database tables
* **DTO Layer:** Separates API models from database models
* **Exception Handling:** Centralized error handling using `@RestControllerAdvice`

---

## Project Structure

```
com.serhat.bankingtransactionapi
│
├── controller
│   └── AccountController.java
│
├── service
│   └── AccountService.java
│
├── repository
│   └── AccountRepository.java
│
├── entity
│   └── Account.java
│
├── dto
│   ├── CreateAccountRequest.java
│   ├── AccountResponse.java
│   ├── DepositRequest.java
│   ├── WithdrawRequest.java
│   └── TransferRequest.java
│
├── exception
│   ├── AccountNotFoundException.java
│   └── GlobalExceptionHandler.java
│
└── BankingTransactionApiApplication.java
```

---

## Technologies

* Java 25
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Swagger (OpenAPI)

---

## Base URL

```
http://localhost:8080
```

---

## API Endpoints

| Method | Endpoint           | Description          |
| ------ | ------------------ | -------------------- |
| GET    | /accounts          | List all accounts    |
| GET    | /accounts/{id}     | Get account by ID    |
| POST   | /accounts          | Create a new account |
| POST   | /accounts/deposit  | Deposit money        |
| POST   | /accounts/withdraw | Withdraw money       |
| POST   | /accounts/transfer | Transfer money       |

---

## Example Request

### Create Account

```
POST /accounts
```

```json
{
  "accountNumber": "TR1001",
  "ownerName": "Serhat",
  "balance": 5000
}
```

---

## Example Response

```json
{
  "id": 1,
  "accountNumber": "TR1001",
  "ownerName": "Serhat",
  "balance": 5000,
  "createdAt": "2026-04-11T14:30:00"
}
```

---

## Database

* **Database:** PostgreSQL
* **Database Name:** banking_db
* **Table:** account

---

## Swagger UI

You can access and test the API via Swagger:

```
http://localhost:8080/swagger-ui.html
```

---

## How to Run

1. Clone the repository

```
git clone https://github.com/SerhatSerce/banking-transaction-api.git
```

2. Open in VS Code / IntelliJ

3. Configure PostgreSQL in `application.properties`

4. Run the application

```
BankingTransactionApiApplication.java
```

---

## Learning Outcomes

* Building REST APIs with Spring Boot
* Applying layered architecture
* Using DTO for clean API design
* Implementing validation and exception handling
* Managing transactions with `@Transactional`
* Integrating PostgreSQL with Spring Data JPA
* Documenting APIs using Swagger

---

## Author

Serhat

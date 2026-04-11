# Banking Transaction API (Spring Boot)

🔹 Backend-focused project simulating real-world banking operations

A production-ready RESTful Banking API built with Java, Spring Boot, and PostgreSQL.

This project demonstrates clean backend architecture using layered design, DTO pattern, validation, transaction management, and global exception handling.

---

## 🚀 Live Demo

**API Base URL:**
https://banking-transaction-api-production.up.railway.app

**Swagger UI:**
https://banking-transaction-api-production.up.railway.app/swagger-ui.html

> **Note:** The root URL does not return a homepage because this project is a backend REST API.
> Use Swagger UI or API endpoints directly.

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
* Cloud deployment using Railway
* RESTful API design principles
* Production-ready deployment

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
├── config
│   └── OpenApiConfig.java
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
│   ├── DuplicateAccountNumberException.java
│   ├── InsufficientBalanceException.java
│   └── GlobalExceptionHandler.java
│
└── BankingTransactionApiApplication.java
```

---

## Transaction Management

All money operations (deposit, withdraw, transfer) are handled using `@Transactional` to ensure data consistency.

If a transaction fails at any step, the entire operation is rolled back to prevent inconsistent balances.

---

## Example Flow: Money Transfer

1. Client sends transfer request
2. System validates accounts
3. Balance is checked
4. Money is withdrawn from sender
5. Money is deposited to receiver
6. Transaction is committed

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

```
POST https://banking-transaction-api-production.up.railway.app/accounts
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

## Error Response Example

```json
{
  "timestamp": "2026-04-11T14:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Insufficient balance",
  "path": "/accounts/withdraw"
}
```

---

## Database

* PostgreSQL (Railway managed service)
* Connection configured via environment variables (`PGHOST`, `PGPORT`, `PGDATABASE`, `PGUSER`, `PGPASSWORD`)
* Hibernate: `ddl-auto=update`

---

## Environment Variables

```
PGHOST=localhost
PGPORT=5432
PGDATABASE=banking_db
PGUSER=postgres
PGPASSWORD=yourpassword
```

---

## Technologies

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Swagger (OpenAPI)
* Railway (Cloud Deployment)
* REST API

---

## Limitations / Future Improvements

* JWT-based authentication & authorization
* Pagination and filtering for account listing
* Logging (SLF4J / Logback)
* Rate limiting & security enhancements
* Unit and integration tests

---

## How to Run

1. Clone the repository

```
git clone https://github.com/SerhatSerce/banking-transaction-api.git
```

2. Open in VS Code / IntelliJ

3. Configure PostgreSQL via environment variables

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
* Deploying backend applications to cloud (Railway)
* Documenting APIs using Swagger (OpenAPI)
* Configuring environment-based database connections

---

## Author

Serhat 🚀

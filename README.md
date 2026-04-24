# Banking Transaction API (Spring Boot)

🔹 Backend-focused project simulating real-world banking operations

A production-ready RESTful Banking API built with Java, Spring Boot, and PostgreSQL.

This project demonstrates clean backend architecture using layered design, DTO pattern, validation, transaction management, global exception handling, JWT authentication, logging, pagination, and unit testing.

🚀 Live Demo

API Base URL:
https://banking-transaction-api-production.up.railway.app

Swagger UI:
https://banking-transaction-api-production.up.railway.app/swagger-ui.html

Note: The root URL does not return a homepage because this project is a backend REST API. Use Swagger UI or API endpoints directly.

---

## Features

Create bank accounts  
Deposit money into accounts  
Withdraw money from accounts  
Transfer money between accounts  

JWT-based authentication & authorization  
DTO-based request/response structure  
Input validation with Hibernate Validator  
Global exception handling  
Transaction management with @Transactional  
Pagination support (page, size)  
Logging with SLF4J  
Unit testing with JUnit & Mockito  
Swagger API documentation  
Clean layered architecture (Controller → Service → Repository)  
Cloud deployment using Railway  
RESTful API design principles  
Production-ready backend practices  

---

## Architecture

The project follows a layered architecture:

Controller → Service → Repository → Entity → Database

Controller Layer: Handles HTTP requests and API endpoints  
Service Layer: Contains business logic  
Repository Layer: Handles database operations via Spring Data JPA  
Entity Layer: Represents database tables  
DTO Layer: Separates API models from database models  
Security Layer: JWT authentication & authorization  
Exception Handling: Centralized error handling using @RestControllerAdvice  

---

## Project Structure

com.serhat.bankingtransactionapi
│
├── config
│   ├── OpenApiConfig.java
│   └── SecurityConfig.java
│
├── controller
│   ├── AccountController.java
│   └── AuthController.java
│
├── service
│   ├── AccountService.java
│   └── AuthService.java
│
├── repository
│   ├── AccountRepository.java
│   └── UserRepository.java
│
├── entity
│   ├── Account.java
│   └── User.java
│
├── dto
│   ├── CreateAccountRequest.java
│   ├── AccountResponse.java
│   ├── DepositRequest.java
│   ├── WithdrawRequest.java
│   ├── TransferRequest.java
│   ├── AuthRequest.java
│   └── AuthResponse.java
│
├── exception
│   ├── AccountNotFoundException.java
│   ├── DuplicateAccountNumberException.java
│   ├── InsufficientBalanceException.java
│   └── GlobalExceptionHandler.java
│
└── BankingTransactionApiApplication.java

---

## Authentication (JWT)

This API uses JWT (JSON Web Token) for authentication.

Flow:

1. Register a user → /auth/register  
2. Login → /auth/login  
3. Receive JWT token  
4. Use token in requests  

Header format:

Authorization: Bearer <your_token>

---

## Transaction Management

All money operations (deposit, withdraw, transfer) are handled using @Transactional to ensure data consistency.

If a transaction fails at any step, the entire operation is rolled back.

---

## Example Flow: Money Transfer

Client sends transfer request  
System validates accounts  
Balance is checked  
Money is withdrawn from sender  
Money is deposited to receiver  
Transaction is committed  

---

## API Endpoints

### Auth

POST /auth/register → Register user  
POST /auth/login → Login & get JWT  

### Accounts

GET /accounts → List accounts (pagination supported)  
POST /accounts → Create account  
POST /accounts/deposit → Deposit money  
POST /accounts/withdraw → Withdraw money  
POST /accounts/transfer → Transfer money  

---

## Pagination Example

GET /accounts?page=0&size=10

---

## Example Request

POST https://banking-transaction-api-production.up.railway.app/accounts

{
  "accountNumber": "TR1001",
  "ownerName": "Serhat",
  "balance": 5000
}

---

## Example Response

{
  "accountNumber": "TR1001",
  "ownerName": "Serhat",
  "balance": 5000
}

---

## Error Response Example

{
  "timestamp": "2026-04-11T14:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Insufficient balance",
  "path": "/accounts/withdraw"
}

---

## Database

PostgreSQL (Railway managed service)

Connection configured via environment variables:

PGHOST  
PGPORT  
PGDATABASE  
PGUSER  
PGPASSWORD  

Hibernate: ddl-auto=update  

---

## Technologies

Java 17  
Spring Boot  
Spring Data JPA  
Hibernate  
PostgreSQL  
Maven  
Swagger (OpenAPI)  
JWT (Authentication)  
SLF4J (Logging)  
JUnit & Mockito (Testing)  
Railway (Cloud Deployment)  

---

## How to Run

Clone the repository

git clone https://github.com/SerhatSerce/banking-transaction-api.git

Configure PostgreSQL via environment variables

Run the application

./mvnw spring-boot:run

Open Swagger UI

http://localhost:8080/swagger-ui.html

---

## Learning Outcomes

Building REST APIs with Spring Boot  
Applying layered architecture  
Using DTO for clean API design  
Implementing validation and exception handling  
Managing transactions with @Transactional  
Implementing JWT authentication  
Writing unit tests with Mockito  
Using pagination in APIs  
Logging backend operations  
Deploying backend applications to cloud (Railway)  
Documenting APIs using Swagger  

---

## Author

Serhat 🚀
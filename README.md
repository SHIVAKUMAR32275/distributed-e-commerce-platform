# 🛒 Distributed E-Commerce Platform

A production-style backend E-Commerce application built using **Spring Boot**, **Spring Security**, **JWT Authentication**, and **Role-Based Access Control (RBAC)**. This project demonstrates enterprise backend development practices including authentication, authorization, layered architecture, exception handling, DTO mapping, and secure REST APIs.

---

## 🚀 Features

### 🔐 Authentication & Authorization
- User Signup
- User Login
- JWT Token Generation
- JWT Token Validation
- Logout
- BCrypt Password Encryption
- Role-Based Access Control (RBAC)
- Spring Security Integration

### 👥 User Management
- Create User
- Update User
- Delete User
- Get User Details
- Get All Users

### 🏷️ Category Management
- Create Category
- Update Category
- Delete Category
- View Categories

### 📦 Product Management
- Create Product
- Update Product
- Delete Product
- Get Product Details
- Get All Products

### 📊 Inventory Management
- Add Inventory
- Update Inventory
- Delete Inventory
- View Inventory

### 🛒 Cart Management
- Create Cart
- Update Cart
- Delete Cart
- View Cart

### 🛍️ Cart Item Management
- Add Cart Item
- Update Cart Item
- Delete Cart Item
- View Cart Items

### 📋 Order Management
- Create Order
- Update Order
- Delete Order
- View Orders

### 📑 Order Item Management
- Create Order Item
- Update Order Item
- Delete Order Item
- View Order Items

### 💳 Payment Management
- Create Payment
- Update Payment
- Delete Payment
- View Payments

### ⭐ Review Management
- Add Review
- Update Review
- Delete Review
- View Reviews

### 🔔 Notification Management
- Create Notification
- Update Notification
- Delete Notification
- View Notifications

### 📍 Address Management
- Create Address
- Update Address
- Delete Address
- View Addresses

---

# 🏗️ Architecture

The project follows a layered architecture:

```
Controller Layer
        ↓
Service Layer
        ↓
Repository Layer
        ↓
Database Layer
```

---

# 🔐 Role Based Access Control (RBAC)

The application supports two roles:

## ADMIN
Can perform:

- Product Management
- Category Management
- Inventory Management
- View Orders
- View Payments
- Manage Notifications

## CUSTOMER
Can perform:

- Signup/Login
- Manage Cart
- Place Orders
- Make Payments
- Add Reviews
- Manage Addresses

---

# 🧰 Technology Stack

| Technology | Version |
|------------|---------|
| Java | 17 |
| Spring Boot | 3.5.x |
| Spring Security | Latest |
| Spring Data JPA | Latest |
| MySQL | 8.x |
| JWT | 0.12.6 |
| Maven | Latest |
| Lombok | Latest |
| Swagger OpenAPI | Latest |
| Hibernate | Latest |

---

# 📂 Project Structure

```
src
 ├── controller
 ├── service
 ├── repository
 ├── Models
 ├── dtos
 ├── exceptions
 ├── security
 ├── configurations
 └── constants
```

---

# 🗄️ Database Entities

The application contains the following entities:

- User
- Role
- Session
- Address
- Category
- Product
- Inventory
- Cart
- CartItem
- Order
- OrderItem
- Payment
- Review
- Notification

---

# 🔑 Authentication Flow

```
Signup
   ↓
Password Encryption (BCrypt)
   ↓
Store User
   ↓
Login
   ↓
Generate JWT
   ↓
JWT Validation
   ↓
Access Protected APIs
```

---

# 🔒 Authorization Flow

```
Request
   ↓
JWT Authentication Filter
   ↓
Extract Role
   ↓
Spring Security Context
   ↓
@PreAuthorize Validation
   ↓
Allow / Deny Access
```

---

# 📖 API Documentation

Swagger UI:

```
http://localhost:8080/swagger-ui/index.html
```

OpenAPI Docs:

```
http://localhost:8080/v3/api-docs
```

---

# 🧪 API Testing

All APIs were tested using:

- Postman
- JWT Authentication
- RBAC Authorization
- CRUD Operations
- Exception Handling

---

# 🛡️ Security Features

- JWT Authentication
- BCrypt Password Encoding
- Spring Security
- Role-Based Authorization
- Method-Level Security
- Session Management

---

# ⚙️ Setup Instructions

## Clone Repository

```bash
git clone https://github.com/SHIVAKUMAR32275/distributed-e-commerce-platform.git
```

## Navigate

```bash
cd distributed-e-commerce-platform
```

## Configure Database

Update:

```properties
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
jwt.secret=
```

## Build

```bash
mvn clean install
```

## Run

```bash
mvn spring-boot:run
```

---

# 📸 Sample Roles

| Role |
|------|
| ADMIN |
| CUSTOMER |

---

# ✅ Implemented Features

- [x] Authentication
- [x] Authorization
- [x] JWT
- [x] RBAC
- [x] User Module
- [x] Address Module
- [x] Category Module
- [x] Product Module
- [x] Inventory Module
- [x] Cart Module
- [x] Cart Item Module
- [x] Order Module
- [x] Order Item Module
- [x] Payment Module
- [x] Review Module
- [x] Notification Module
- [x] Exception Handling
- [x] DTO Layer
- [x] Swagger
- [x] Spring Security

---

# 🚀 Future Enhancements

- Docker
- Redis Cache
- Kafka Notifications
- JUnit & Mockito
- CI/CD Pipeline
- AWS Deployment
- API Gateway
- Microservices Migration

---

# 👨‍💻 Author

**Vilasagarapu Shiva Kumar**

- Java Backend Developer
- Spring Boot Developer
- Software Engineer

GitHub:
https://github.com/SHIVAKUMAR32275

---

# ⭐ Project Status

✅ Completed  
✅ Tested using Postman  
✅ JWT Authentication Implemented  
✅ RBAC Implemented  
✅ Ready for GitHub Portfolio

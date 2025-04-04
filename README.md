# 🚀 Spring Boot Banking Microservices

A modern **Banking System** built with **Spring Boot Microservices** architecture that provides core banking functionalities such as **account management** and **transaction processing**.


---

## 📌 Table of Contents
- [✨ Features](#-features)
- [📂 Project Structure](#-project-structure)
- [🛠 Technologies Used](#-technologies-used)
- [⚡ Prerequisites](#-prerequisites)
- [📥 Setup and Installation](#-setup-and-installation)
- [▶️ Running the Application](#️-running-the-application)
- [🤝 Contributing](#-contributing)
- [📜 License](#-license)

---

## ✨ Features

1. **Account Service**: Manage customer accounts  
&nbsp;&nbsp;&nbsp;&nbsp;I. Create new accounts  
&nbsp;&nbsp;&nbsp;&nbsp;II. Retrieve account details  
&nbsp;&nbsp;&nbsp;&nbsp;III. Update account information  
&nbsp;&nbsp;&nbsp;&nbsp;IV. Delete accounts

2. **Transaction Service**: Process financial transactions  
&nbsp;&nbsp;&nbsp;&nbsp;I. Deposit funds  
&nbsp;&nbsp;&nbsp;&nbsp;II. Withdraw funds  
&nbsp;&nbsp;&nbsp;&nbsp;III. Transfer between accounts  
&nbsp;&nbsp;&nbsp;&nbsp;IV. View transaction history

3. **Service Discovery**: Dynamic service registration and discovery  
4. **API Gateway**: Centralized routing for all microservices  
5. **Custom Exception Handling**: Standardized error responses across services

---

## 📂 Project Structure

The project is structured as follows:

The project is organized into multiple microservices:

```
Spring-boot-Banking/
├── account-service/        # Handles account management operations
├── transaction-service/    # Manages transactions between accounts
├── discovery-server/       # Eureka service for service discovery
├── api-gateway/            # Gateway for routing requests
└── images/                 # Project images and diagrams
```

---

## 🛠 Technologies Used

| Technology             | Description |
|------------------------|-------------|
| **Spring Boot**        | Framework for building microservices |
| **Spring Cloud**       | Tools for distributed systems |
| **Spring Data JPA**    | Data persistence |
| **H2/MySQL/PostgreSQL** | Database options |
| **Eureka**             | Service discovery |
| **Feign Client**       | Service-to-service communication |
| **Spring Cloud Gateway** | API Gateway |
| **Maven**              | Dependency management and build |
| **JUnit & Mockito**    | Testing frameworks |

---

## ⚡ Prerequisites

Ensure you have the following installed:

✅ **JDK 11** or newer  
✅ **Maven 3.6.0** or newer  
✅ **Git**  
✅ **Preferred IDE**: IntelliJ IDEA / Eclipse / VS Code

---

## 📥 Setup and Installation

1. **Clone the Repository**

```bash
git clone https://github.com/yourusername/Spring-boot-Banking.git
cd Spring-boot-Banking
```

2. **Configure Database**

Each service has its own `application.properties` or `application.yml` file. Update these with your database configuration:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/bankingdb
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

3. **Build the Project**

```bash
mvn clean install
```
---

## ▶️ Running the Application

The services should be started in the following order:

1. **Start the Discovery Server**

```bash
cd discovery-server
mvn spring-boot:run
```

2. **Start the Account Service**

```bash
cd account-service
mvn spring-boot:run
```

3. **Start the Transaction Service**

```bash
cd transaction-service
mvn spring-boot:run
```

4. **Start the API Gateway**

```bash
cd api-gateway
mvn spring-boot:run
```

Once all services are running, you can access:

- Eureka Dashboard: http://localhost:8761
- API Gateway: http://localhost:8080
- Account Service (direct): http://localhost:8081
- Transaction Service (direct): http://localhost:8083



---

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📜 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

Created with ❤️ by Tanooj Reddy 
# Boilerplate Project: Multi-Tenant API with DDD and Spring

This project is a sample backend for a SaaS application that demonstrates the implementation of a robust **Multi-Tenant** architecture with data separation per **Schema**, organized under the principles of **Domain-Driven Design (DDD)**.

It serves as a boilerplate or template for starting new projects that require data isolation between different organizations, users, or 'tenants'.

-----

## Table of Contents

* [Core Concept](https://www.google.com/search?q=%23-core-concept)
* [Applied Architecture](https://www.google.com/search?q=%23-applied-architecture)
* [Tech Stack](https://www.google.com/search?q=%23-tech-stack)
* [Getting Started](https://www.google.com/search?q=%23-getting-started)
* [How to Use and Test the API](https://www.google.com/search?q=%23-how-to-use-and-test-the-api)
* [Package Structure (DDD)](https://www.google.com/search?q=%23-package-structure-ddd)
* [Contributing](https://www.google.com/search?q=%23-contributing)
* [License](https://www.google.com/search?q=%23-license)

-----

## 🎯 Core Concept

This project simulates the core of a generic service where multiple 'tenants' (organizations or users) can sign up to manage their own resources (e.g., projects, clients, documents, etc.). The main technical challenge is to ensure that a tenant's data is completely isolated and secure from others, even though everyone shares the same application instance.

-----

## 🏛️ Applied Architecture

The project was built upon two main architectural pillars:

### 1\. Multi-Tenancy: Schema-per-Tenant Model

The **Schema-per-Tenant** model was chosen, as it offers an excellent balance between security and cost-effectiveness.

* **How it works:** All tenants share the same PostgreSQL database server, but each has its own dedicated set of tables within a "schema" (e.g., `org_a.resources`, `org_b.resources`).
* **Advantage:** Isolation is guaranteed at the database level, providing a strong security barrier against accidental data leaks, which is superior to the shared-schema model.

### 2\. Domain-Driven Design (DDD)

To manage complexity and align the code with business rules, the project follows a layered architecture:

* **`Presentation`**: Responsible for interaction with the outside world (REST Controllers, HTTP Interceptors).
* **`Application`**: Orchestrates the application's use cases, acting as a thin layer between the Presentation and the domain.
* **`Domain`**: The heart of the software. It contains the entities, value objects, and pure business rules, with no dependencies on frameworks.
* **`Infrastructure`**: Contains the technical implementation details, such as persistence logic with Spring Data JPA, multi-tenancy configuration, and database migrations with Flyway.

-----

## 🛠️ Tech Stack

* **Java 21**
* **Spring Boot 3.3** (Web, Data JPA)
* **PostgreSQL 15** (Relational Database)
* **Docker & Docker Compose** (Database environment management)
* **Hibernate** (As the JPA provider for the Multi-Tenancy logic)
* **Flyway** (For versioning and migration of each tenant's schema)
* **Maven** (Dependency manager)
* **Lombok** (For boilerplate code reduction)

-----

## 🚀 Getting Started

### Prerequisites

* JDK 21 or higher
* Docker and Docker Compose
* Maven

### Execution Steps

1.  **Clone the repository:**

    ```bash
    git clone https://github.com/your-user/spring-ddd-multitenant.git
    cd spring-ddd-multitenant
    ```

2.  **Start the PostgreSQL database with Docker:**

    ```bash
    docker-compose up -d
    ```

    This will start a PostgreSQL container on port `5432`.

3.  **Run the Spring Boot application:**

    ```bash
    ./mvnw spring-boot:run
    ```

    The API will be available at `http://localhost:8080`.

-----

## 🔬 How to Use and Test the API

Use `cURL` or a tool like Postman/Insomnia to interact with the API.

### Step 1: Provision a New Tenant

First, create the schema and tables for a tenant named `org_a`.

```bash
curl -X POST http://localhost:8080/tenants/org_a
```

* **Expected Result:** A success message. Behind the scenes, Flyway has executed the migrations and created the tables within the `org_a` schema.

### Step 2: Create a Resource (Client) for the New Tenant

To interact with the tenant's resources, you **must** send the `X-Tenant-ID` header.

```bash
curl -X POST http://localhost:8080/api/clients \
-H "Content-Type: application/json" \
-H "X-Tenant-ID: org_a" \
-d '{"id": 1, "name": "First Client Inc.", "email": "contact@firstclient.com"}'
```

* **Expected Result:** A `201 Created` status with the data of the created client.

### Step 3: Test the Data Isolation

1.  Provision a second tenant: `curl -X POST http://localhost:8080/tenants/org_b`
2.  Create a client for it:
    ```bash
    curl -X POST http://localhost:8080/api/clients \
    -H "Content-Type: application/json" \
    -H "X-Tenant-ID: org_b" \
    -d '{"id": 100, "name": "Competitor Client", "email": "finance@competitor.com"}'
    ```
3.  List the clients for `org_a`. You will only see "First Client Inc.":
    ```bash
    curl -H "X-Tenant-ID: org_a" http://localhost:8080/api/clients
    ```
4.  List the clients for `org_b`. You will only see "Competitor Client":
    ```bash
    curl -H "X-Tenant-ID: org_b" http://localhost:8080/api/clients
    ```
    **Isolation has been successfully proven\!**

-----

## 📂 Package Structure (DDD)

The project structure reflects the layered architecture. (Example package name: `com.example.multitenantapp`)

```
com.example.multitenantapp
├── application     # Orchestrates use cases
│   ├── dto         # Data Transfer Objects
│   └── service     # Application services
├── domain          # The heart of the business logic
│   ├── model       # Entities and Value Objects
│   ├── repository  # Repository Presentations
│   └── service     # Domain services
├── infrastructure  # Technical details and implementations
│   ├── config      # Manual bean configuration (Hibernate, etc.)
│   ├── multitenancy# Multi-Tenancy logic (Resolver, Provider)
│   └── persistence # Repository implementations
└── presentation       # Entry layer (REST API)
    ├── rest
    │   ├── controller
    │   └── interceptor
    └── ...
```

-----

## 🤝 Contributing

Contributions are welcome\! Feel free to open an *issue* to report bugs or suggest improvements. If you wish to contribute with code, please open a *Pull Request*.

-----

## 📄 License

This project is licensed under the MIT License. See the `LICENSE` file for more details.
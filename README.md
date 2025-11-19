# Spring Demo Project (Entities: Company, Project, Employee, Address)

This repository contains a minimal Spring Boot project demonstrating:
- OneToOne Employee ↔ Address (cascade & orphanRemoval)
- ManyToMany Project ↔ Employee (no cascade remove)
- OneToMany Company → Project → Employee with cascade in Company (scenario variations)
- HQL queries and a simple Specification example
- Pagination and sorting example for employees
- An H2 in-memory database with preload SQL (data.sql)

## Run
Requires Java 11+ and Maven.

    mvn spring-boot:run

H2 console: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb

## Useful endpoints
- GET /api/companies/by-name?name=Acme%20Corp
- POST /api/companies
- DELETE /api/companies/{id}
- GET /api/projects/{id}/employees
- GET /api/projects/by-employee/{employeeId}
- GET /api/employees?page=0&size=5&sort=name,asc
- POST /api/employees
- DELETE /api/employees/{id}


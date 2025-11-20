# Spring Demo API

**Project:** Spring Boot REST API — Companies, Employees, Projects, Addresses

## Overview

A simple Spring Boot (3.3.x) REST API that models Companies, Employees, Projects and Addresses. Demonstrates JPA mappings (OneToOne, ManyToOne, ManyToMany), DTO/Mapper usage, service/controller layers, pagination & sorting, and database initialization with `data.sql`.

## Tech stack

* Java 21
* Spring Boot 3.3.x
* Spring Data JPA (Hibernate 6.x)
* MySQL (local)
* Maven

## Key features

* CRUD for Company, Employee, Project, Address
* Each Employee has one Address (OneToOne)
* Employee ↔ Project (ManyToMany)
* Company → Project → Employees (cascade / optional behavior configurable)
* Sample Postman collection included (see `postman_collection.json` in repo)
* `data.sql` for preloading base data
* Pagination & sorting for listing employees inside a project

---

## Quick setup

1. Clone the repo.
2. Configure MySQL and `application.properties` (example shown below).
3. Ensure an empty database exists (e.g., `week5`).
4. Build & run:

```bash
mvn clean package
java -jar target/week-5-0.0.1-SNAPSHOT.jar
```

### Example `application.properties`

```
# MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/week5?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Server
server.port=8080
```

> **Note:** `spring.jpa.hibernate.ddl-auto=update` will create tables automatically. The included `data.sql` will run on startup to insert base data — make sure table names in `data.sql` match the schema (pluralization matters: `companies`, `employee`, `project`, etc.).

---

## data.sql (example)

Place this file in `src/main/resources/data.sql`. This script inserts one company, two employees, one project and links them.

```sql
-- Companies
INSERT INTO companies (id, name, location) VALUES (1, 'AVK Tech Solutions', 'Hyderabad');

-- Addresses (for employees)
INSERT INTO addresses (id, street, city, state, zip) VALUES (1, 'MG Road', 'Hyderabad', 'TS', '500001');
INSERT INTO addresses (id, street, city, state, zip) VALUES (2, 'Banjara Hills', 'Hyderabad', 'TS', '500034');

-- Employees
INSERT INTO employee (id, name, email, phone, company_id, address_id) VALUES (1, 'John Doe', 'john@example.com', '9999999999', 1, 1);
INSERT INTO employee (id, name, email, phone, company_id, address_id) VALUES (2, 'Jane Smith', 'jane@example.com', '8888888888', 1, 2);

-- Project
INSERT INTO project (id, title, company_id) VALUES (1, 'AI Platform', 1);

-- Many-to-many join table (employee_project)
INSERT INTO employee_project (employee_id, project_id) VALUES (1, 1);
INSERT INTO employee_project (employee_id, project_id) VALUES (2, 1);
```

**Troubleshooting:** if startup fails because `data.sql` references a table name that doesn't exist ("Table 'week5.company' doesn't exist"), double-check the generated table names in your DB and adjust `data.sql` to match (e.g., `companies`, `company`, `employee`).

---

## API Endpoints (examples)

Base: `http://localhost:8080`

### Company

* `POST /companies` — Create company
* `GET /companies` — List companies
* `GET /companies/{id}` — Get by id
* `GET /companies/by-name?name=...` — Find by name
* `DELETE /companies/{id}` — Delete

### Employee

* `POST /employees?companyId={companyId}` — Create employee (body contains dto with `address`)
* `GET /employees` — List all
* `GET /employees/{id}` — Get by id
* `PUT /employees/{id}` — Update
* `DELETE /employees/{id}` — Delete
* `GET /employees/{id}/projects` — Get projects of an employee

### Project

* `POST /projects?companyId={companyId}` — Create project (body may contain `employeeIds` array)
* `POST /projects/{projectId}/employees/{employeeId}` — Assign employee
* `DELETE /projects/{projectId}/employees/{employeeId}` — Remove employee
* `GET /projects?companyId={companyId}` — Get projects for company
* `GET /projects/{projectId}/employees` — List employees in project (supports pagination & sorting)

  * Example: `/projects/1/employees?page=0&size=5&sort=name,asc`

### Address

* `POST /addresses` — Create address
* `GET /addresses` — List
* `GET /addresses/{id}` — Get by id
* `PUT /addresses/{id}` — Update
* `DELETE /addresses/{id}` — Delete

---

## Postman

A Postman collection JSON is included in the repo (`postman_collection.json`). Import it into Postman to test all endpoints.

---

## Pagination & Sorting (employees inside a project)

Controller: `GET /projects/{projectId}/employees` accepts `page`, `size`, `sort` query params and returns a paged list of Employee DTOs. Use `PageRequest.of(page, size, Sort.by(...))` in service/repository.

---

## Completed tasks checklist

* [x] Entities: Company, Employee, Project, Address
* [x] JPA mappings and DTOs + mappers
* [x] CRUD controllers & services
* [x] Project ↔ Employee many-to-many operations (assign/remove)
* [x] Company endpoints (by id and by name)
* [x] data.sql script (example provided)
* [x] Postman collection
* [x] Pagination & sorting for employees in project (controller + service ready)

---

## Notes & gotchas

* Make sure your `data.sql` table names match the actual generated table names. Spring Boot uses `ddl-auto=update` to create tables — but if your `data.sql` runs before schema creation or has mismatched names you can get errors.
* For many-to-many mapping, ensure only one side uses `mappedBy` and you don't duplicate `@JoinTable` and `mappedBy` together.
* When creating resources by query parameter (e.g., `POST /employees?companyId=1`), ensure the controller expects the param as `@RequestParam Long companyId` (not null) and the service uses that id to fetch the company.

---

## Contributing

Make changes, run tests and open PRs. Keep naming consistent and update `data.sql` when you rename entities/tables.

---

---

*Generated README for the Spring Demo API project.*

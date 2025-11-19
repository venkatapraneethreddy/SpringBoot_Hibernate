INSERT INTO company (id, name) VALUES (1, 'Acme Corp');
INSERT INTO project (id, name, company_id) VALUES (1, 'Apollo', 1);
INSERT INTO employee (id, name, email) VALUES (1, 'Alice', 'alice@example.com');
INSERT INTO employee (id, name, email) VALUES (2, 'Bob', 'bob@example.com');
INSERT INTO address (id, street, city, employee_id) VALUES (1, '123 Main St', 'Townsville', 1);
INSERT INTO project_employee (project_id, employee_id) VALUES (1, 1);
INSERT INTO project_employee (project_id, employee_id) VALUES (1, 2);

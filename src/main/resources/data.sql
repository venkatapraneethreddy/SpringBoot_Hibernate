
-- ======================
-- 1. Companies
-- ======================
INSERT INTO company (id, name, location)
VALUES
  (1, 'TechCorp', 'Hyderabad'),
  (2, 'CodeWorks', 'Bangalore');

-- ======================
-- 2. Addresses
-- ======================
INSERT INTO address (id, street, city, state, zip)
VALUES
  (1, 'MG Road', 'Bangalore', 'KA', '560001'),
  (2, 'Hitech City', 'Hyderabad', 'TS', '500081'),
  (3, 'Park Street', 'Kolkata', 'WB', '700016');

-- ======================
-- 3. Employees
-- (each employee linked to company + address)
-- ======================
INSERT INTO employee (id, name, email, phone, company_id, address_id)
VALUES
  (1, 'Praneeth Reddy', 'praneeth@gmail.com', '9999999999', 1, 1),
  (2, 'Dinesh Kumar', 'dinesh@gmail.com', '8888888888', 1, 2),
  (3, 'Ravi Patel', 'ravi@gmail.com', '7777777777', 2, 3);

-- ======================
-- 4. Projects
-- (each project linked to a company)
-- ======================
INSERT INTO project (id, title, company_id)
VALUES
  (1, 'AI Platform', 1),
  (2, 'FinTech App', 1),
  (3, 'E-Commerce Portal', 2);

-- ======================
-- 5. Employee-Project Mapping
-- ======================
-- Project 1 (AI Platform)
INSERT INTO project_employees (project_id, employee_id) VALUES (1, 1);
INSERT INTO project_employees (project_id, employee_id) VALUES (1, 2);

-- Project 2 (FinTech App)
INSERT INTO project_employees (project_id, employee_id) VALUES (2, 2);

-- Project 3 (E-Commerce Portal)
INSERT INTO project_employees (project_id, employee_id) VALUES (3, 3);

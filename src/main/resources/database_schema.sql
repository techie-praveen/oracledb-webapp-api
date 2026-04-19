-- SQL Script for Oracle Database
-- This script creates the necessary tables and sequences for Employee-Department management system

-- Create Sequences
CREATE SEQUENCE DEPT_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE SEQUENCE EMP_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Create DEPARTMENTS Table
CREATE TABLE departments (
    dept_id NUMBER PRIMARY KEY,
    dept_name VARCHAR2(100) NOT NULL,
    location VARCHAR2(100),
    budget NUMBER(10,2),
    CONSTRAINT uk_dept_name UNIQUE (dept_name)
);

-- Create EMPLOYEES Table
CREATE TABLE employees (
    emp_id NUMBER PRIMARY KEY,
    emp_name VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) UNIQUE,
    phone VARCHAR2(20),
    salary NUMBER(10,2),
    hire_date VARCHAR2(20),
    job_title VARCHAR2(100),
    dept_id NUMBER NOT NULL,
    CONSTRAINT fk_emp_dept FOREIGN KEY (dept_id) REFERENCES departments(dept_id) ON DELETE CASCADE
);

-- Create Indexes for better query performance
CREATE INDEX idx_emp_dept_id ON employees(dept_id);
CREATE INDEX idx_emp_email ON employees(email);

-- Insert Sample Data into DEPARTMENTS
INSERT INTO departments (dept_id, dept_name, location, budget) VALUES (DEPT_SEQ.NEXTVAL, 'Information Technology', 'New York', 500000);
INSERT INTO departments (dept_id, dept_name, location, budget) VALUES (DEPT_SEQ.NEXTVAL, 'Human Resources', 'Los Angeles', 300000);
INSERT INTO departments (dept_id, dept_name, location, budget) VALUES (DEPT_SEQ.NEXTVAL, 'Finance', 'Chicago', 400000);
INSERT INTO departments (dept_id, dept_name, location, budget) VALUES (DEPT_SEQ.NEXTVAL, 'Sales', 'Houston', 600000);
INSERT INTO departments (dept_id, dept_name, location, budget) VALUES (DEPT_SEQ.NEXTVAL, 'Marketing', 'Phoenix', 350000);

-- Insert Sample Data into EMPLOYEES
INSERT INTO employees (emp_id, emp_name, email, phone, salary, hire_date, job_title, dept_id)
VALUES (EMP_SEQ.NEXTVAL, 'John Doe', 'john.doe@company.com', '555-0101', 75000, '2022-01-15', 'Senior Developer', 1);

INSERT INTO employees (emp_id, emp_name, email, phone, salary, hire_date, job_title, dept_id)
VALUES (EMP_SEQ.NEXTVAL, 'Jane Smith', 'jane.smith@company.com', '555-0102', 65000, '2022-03-20', 'Developer', 1);

INSERT INTO employees (emp_id, emp_name, email, phone, salary, hire_date, job_title, dept_id)
VALUES (EMP_SEQ.NEXTVAL, 'Mike Johnson', 'mike.johnson@company.com', '555-0103', 70000, '2021-06-10', 'DevOps Engineer', 1);

INSERT INTO employees (emp_id, emp_name, email, phone, salary, hire_date, job_title, dept_id)
VALUES (EMP_SEQ.NEXTVAL, 'Sarah Williams', 'sarah.williams@company.com', '555-0104', 60000, '2023-02-14', 'HR Manager', 2);

INSERT INTO employees (emp_id, emp_name, email, phone, salary, hire_date, job_title, dept_id)
VALUES (EMP_SEQ.NEXTVAL, 'Robert Brown', 'robert.brown@company.com', '555-0105', 65000, '2022-08-01', 'HR Specialist', 2);

INSERT INTO employees (emp_id, emp_name, email, phone, salary, hire_date, job_title, dept_id)
VALUES (EMP_SEQ.NEXTVAL, 'Emily Davis', 'emily.davis@company.com', '555-0106', 72000, '2021-11-15', 'Financial Analyst', 3);

INSERT INTO employees (emp_id, emp_name, email, phone, salary, hire_date, job_title, dept_id)
VALUES (EMP_SEQ.NEXTVAL, 'David Miller', 'david.miller@company.com', '555-0107', 78000, '2020-09-20', 'Finance Manager', 3);

INSERT INTO employees (emp_id, emp_name, email, phone, salary, hire_date, job_title, dept_id)
VALUES (EMP_SEQ.NEXTVAL, 'Jennifer Wilson', 'jennifer.wilson@company.com', '555-0108', 68000, '2022-05-10', 'Sales Executive', 4);

INSERT INTO employees (emp_id, emp_name, email, phone, salary, hire_date, job_title, dept_id)
VALUES (EMP_SEQ.NEXTVAL, 'Christopher Taylor', 'chris.taylor@company.com', '555-0109', 71000, '2021-12-01', 'Sales Manager', 4);

INSERT INTO employees (emp_id, emp_name, email, phone, salary, hire_date, job_title, dept_id)
VALUES (EMP_SEQ.NEXTVAL, 'Lisa Anderson', 'lisa.anderson@company.com', '555-0110', 64000, '2023-01-10', 'Marketing Specialist', 5);

-- Commit all changes
COMMIT;

-- Display all departments
SELECT * FROM departments;

-- Display all employees
SELECT * FROM employees;

-- Display employees with their departments
SELECT e.emp_id, e.emp_name, e.email, e.job_title, e.salary, d.dept_name, d.location
FROM employees e
INNER JOIN departments d ON e.dept_id = d.dept_id
ORDER BY e.emp_id;


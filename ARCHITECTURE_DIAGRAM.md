# Architecture and Relationship Diagram

## System Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│                     REST API Client (Postman/cURL)              │
└─────────────────────────────────────────────────────────────────┘
                              ↓ HTTP Requests
┌─────────────────────────────────────────────────────────────────┐
│                    Spring Boot Application (Port 8080)          │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │           REST Controllers                              │   │
│  │  ┌──────────────────┐  ┌──────────────────┐            │   │
│  │  │ Department       │  │ Employee         │            │   │
│  │  │ Controller       │  │ Controller       │            │   │
│  │  └────────┬─────────┘  └────────┬─────────┘            │   │
│  │           │                      │                      │   │
│  │           ↓                      ↓                      │   │
│  │  ┌──────────────────┐  ┌──────────────────┐            │   │
│  │  │ Department       │  │ Employee         │            │   │
│  │  │ Service          │  │ Service          │            │   │
│  │  └────────┬─────────┘  └────────┬─────────┘            │   │
│  │           │                      │                      │   │
│  │           ↓                      ↓                      │   │
│  │  ┌──────────────────┐  ┌──────────────────┐            │   │
│  │  │ Department       │  │ Employee         │            │   │
│  │  │ Repository       │  │ Repository       │            │   │
│  │  └────────┬─────────┘  └────────┬─────────┘            │   │
│  └────────────┼──────────────────────┼──────────────────────┘   │
│               │                      │                          │
│               ↓                      ↓                          │
│  ┌─────────────────────────────────────────────────────┐       │
│  │           JPA / Hibernate ORM                        │       │
│  └──────────────────────┬──────────────────────────────┘       │
└─────────────────────────┼────────────────────────────────────────┘
                          ↓ JDBC Queries
┌─────────────────────────────────────────────────────────────────┐
│             Oracle Database (OJDBC Driver)                      │
│  ┌──────────────────────┐      ┌──────────────────────┐        │
│  │   DEPARTMENTS        │◄─────┤   EMPLOYEES          │        │
│  │  ┌──────────────────┐│      │  ┌──────────────────┐│        │
│  │  │ dept_id (PK)     ││      │  │ emp_id (PK)      ││        │
│  │  │ dept_name        ││      │  │ emp_name         ││        │
│  │  │ location         ││      │  │ email            ││        │
│  │  │ budget           ││      │  │ phone            ││        │
│  │  └──────────────────┘│      │  │ salary           ││        │
│  │                      │      │  │ hire_date        ││        │
│  │  (DEPT_SEQ)          │      │  │ job_title        ││        │
│  │                      │      │  │ dept_id (FK)     ││        │
│  │                      │      │  └──────────────────┘│        │
│  └──────────────────────┘      │                      │        │
│     1 (One)          * (Many)  │  (EMP_SEQ)           │        │
│  ◄─────────────────────────────►                      │        │
│  OneToMany              ManyToOne                      │        │
│                                 └──────────────────────┘        │
└─────────────────────────────────────────────────────────────────┘
```

## Entity Relationship Diagram (ERD)

```
┌─────────────────────┐
│    DEPARTMENT       │
├─────────────────────┤
│ PK: dept_id         │
│    dept_name (U)    │
│    location         │
│    budget           │
└─────────────────────┘
         │ 1
         │
    OneToMany (mappedBy="department")
         │
         │ *
         │
┌─────────────────────┐
│    EMPLOYEE         │
├─────────────────────┤
│ PK: emp_id          │
│    emp_name         │
│    email (U)        │
│    phone            │
│    salary           │
│    hire_date        │
│    job_title        │
│ FK: dept_id ────────┼──→ DEPARTMENT.dept_id
└─────────────────────┘
         │
    ManyToOne (fetch=EAGER)
         │
         └──→ belongs to exactly ONE Department
```

## API Flow Diagram

```
┌──────────────────────────────────────────────────────────────────┐
│                      API CLIENT REQUEST                          │
│                    (Postman/cURL/Browser)                        │
└────────────────────────────┬─────────────────────────────────────┘
                             │
        ┌────────────────────┼────────────────────┐
        │                    │                    │
        ↓                    ↓                    ↓
┌──────────────────┐ ┌──────────────────┐ ┌──────────────────┐
│  POST Request    │ │  GET Request     │ │  PUT Request     │
│  (Create)        │ │  (Read)          │ │  (Update)        │
│  201 Created     │ │  200 OK          │ │  200 OK          │
└────────┬─────────┘ └────────┬─────────┘ └────────┬─────────┘
         │                    │                    │
         └────────────┬───────┴────────┬───────────┘
                      │                │
                      ↓                ↓
            ┌──────────────────────────────────┐
            │   DepartmentController /          │
            │   EmployeeController             │
            │   (Handle HTTP Requests)         │
            └──────────────────┬───────────────┘
                               │
                               ↓
            ┌──────────────────────────────────┐
            │   DepartmentService /            │
            │   EmployeeService               │
            │   (Business Logic)              │
            └──────────────────┬───────────────┘
                               │
                               ↓
            ┌──────────────────────────────────┐
            │   DepartmentRepository /         │
            │   EmployeeRepository            │
            │   (Data Access - JPA)           │
            └──────────────────┬───────────────┘
                               │
                               ↓
            ┌──────────────────────────────────┐
            │   Hibernate / Spring Data JPA    │
            │   (ORM Layer)                    │
            └──────────────────┬───────────────┘
                               │
                               ↓
            ┌──────────────────────────────────┐
            │   Oracle JDBC Driver             │
            │   (Database Connection)          │
            └──────────────────┬───────────────┘
                               │
                               ↓
            ┌──────────────────────────────────┐
            │   Oracle Database                │
            │   (Data Persistence)             │
            └────────────────────────────────┐┘
                                             │
                                             ↓
                                ┌──────────────────────┐
                                │  Return Result (JSON)│
                                │  200 OK / 404 / 500  │
                                └──────────────────────┘
```

## Relationship Mapping Flow

```
DEPARTMENT CREATION
│
├─→ POST /api/departments
│   └─→ Request: {deptName, location, budget}
│       Response: Department with deptId
│
└─→ Created Department stored in DEPARTMENTS table

EMPLOYEE CREATION
│
├─→ POST /api/employees
│   └─→ Request: {empName, email, department: {deptId}}
│       ├─→ Validates deptId exists in DEPARTMENTS
│       └─→ Response: Employee with empId and department details
│
└─→ Created Employee stored in EMPLOYEES table
   └─→ Foreign Key (dept_id) links to DEPARTMENTS

RETRIEVE RELATIONSHIPS
│
├─→ GET /api/departments/{deptId}
│   └─→ Returns Department with list of Employees (OneToMany)
│
├─→ GET /api/employees/{empId}
│   └─→ Returns Employee with Department details (ManyToOne)
│
└─→ GET /api/employees/department/{deptId}
    └─→ Returns all Employees for that department

DELETE CASCADE
│
├─→ DELETE /api/departments/{deptId}
│   └─→ Deletes Department AND all its Employees
│       (Cascade delete enabled)
│
└─→ DELETE /api/employees/{empId}
    └─→ Deletes only the specific Employee
        (Department remains intact)
```

## Data Flow for Creating Employee with Department

```
┌─────────────────────────────────────────┐
│   POST /api/employees                   │
│   {                                     │
│     "empName": "John Doe",              │
│     "email": "john@example.com",        │
│     "department": { "deptId": 1 }       │
│   }                                     │
└────────────────┬────────────────────────┘
                 │
                 ↓
        ┌────────────────────┐
        │ EmployeeController │
        │   createEmployee() │
        └────────────┬───────┘
                     │
                     ↓
        ┌────────────────────┐
        │ EmployeeService    │
        │ createEmployee()   │
        │ (Validates dept)   │
        └────────────┬───────┘
                     │
                     ↓
        ┌────────────────────────────┐
        │ EmployeeRepository.save()  │
        │ (JPA Persist)              │
        └────────────┬───────────────┘
                     │
                     ↓
        ┌────────────────────────────┐
        │  Hibernate generates SQL:  │
        │  INSERT INTO employees     │
        │    (emp_id, emp_name,      │
        │     email, dept_id, ...)   │
        │  VALUES (SEQ.NEXTVAL,      │
        │    'John Doe', '...', 1)   │
        └────────────┬───────────────┘
                     │
                     ↓
        ┌────────────────────────────┐
        │  Oracle executes INSERT    │
        │  Foreign key constraint    │
        │  validated (dept_id=1)     │
        └────────────┬───────────────┘
                     │
                     ↓
        ┌────────────────────────────┐
        │  Employee persisted with   │
        │  emp_id=1, dept_id=1       │
        │  Returns created Employee  │
        └────────────┬───────────────┘
                     │
                     ↓
┌──────────────────────────────────────────┐
│   201 Created Response:                  │
│   {                                      │
│     "empId": 1,                          │
│     "empName": "John Doe",               │
│     "email": "john@example.com",         │
│     "department": {                      │
│       "deptId": 1,                       │
│       "deptName": "IT",                  │
│       ...                                │
│     }                                    │
│   }                                      │
└──────────────────────────────────────────┘
```

## Cascade Delete Flow

```
DELETE /api/departments/1 (IT Department)
│
├─→ DepartmentController.deleteDepartment(1)
│
├─→ DepartmentService.deleteDepartment(1)
│
├─→ DepartmentRepository.deleteById(1)
│
├─→ Hibernate detects:
│   @OneToMany(cascade = CascadeType.ALL)
│
├─→ Query: SELECT * FROM employees WHERE dept_id = 1
│   Returns: [John Doe, Jane Smith, Mike Johnson]
│
├─→ Delete all related employees first:
│   DELETE FROM employees WHERE dept_id = 1
│   (emp_id: 1, 2, 3 deleted)
│
├─→ Then delete the department:
│   DELETE FROM departments WHERE dept_id = 1
│
└─→ Response: "Department deleted successfully"
   ├─→ DEPARTMENTS table: 1 record removed
   └─→ EMPLOYEES table: 3 records removed
```

## Performance Considerations

```
Fetch Strategies:
├─ Department.employees → @OneToMany(fetch=FetchType.LAZY)
│  ├─ Initial query: Load Department only
│  └─ Lazy query: Load employees when accessed
│     └─ Avoids loading unnecessary data
│
└─ Employee.department → @ManyToOne(fetch=FetchType.EAGER)
   ├─ Loads Department with every Employee
   └─ Prevents N+1 query problem
      └─ Needed because most employee queries need dept info

Indexes Created:
├─ idx_emp_dept_id on employees.dept_id
│  └─ Speeds up: findByDepartmentDeptId()
│
└─ idx_emp_email on employees.email
   └─ Speeds up: findByEmail()
```

---

This diagram provides a comprehensive view of the system architecture, entity relationships, and data flow through the application.


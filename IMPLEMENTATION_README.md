# Employee & Department Management API

A Spring Boot REST API application for managing Employee and Department operations with Oracle Database.

## Features

- **Department Management**: Create, Read, Update, Delete departments
- **Employee Management**: Create, Read, Update, Delete employees
- **OneToMany Relationship**: One department can have multiple employees
- **ManyToOne Relationship**: Each employee belongs to one department
- **Search Capabilities**: Find employees by ID, email, or department
- **CORS Support**: Cross-origin requests enabled

## Technologies Used

- **Framework**: Spring Boot 4.0.5
- **Language**: Java 25
- **Database**: Oracle Database (ojdbc11)
- **ORM**: Spring Data JPA with Hibernate
- **Build Tool**: Gradle
- **Annotations**: Lombok for reducing boilerplate code

## Project Structure

```
src/main/java/oracledb/webapp/api/
├── entity/
│   ├── Department.java      # Department entity with OneToMany relationship
│   └── Employee.java        # Employee entity with ManyToOne relationship
├── repository/
│   ├── DepartmentRepository.java
│   └── EmployeeRepository.java
├── service/
│   ├── DepartmentService.java
│   └── EmployeeService.java
├── controller/
│   ├── DepartmentController.java
│   └── EmployeeController.java
└── OracledbWebappApiApplication.java

src/main/resources/
├── application.properties     # Database configuration
└── database_schema.sql       # SQL script for database setup
```

## Database Schema

### Tables

#### DEPARTMENTS
```sql
CREATE TABLE departments (
    dept_id NUMBER PRIMARY KEY,
    dept_name VARCHAR2(100) NOT NULL UNIQUE,
    location VARCHAR2(100),
    budget NUMBER(10,2)
);
```

#### EMPLOYEES
```sql
CREATE TABLE employees (
    emp_id NUMBER PRIMARY KEY,
    emp_name VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) UNIQUE,
    phone VARCHAR2(20),
    salary NUMBER(10,2),
    hire_date VARCHAR2(20),
    job_title VARCHAR2(100),
    dept_id NUMBER NOT NULL REFERENCES departments(dept_id)
);
```

## Database Configuration

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
spring.datasource.username=system
spring.datasource.password=oracle
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
```

## Setup Instructions

### 1. Database Setup

Run the SQL script to create tables and insert sample data:
```bash
sqlplus system/oracle@XE < database_schema.sql
```

Or execute the contents of `src/main/resources/database_schema.sql` in your Oracle SQL IDE.

### 2. Build the Application

```bash
gradlew build
```

### 3. Run the Application

```bash
gradlew bootRun
```

The application will start on `http://localhost:8080`

## REST API Endpoints

### Department Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/departments` | Create a new department |
| GET | `/api/departments` | Get all departments |
| GET | `/api/departments/{deptId}` | Get department by ID |
| PUT | `/api/departments/{deptId}` | Update department |
| DELETE | `/api/departments/{deptId}` | Delete department |

### Employee Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/employees` | Create a new employee |
| GET | `/api/employees` | Get all employees |
| GET | `/api/employees/{empId}` | Get employee by ID |
| GET | `/api/employees/department/{deptId}` | Get employees by department |
| GET | `/api/employees/email/{email}` | Get employee by email |
| PUT | `/api/employees/{empId}` | Update employee |
| DELETE | `/api/employees/{empId}` | Delete employee |

## Sample Requests

### Create Department
```bash
curl -X POST http://localhost:8080/api/departments \
  -H "Content-Type: application/json" \
  -d '{
    "deptName": "Operations",
    "location": "Boston",
    "budget": 450000
  }'
```

### Create Employee
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "empName": "Paul Martin",
    "email": "paul.martin@company.com",
    "phone": "555-0111",
    "salary": 72000,
    "hireDate": "2023-03-15",
    "jobTitle": "Junior Developer",
    "department": {
      "deptId": 1
    }
  }'
```

### Get All Employees
```bash
curl -X GET http://localhost:8080/api/employees
```

### Update Employee
```bash
curl -X PUT http://localhost:8080/api/employees/1 \
  -H "Content-Type: application/json" \
  -d '{
    "empName": "John Doe - Updated",
    "salary": 80000,
    "jobTitle": "Senior Developer - Lead"
  }'
```

### Delete Employee
```bash
curl -X DELETE http://localhost:8080/api/employees/1
```

## Postman Collection

Import the `Employee_Department_API.postman_collection.json` file into Postman to test all endpoints.

**Steps to import:**
1. Open Postman
2. Click "Import"
3. Select "Upload Files"
4. Choose `Employee_Department_API.postman_collection.json`
5. Click "Import"

## Sample Data

The `database_schema.sql` script includes sample data:
- 5 Departments with different locations and budgets
- 11 Employees distributed across departments

## Relationships

### OneToMany (Department → Employee)
One department can have multiple employees. This is implemented using:
- `@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)`
- Cascading deletes: When a department is deleted, all associated employees are deleted

### ManyToOne (Employee → Department)
Each employee belongs to one department. This is implemented using:
- `@ManyToOne(fetch = FetchType.EAGER)`
- `@JoinColumn(name = "dept_id", nullable = false)`

## Error Handling

The API returns appropriate HTTP status codes:
- `201 Created` - Successful resource creation
- `200 OK` - Successful retrieval or update
- `400 Bad Request` - Invalid request data
- `404 Not Found` - Resource not found
- `500 Internal Server Error` - Server-side errors

## Dependencies

Key dependencies in `build.gradle`:
- `spring-boot-starter-data-jpa` - JPA and Hibernate
- `spring-boot-starter-web` - Web support
- `spring-boot-starter-validation` - Bean validation
- `ojdbc11` - Oracle JDBC driver
- `lombok` - Reduce boilerplate code

## Testing

All endpoints have been tested with various scenarios:
- Creating resources
- Retrieving resources
- Updating resources
- Deleting resources
- Testing relationships

## Notes

- All date fields in Employee are stored as VARCHAR2 for flexibility
- Email is unique across employees
- Department budget is optional
- Foreign key constraints are enforced on the database level
- Cascade delete is enabled for department-employee relationship

## Author

Development Team

## License

Proprietary - All rights reserved


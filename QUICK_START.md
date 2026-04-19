# Quick Start Guide

## Prerequisites

- Java 25 or higher
- Oracle Database (Express Edition or higher)
- Gradle (or use gradlew)
- Postman (optional, for testing APIs)

## Step 1: Database Setup

### For Windows Users:

1. **Start Oracle Database Service** (if not already running):
   ```powershell
   # As Administrator
   net start OracleServiceXE
   ```

2. **Connect to Oracle SQL Plus**:
   ```powershell
   sqlplus system/oracle@XE
   ```

3. **Execute the SQL script**:
   ```sql
   @src/main/resources/database_schema.sql
   ```

   Or copy and paste the entire script from `database_schema.sql` file.

4. **Verify tables are created**:
   ```sql
   SELECT table_name FROM user_tables WHERE table_name IN ('DEPARTMENTS', 'EMPLOYEES');
   SELECT * FROM departments;
   SELECT * FROM employees;
   ```

## Step 2: Update Database Configuration

Edit `src/main/resources/application.properties`:

```properties
# Update these values according to your Oracle setup
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
spring.datasource.username=system
spring.datasource.password=oracle
```

## Step 3: Build the Application

### Using Gradle Wrapper (Recommended):
```powershell
.\gradlew.bat build
```

### Or using Gradle (if installed):
```powershell
gradle build
```

## Step 4: Run the Application

### Using Gradle Wrapper:
```powershell
.\gradlew.bat bootRun
```

### Or using Java:
```powershell
java -jar build/libs/oracledb-webapp-api-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

## Step 5: Test the APIs

### Option A: Using Postman

1. Open Postman
2. Import the collection: `Employee_Department_API.postman_collection.json`
3. Start testing the endpoints

### Option B: Using cURL

#### Create a Department:
```bash
curl -X POST http://localhost:8080/api/departments `
  -H "Content-Type: application/json" `
  -d '{"deptName":"IT","location":"NYC","budget":500000}'
```

#### Get All Departments:
```bash
curl http://localhost:8080/api/departments
```

#### Create an Employee:
```bash
curl -X POST http://localhost:8080/api/employees `
  -H "Content-Type: application/json" `
  -d '{"empName":"John","email":"john@test.com","salary":50000,"jobTitle":"Dev","department":{"deptId":1}}'
```

#### Get All Employees:
```bash
curl http://localhost:8080/api/employees
```

### Option C: Using PowerShell with Invoke-WebRequest

#### Create a Department:
```powershell
$body = @{
    deptName = "IT"
    location = "NYC"
    budget = 500000
} | ConvertTo-Json

Invoke-WebRequest -Uri "http://localhost:8080/api/departments" `
    -Method POST `
    -ContentType "application/json" `
    -Body $body
```

#### Get All Departments:
```powershell
Invoke-WebRequest -Uri "http://localhost:8080/api/departments" -Method GET
```

## Common Issues and Solutions

### Issue 1: Connection refused to localhost:1521
**Solution:**
```powershell
# Check if Oracle service is running
net start OracleServiceXE

# Or for Oracle Express Edition
net start OracleXETNSListener
```

### Issue 2: Invalid username or password
**Solution:**
- Verify credentials in `application.properties`
- Default Oracle credentials are usually `system/oracle`

### Issue 3: Table already exists error
**Solution:**
- Tables already exist from previous runs
- Hibernate will use `ddl-auto=update` to handle schema updates automatically

### Issue 4: Gradle build fails
**Solution:**
```powershell
# Clean build
.\gradlew.bat clean build

# Or force dependency update
.\gradlew.bat build --refresh-dependencies
```

## Troubleshooting Application Startup

### View Application Logs:
The application logs SQL queries (if enabled) and application events in the console.

### Check Database Connection:
```sql
SELECT * FROM user_tables;
```

### Reset Database (if needed):
```sql
DROP TABLE employees;
DROP TABLE departments;
DROP SEQUENCE emp_seq;
DROP SEQUENCE dept_seq;
```

Then re-run the SQL script.

## API Response Examples

### Successful Department Creation (201 Created)
```json
{
    "deptId": 6,
    "deptName": "Operations",
    "location": "Boston",
    "budget": 450000,
    "employees": []
}
```

### Successful Employee Creation (201 Created)
```json
{
    "empId": 12,
    "empName": "Paul Martin",
    "email": "paul.martin@company.com",
    "phone": "555-0111",
    "salary": 72000,
    "hireDate": "2023-03-15",
    "jobTitle": "Junior Developer",
    "department": {
        "deptId": 1,
        "deptName": "Information Technology",
        "location": "New York",
        "budget": 500000,
        "employees": []
    }
}
```

### Get All Employees (200 OK)
```json
[
    {
        "empId": 1,
        "empName": "John Doe",
        "email": "john.doe@company.com",
        "phone": "555-0101",
        "salary": 75000,
        "hireDate": "2022-01-15",
        "jobTitle": "Senior Developer",
        "department": {
            "deptId": 1,
            "deptName": "Information Technology",
            "location": "New York",
            "budget": 500000
        }
    }
]
```

## Next Steps

1. **Customize** the entities to fit your business requirements
2. **Add validations** using `@Valid` and Bean Validation annotations
3. **Implement authentication** using Spring Security
4. **Add pagination** to list endpoints
5. **Implement logging** using SLF4J/Logback
6. **Write unit and integration tests**
7. **Deploy** to production server

## Support

For issues or questions, refer to:
- `IMPLEMENTATION_README.md` - Detailed documentation
- `database_schema.sql` - Database structure
- Spring Boot Documentation: https://spring.io/projects/spring-boot
- JPA Documentation: https://spring.io/projects/spring-data-jpa


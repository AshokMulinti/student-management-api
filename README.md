# Student Management API

A RESTful Spring Boot API for managing student information with MySQL database integration.

## Features

- **CRUD Operations**: Create, Read, Update, and Delete student records
- **Search Functionality**: Search students by name, grade, and GPA
- **Data Validation**: Input validation with proper error handling
- **RESTful Design**: Follows REST API best practices
- **MySQL Integration**: Uses MySQL database for data persistence

## Technology Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **MySQL 8.0**
- **Maven**

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- Database `studentdb` created on port 3306

## Database Setup

1. Create MySQL database:
```sql
CREATE DATABASE studentdb;
```

2. Update database credentials in `src/main/resources/application.properties`:
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## Running the Application

1. **Clone/Download** the project
2. **Navigate** to project directory:
   ```bash
   cd student-management-api
   ```
3. **Build** the project:
   ```bash
   mvn clean install
   ```
4. **Run** the application:
   ```bash
   mvn spring-boot:run
   ```

The API will be available at `http://localhost:8080`

## API Endpoints

### Student Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/students` | Get all students |
| GET | `/api/students/{id}` | Get student by ID |
| GET | `/api/students/email/{email}` | Get student by email |
| POST | `/api/students` | Create new student |
| PUT | `/api/students/{id}` | Update existing student |
| DELETE | `/api/students/{id}` | Delete student |

### Search & Filter

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/students/search?name={name}` | Search students by name |
| GET | `/api/students/grade/{grade}` | Get students by grade |
| GET | `/api/students/gpa-above/{gpa}` | Get students with GPA above threshold |
| GET | `/api/students/count` | Get total student count |

## Sample API Usage

### Create a Student
```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "phoneNumber": "123-456-7890",
    "grade": "A",
    "gpa": 3.8
  }'
```

### Get All Students
```bash
curl http://localhost:8080/api/students
```

### Get Student by ID
```bash
curl http://localhost:8080/api/students/1
```

### Update Student
```bash
curl -X PUT http://localhost:8080/api/students/1 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Smith",
    "email": "john.smith@example.com",
    "grade": "A+",
    "gpa": 3.9
  }'
```

### Delete Student
```bash
curl -X DELETE http://localhost:8080/api/students/1
```

### Search Students by Name
```bash
curl "http://localhost:8080/api/students/search?name=John"
```

## Student Entity Fields

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| id | Long | Auto-generated | Unique identifier |
| firstName | String | Yes | Student's first name (2-50 chars) |
| lastName | String | Yes | Student's last name (2-50 chars) |
| email | String | Yes | Unique email address |
| phoneNumber | String | No | Contact phone number |
| dateOfBirth | LocalDate | No | Student's date of birth |
| grade | String | No | Academic grade |
| gpa | Double | No | Grade Point Average |

## Error Handling

The API provides consistent error responses:

- **400 Bad Request**: Validation errors or business logic violations
- **404 Not Found**: Resource not found
- **500 Internal Server Error**: Unexpected server errors

## Database Schema

The application will automatically create the `students` table with the following structure:

```sql
CREATE TABLE students (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(20),
    date_of_birth DATE,
    grade VARCHAR(10),
    gpa DOUBLE
);
```

## Development

- **Port**: 8080 (configurable in `application.properties`)
- **Database**: MySQL on localhost:3306
- **Auto-create tables**: Enabled (`spring.jpa.hibernate.ddl-auto=update`)
- **Logging**: SQL queries and Spring Web debug logs enabled

## Testing

Run tests with:
```bash
mvn test
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is open source and available under the [MIT License](LICENSE).

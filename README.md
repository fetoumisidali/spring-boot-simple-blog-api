# Blog REST API

A professional RESTful API for managing blog posts, built with Spring Boot 3.5.7 and Java 21. This application provides a complete backend solution for blog post management with CRUD operations, search functionality, and robust error handling.

## Technology Stack

- **Java 21** - Latest LTS version
- **Spring Boot 3.5.7** - Application framework
- **Spring Data JPA** - Data persistence and repository abstraction
- **Hibernate** - ORM implementation (MySQL 8 dialect)
- **MySQL 8** - Relational database
- **Lombok** - Reduces boilerplate code (getters, setters, builders)
- **Jakarta Bean Validation** - Request validation (formerly Java EE)
- **Maven** - Build and dependency management

### Key Dependencies

```xml
<!-- Core Spring Boot Starters -->
spring-boot-starter-web          - REST API & embedded Tomcat
spring-boot-starter-data-jpa     - JPA/Hibernate & Spring Data
spring-boot-starter-validation   - Jakarta Bean Validation

<!-- Database -->
mysql-connector-j                - MySQL JDBC driver

<!-- Development Tools -->
lombok                          - Code generation & boilerplate reduction

<!-- Testing -->
spring-boot-starter-test        - Testing framework (JUnit, Mockito, etc.)
```

## Features

- **CRUD Operations** - Create, read, update, and delete blog posts
- **Search Functionality** - Case-insensitive search for posts by title using custom JPQL queries
- **Bean Validation** - Automatic request validation using Jakarta Validation API with custom constraint messages
- **Global Exception Handling** - Centralized error handling with standardized error codes and custom error responses
- **JPA Auditing** - Automatic tracking of creation and modification timestamps using Spring Data JPA auditing
- **CORS Configuration** - Pre-configured CORS support for frontend integration
- **Layered Architecture** - Clean separation of concerns (Controller, Service, Repository, DTO, Entity)
- **DTO Pattern** - Separation between domain entities and API responses using dedicated mapper component
- **Custom JPQL Queries** - Optimized database queries for search functionality
- **Standardized Error Codes** - Enum-based error code management for consistent API responses

## Project Structure

```
blog/
├── src/main/java/com/sidalifetoumi/blog/
│   ├── config/           # Configuration classes
│   │   ├── CorsConfig.java
│   │   └── JpaAuditingConfiguration.java
│   ├── controller/       # REST controllers
│   │   └── PostController.java
│   ├── dto/              # Data Transfer Objects
│   │   ├── CreatePostDto.java
│   │   ├── PostResponseDto.java
│   │   └── ErrorResponse.java
│   ├── entity/           # JPA entities
│   │   └── Post.java
│   ├── exception/        # Exception handling
│   │   ├── GlobalExceptionHandler.java
│   │   ├── PostNotFoundException.java
│   │   └── ErrorCode.java
│   ├── mapper/           # Entity-DTO mappers
│   │   └── PostMapper.java
│   ├── repository/       # Data access layer
│   │   └── PostRepository.java
│   ├── service/          # Business logic
│   │   ├── PostService.java
│   │   └── impl/
│   │       └── PostServiceImpl.java
│   └── BlogApplication.java
└── src/main/resources/
    └── application.properties
```

## Database Schema

### Post Entity

The application uses a single entity with the following structure:

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| id | Long | Primary Key, Auto-generated (Sequence) | Unique identifier |
| title | String | NOT NULL | Post title |
| content | String | NOT NULL, Non-updatable | Post content |
| createdAt | LocalDateTime | NOT NULL, Non-updatable | Creation timestamp (auto-managed) |
| updatedAt | LocalDateTime | - | Last update timestamp (auto-managed) |

**Auditing Features:**
- `createdAt` is automatically set on entity creation
- `updatedAt` is automatically updated on entity modification
- Managed by Spring Data JPA Auditing with `@CreatedDate` and `@LastModifiedDate`
- Requires `@EnableJpaAuditing` configuration

## Prerequisites

- Java Development Kit (JDK) 21 or higher
- Maven 3.6 or higher
- MySQL 8.0 or higher
- IDE (IntelliJ IDEA, Eclipse, or VS Code recommended)

## Installation & Setup

### 1. Clone the Repository

```bash
git clone <repository-url>
cd blog
```

### 2. Configure Database

Create a MySQL database or let the application auto-create it:

```sql
CREATE DATABASE my_database;
```

Update database credentials in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/my_database?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=your_password
```

### 3. Build the Project

```bash
mvnw clean install
```

Or on Unix/Linux/Mac:

```bash
./mvnw clean install
```

### 4. Run the Application

```bash
mvnw spring-boot:run
```

Or on Unix/Linux/Mac:

```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## Quick Start Guide

After starting the application, you can test it using curl, Postman, or any HTTP client:

### Create Your First Post

```bash
curl -X POST http://localhost:8080/posts \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Hello World",
    "content": "This is my first blog post!"
  }'
```

### Get All Posts

```bash
curl http://localhost:8080/posts
```

### Search Posts

```bash
curl "http://localhost:8080/posts?title=Hello"
```

## API Endpoints

### Base URL
```
http://localhost:8080/posts
```

### Available Endpoints

| Method | Endpoint | Description | Request Body | Response Code |
|--------|----------|-------------|--------------|---------------|
| GET | `/posts` | Retrieve all posts | - | 200 OK |
| GET | `/posts?title={keyword}` | Search posts by title (case-insensitive) | - | 200 OK |
| GET | `/posts/{id}` | Retrieve a specific post | - | 200 OK / 404 Not Found |
| POST | `/posts` | Create a new post | CreatePostDto | 200 OK / 400 Bad Request |
| PUT | `/posts/{id}` | Update an existing post | CreatePostDto | 200 OK / 404 Not Found |
| DELETE | `/posts/{id}` | Delete a post | - | 200 OK / 404 Not Found |

### Request/Response Examples

#### Create Post
```http
POST /posts
Content-Type: application/json

{
  "title": "My First Blog Post",
  "content": "This is the content of my blog post. It must be at least 10 characters long."
}
```

**Response:**
```json
{
  "id": 1,
  "title": "My First Blog Post",
  "content": "This is the content of my blog post. It must be at least 10 characters long.",
  "createdAt": "2025-11-04T10:30:00",
  "updatedAt": "2025-11-04T10:30:00"
}
```

#### Get All Posts
```http
GET /posts
```

**Response:**
```json
[
  {
    "id": 1,
    "title": "My First Blog Post",
    "content": "This is the content of my blog post.",
    "createdAt": "2025-11-04T10:30:00",
    "updatedAt": "2025-11-04T10:30:00"
  }
]
```

#### Search Posts by Title
```http
GET /posts?title=First
```

#### Update Post
```http
PUT /posts/1
Content-Type: application/json

{
  "title": "Updated Title",
  "content": "Updated content for the blog post."
}
```

#### Delete Post
```http
DELETE /posts/1
```

## Input Validation

The application uses Jakarta Bean Validation (formerly Java EE Bean Validation) to ensure data integrity and provide meaningful error messages to API consumers.

### Validation Framework

- **@Valid Annotation** - Triggers validation on request body objects
- **Custom Constraint Messages** - User-friendly validation error messages
- **Field-Level Validation** - Each field has specific validation rules
- **Automatic Error Handling** - Validation errors are automatically caught and formatted by GlobalExceptionHandler

### Validation Annotations Used

- **@NotBlank** - Ensures the field is not null and contains at least one non-whitespace character
- **@Size** - Validates the minimum and maximum length of string fields

### Post Creation/Update Validation Rules

#### Title Field
- **@NotBlank** - "title is required"
- **@Size(min = 5, max = 50)** - "title must be between 5 and 50 characters"

#### Content Field
- **@NotBlank** - "content is required"
- **@Size(min = 10, max = 5000)** - "content must be between 10 and 5000 characters"

### Validation Error Response Example

When validation fails, the API returns a structured error response with all validation errors:

```json
{
  "status": 400,
  "code": "VALIDATION_ERROR",
  "errors": {
    "title": "title must be between 5 and 50 characters",
    "content": "content is required"
  },
  "path": "uri=/posts",
  "timestamp": "2025-11-04T10:30:00"
}
```

### How Validation Works

1. Client sends a POST/PUT request with JSON body
2. Spring deserializes JSON to CreatePostDto object
3. @Valid annotation triggers validation on the DTO
4. If validation fails, MethodArgumentNotValidException is thrown
5. GlobalExceptionHandler catches the exception
6. Handler extracts field errors and formats them
7. Client receives a 400 Bad Request with detailed error information

## Error Handling

The application implements a centralized exception handling mechanism using @ControllerAdvice.

### Exception Handling Components

- **GlobalExceptionHandler** - Centralized error handling for all controllers
- **ErrorCode Enum** - Standardized error codes with HTTP status mappings
- **ErrorResponse DTO** - Consistent error response structure
- **Custom Exceptions** - Domain-specific exceptions (PostNotFoundException)

### Error Response Structure

All errors follow a consistent structure:

```json
{
  "status": 404,
  "code": "POST_NOT_FOUND",
  "message": "Post with id 1 not found.",
  "path": "uri=/posts/1",
  "timestamp": "2025-11-04T10:30:00"
}
```

### Supported Error Types

1. **Validation Errors (400 Bad Request)**
   - Triggered by @Valid annotation failures
   - Returns field-level error details
   - Error code: VALIDATION_ERROR

2. **Resource Not Found (404 Not Found)**
   - Triggered when post ID doesn't exist
   - Returns custom error message
   - Error code: POST_NOT_FOUND

### Error Code Enum

The application uses an enum to manage error codes consistently:

```java
POST_NOT_FOUND(HttpStatus.NOT_FOUND, "POST_NOT_FOUND")
VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR")
```

## Architecture & Design Patterns

### Layered Architecture

The application follows a clean layered architecture pattern:

1. **Controller Layer** (PostController)
   - Handles HTTP requests and responses
   - Input validation with @Valid
   - Maps endpoints to service methods

2. **Service Layer** (PostService, PostServiceImpl)
   - Contains business logic
   - Transaction management
   - Orchestrates repository and mapper calls

3. **Repository Layer** (PostRepository)
   - Data access abstraction
   - Extends JpaRepository for CRUD operations
   - Custom JPQL queries for complex searches

4. **Entity Layer** (Post)
   - JPA entities mapped to database tables
   - Auditing fields (createdAt, updatedAt)

5. **DTO Layer** (CreatePostDto, PostResponseDto)
   - Data transfer objects for API contracts
   - Separates internal entities from external API

6. **Mapper Layer** (PostMapper)
   - Converts between entities and DTOs
   - Keeps layers decoupled

### Design Patterns Used

- **Repository Pattern** - Abstracts data access logic
- **DTO Pattern** - Separates domain models from API contracts
- **Mapper Pattern** - Handles object transformations
- **Dependency Injection** - Constructor-based dependency injection
- **Exception Handling Pattern** - Centralized error handling with @ControllerAdvice
- **Builder Pattern** - Used in DTOs with Lombok @Builder

### Custom JPQL Query

The search functionality uses a custom JPQL query for case-insensitive partial matching:

```java
@Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%'))")
List<Post> searchByTitle(@Param("title") String title);
```

This query:
- Converts both search term and database values to lowercase
- Uses LIKE with wildcards for partial matching
- Returns all posts where the title contains the search term

## Configuration

### CORS Configuration
The application is pre-configured to accept requests from `http://localhost:3000`. To modify CORS settings, edit `CorsConfig.java`:

```java
.allowedOrigins("http://localhost:3000", "http://your-frontend-domain.com")
```

### Database Configuration

The application uses `create-drop` strategy for Hibernate DDL. For production, change this in `application.properties`:

```properties
spring.jpa.hibernate.ddl-auto=update
```

**Important Configuration Notes:**
- Database is auto-created if it doesn't exist (`createDatabaseIfNotExist=true`)
- SQL statements are logged to console (`spring.jpa.show-sql=true`)
- SQL formatting is enabled for better readability
- JPA Auditing is enabled via `@EnableJpaAuditing` for automatic timestamp management

## Testing

Run the test suite:

```bash
mvnw test
```

## Building for Production

Create a production-ready JAR:

```bash
mvnw clean package -DskipTests
```

The JAR file will be generated in the `target/` directory:

```bash
java -jar target/blog-0.0.1-SNAPSHOT.jar
```

## Development

### Using Lombok
This project uses Lombok to reduce boilerplate code. Ensure your IDE has the Lombok plugin installed:

- **IntelliJ IDEA**: Install Lombok plugin from the marketplace and enable annotation processing
- **Eclipse**: Download `lombok.jar` and run it to install
- **VS Code**: Install the Lombok Annotations Support extension

### Recommended Tools for API Testing

- **Postman** - Full-featured API testing tool
- **Swagger/OpenAPI** - Can be integrated for interactive API documentation

## Troubleshooting

### Common Issues

**Problem: Application fails to start with "Access denied for user 'root'@'localhost'"**
- Solution: Update database credentials in `application.properties`
- Ensure MySQL is running and credentials are correct

**Problem: "Table 'my_database.post' doesn't exist"**
- Solution: Verify database exists and connection is successful
- Check `spring.jpa.hibernate.ddl-auto` is set to `create-drop` or `update`

**Problem: Lombok annotations not working (getters/setters not found)**
- Solution: Install Lombok plugin in your IDE
- Enable annotation processing in IDE settings
- Rebuild/clean the project

**Problem: Validation not working (no error messages)**
- Solution: Ensure `@Valid` annotation is present in controller methods
- Verify `spring-boot-starter-validation` dependency is included
- Check GlobalExceptionHandler is being scanned by Spring

**Problem: "Could not resolve placeholder" error**
- Solution: Ensure `application.properties` is in `src/main/resources`
- Check property names are spelled correctly

**Problem: CORS errors when calling from frontend**
- Solution: Update `CorsConfig.java` with your frontend URL
- Ensure CORS configuration matches your frontend origin

## Future Enhancements

Potential improvements for this project:

- Add pagination for list endpoints
- Implement user authentication and authorization (Spring Security)
- Add post categories and tags
- Implement comment system
- Add file upload for images
- Implement caching (Redis/Ehcache)
- Add API documentation (Swagger/OpenAPI)
- Implement soft delete functionality
- Add integration tests
- Implement rate limiting

## Author

Developed by Sidali Fetoumi

## Contributing

Contributions, issues, and feature requests are welcome. Feel free to check the issues page if you want to contribute.

---

**Note:** This README was generated with AI assistance to provide comprehensive documentation for the project.

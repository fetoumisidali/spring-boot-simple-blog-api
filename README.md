# Blog REST API

A professional RESTful API for managing blog posts, built with Spring Boot 3.5.7 and Java 21. This application provides a complete backend solution for blog post management with CRUD operations, search functionality, and robust error handling.

## Technology Stack

- **Java 21** - Latest LTS version
- **Spring Boot 3.5.7** - Application framework
- **Spring Data JPA** - Data persistence
- **Hibernate** - ORM implementation
- **MySQL 8** - Relational database
- **Lombok** - Reduces boilerplate code
- **Bean Validation** - Request validation
- **Maven** - Build and dependency management

## Features

- **CRUD Operations** - Create, read, update, and delete blog posts
- **Search Functionality** - Search posts by title
- **Input Validation** - Automatic request validation with detailed error messages
- **Global Exception Handling** - Centralized error handling with custom error responses
- **JPA Auditing** - Automatic tracking of creation and modification timestamps
- **CORS Configuration** - Pre-configured CORS support for frontend integration
- **Layered Architecture** - Clean separation of concerns (Controller, Service, Repository, DTO, Entity)
- **DTO Pattern** - Separation between domain entities and API responses

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

## API Endpoints

### Base URL
```
http://localhost:8080/posts
```

### Available Endpoints

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| GET | `/posts` | Retrieve all posts | - |
| GET | `/posts?title={keyword}` | Search posts by title | - |
| GET | `/posts/{id}` | Retrieve a specific post | - |
| POST | `/posts` | Create a new post | CreatePostDto |
| PUT | `/posts/{id}` | Update an existing post | CreatePostDto |
| DELETE | `/posts/{id}` | Delete a post | - |

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

## Validation Rules

### Post Creation/Update

- **Title**
  - Required
  - Minimum length: 5 characters
  - Maximum length: 50 characters

- **Content**
  - Required
  - Minimum length: 10 characters
  - Maximum length: 5000 characters

## Error Handling

The API provides structured error responses:

```json
{
  "status": 404,
  "code": "POST_NOT_FOUND",
  "message": "Post not found with id: 1",
  "path": "uri=/posts/1",
  "timestamp": "2025-11-04T10:30:00"
}
```

Validation errors return detailed field-level information:

```json
{
  "status": 400,
  "code": "VALIDATION_ERROR",
  "errors": {
    "title": "title is required",
    "content": "content must be between 10 and 5000 characters"
  },
  "path": "uri=/posts",
  "timestamp": "2025-11-04T10:30:00"
}
```

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

- **IntelliJ IDEA**: Install Lombok plugin from the marketplace
- **Eclipse**: Download `lombok.jar` and run it to install
- **VS Code**: Install the Lombok Annotations Support extension

## License

This project is open source and available for educational and commercial use.

## Author

Developed by Sidali Fetoumi

---

**Note:** This README was generated with AI assistance to provide comprehensive documentation for the project.

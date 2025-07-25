# Programming in Kotlin - Course Syllabus

**Duration:** 3 Weeks (15 Lessons)
**Format:** Theory + Practice (Backend course approach)
**Project:** Fantasy.Space - A fantasy character battle game backend

---

## Course Overview

This course teaches programming fundamentals through Kotlin, progressing from language basics to building production-ready Spring Boot applications. Students build a complete Fantasy.Space game backend throughout the course, with each lesson adding new features and concepts.

**Key Learning Outcomes:**
- Master Kotlin language fundamentals and idiomatic patterns
- Understand object-oriented and functional programming principles
- Write testable, maintainable code following SOLID principles
- Build RESTful APIs with Spring Boot
- Deploy and monitor production applications

---

## Week 1: Kotlin Fundamentals

### Lesson 1: Kotlin Essentials
**Topics:**
- Introduction to Kotlin:
  - Why Kotlin? Development Environment Setup
  - Your First Kotlin Program (short exercise)
  - Introduction to Java & JVM
  - Introduction to Kotlin
- Kotlin Essentials:
  - Language Syntax (variables, constants, comments)
  - Data Types (primitives, nullable types, type inference)
  - Operators (arithmetic, comparison, logical)
  - Conditionals (if/when expressions, loops)
  - Working with Strings
  - Arrays
- Function Basics:
  - Functions (declaration, parameters, return types, default arguments, named arguments)
- Project Introduction: Fantasy.Space

**In-Class Exercise:**
- TODO: Geometry shape themed, calculations, conversions, comparisons and conditionals

**Assignment:**
- Fantasy.Space:
  - simple character between two characters with basic stats and calculations
  - should include functions: attack, receiveAttack, isAlive, stats summary

**Learning Objectives:**
- Set up Kotlin development environment
- Write basic Kotlin programs with variables, operators and control flow
- Understand fundamental data types and nullable types
- Create and use functions with parameters and return types

---

### Lesson 2: Classes, Objects & Data Models
**Topics:**
- OOP Concepts and Data Models:
  - OOP Concept Overview
  - Communication of Intent in Code
  - Data Models
- OOP Basics:
  - Objects & Classes (properties, constructors, methods)
  - Singleton Objects (object declaration, companion objects)
  - Data Classes
  - Enums (basic and with properties)
  - Pairs & Triples
- Packages, Imports, Modifiers (visibility, access control)

**Assignment:**
- Fantasy.Space: 
  - Character data class, CharacterType enum, CharacterStats, GameStats singleton, simple battle system

**Learning Objectives:**
- Understand OOP principles and communication of intent in code
- Create classes and objects with proper encapsulation
- Use data classes and enums for domain modeling
- Work with pairs and triples for simple data structures
- Apply visibility modifiers appropriately
- Design data models that clearly express domain concepts

---

### Lesson 3: Collections, Exceptions & Error Handling
**Topics:**
- Advanced Functions & Lambdas:
    - Lambda Expressions & Anonymous Functions
    - Higher-Order Functions
    - Extension Functions
    - Scope Functions (let, run, with, apply, also)
    - Function types and function literals
- Ranges & Progressions
- Collections:
  - Collections Overview (List, Set, Map)
  - Mutable vs Immutable Collections
  - Collection Operations (add, remove, contains)
  - Collection Traversal (forEach, forEachIndexed, iterator)
  - Collection Transformations (map, filter, reduce, fold, groupBy, flatMap)
  - Sequences (lazy evaluation)

**In-Class Exercises:**
- Function composition with lambdas
- Extending standard library classes with extension functions
- Collection transformations and data pipelines
- Building type-safe DSLs

**Learning Objectives:**
- Write and use lambda expressions and anonymous functions effectively
- Create and apply higher-order functions
- Extend existing classes with extension functions
- Use scope functions (let, run, with, apply, also) appropriately
- Choose appropriate collection types for different scenarios
- Transform and manipulate collections functionally using map, filter, reduce, etc.
- Understand and apply sequences for lazy evaluation
- Write efficient collection processing code

**Assignment:**
- Fantasy.Space:
  - Manage character roster with collections
  - Filter and sort characters by attributes
  - Track battle history using lists/maps
  - Aggregate game statistics

---

### Lesson 4: Advanced Functions & Lambdas, Generics, I/O
**Topics:**
- Exceptions & Error Handling:
    - Exceptions (throwing, catching, exception hierarchy)
    - Exception Handling (try-catch-finally, multiple catch blocks)
- I/O
    - Read/Write files
    - Command-line I/O

**Assignment:**
- Fantasy.Space:
  - Reading character data from a file
  - Writing battle logs to file
  - Handling file I/O errors gracefully

**Learning Objectives:**
- Understand exception hierarchy and when to use different exception types
- Handle exceptions with try-catch-finally blocks
- Throw and create custom exceptions
- Read from and write to files using Kotlin I/O
- Handle command-line input
- Apply proper error handling strategies in I/O operations

---

### Lesson 5: OOP Principles & Inheritance
**Topics:**
- Refresher:
    - Communication of Intent in Code
    - OOP Principles Overview
- OOP Principles:
    - Encapsulation (information hiding, access control)
    - Inheritance (extending classes, overriding)
    - Polymorphism (method overriding, type polymorphism)
    - Abstraction (abstract classes, interfaces)
- Generics:
    - Generic Classes & Functions
    - Type Parameters
    - Variance (in, out)
    - Reified Types
    - Constraints (upper bounds)

**In-Class Exercise:**
- TODO
- Generic collections with basic tests
- Creating reusable generic data structures

**Exercise:**
- Fantasy.Space with inheritance: base Character class, Warrior/Sorcerer/Archer subclasses, polymorphic behavior

**Learning Objectives:**
- Apply OOP principles (encapsulation, inheritance, polymorphism, abstraction) to design better software
- Use inheritance and polymorphism effectively
- Create and work with abstract classes and interfaces
- Understand when to use composition vs inheritance
- Write generic classes and functions with type parameters
- Apply variance (in, out) to generic types
- Use reified types for type-safe generic operations
- Apply generic constraints and bounds appropriately

---

## Week 2: Advanced Concepts & Architecture

### Lesson 6: Testing Fundamentals
**Topics:**
- Dependency Management, Logging, Debugging & Documentation:
    - Dependency Management (Gradle)
    - Logging (structured logging, log levels)
    - Debugging (breakpoints, step through, inspect variables)
    - Documentation (KDoc, comments)
- Testing Fundamentals (Theory):
    - Introduction to Testing (why test, testing pyramid)
    - Types of Testing (unit, integration, system, acceptance)
    - Test Cases & Test Suites
    - Test Design Techniques (equivalence partitioning, boundary value analysis)
- Unit Testing & Integration Testing:
    - Unit Testing (AAA pattern, assertions)
    - Integration Testing (testing multiple components)
    - Testing Layers (repository, service, controller testing) - theory only
    - JUnit (test annotations, lifecycle, assertions)
    - Kotest (BDD style, matchers, property testing)

**In-Class Exercises:**
- TODO

**Practice:**
- Test Fantasy.Space with error scenarios: invalid input, edge cases, exception handling, mocking

**Learning Objectives:**
- Set up and manage project dependencies with Gradle
- Implement structured logging at appropriate levels
- Use debugging tools effectively (breakpoints, step-through, variable inspection)
- Write clear documentation with KDoc
- Understand the testing pyramid and different types of tests
- Design effective test cases using equivalence partitioning and boundary value analysis
- Write unit tests following AAA (Arrange-Act-Assert) pattern
- Write integration tests for multiple components
- Use JUnit test framework with annotations and assertions
- Use Kotest for BDD-style testing with matchers
- Understand testing strategies for different application layers

---


### Lesson 7: Memory & Concurrency
**Topics:**
- Java Virtual Machine (JVM architecture, bytecode)
- JRE, JDK, Compiler (toolchain overview)
- Java Memory Management (heap, stack, garbage collection)
- Multithreading (threads, Thread class, Runnable)
- Thread synchronization and safety
- Coroutines (suspend functions, coroutine builders, coroutine scope)
- Coroutine dispatchers and contexts
- Brief: Command-line I/O, File basics (reading/writing files)

**In-Class Exercise:**
- CoffeeShop with concurrency: order queue, parallel order processing, thread safety

**Learning Objectives:**
- Understand JVM architecture and memory model
- Write concurrent code with threads
- Use Kotlin coroutines for asynchronous programming
- Understand thread safety and synchronization
---

### Lesson 8: SOLID Principles, Architecture & Application Design
**Topics:**
- SOLID Principles:
    - Single Responsibility Principle
    - Open/Closed Principle
    - Liskov Substitution Principle
    - Interface Segregation Principle
    - Dependency Inversion Principle
- Inversion of Control (IoC)
- Dependency Injection (constructor injection, property injection)
- Functional Programming Concepts (immutability, pure functions, higher-order functions)
- Design Patterns:
    - Creational: Factory, Builder, Singleton
    - Structural: Adapter, Decorator
    - Behavioral: Strategy, Observer, Template Method
- Architectural Patterns Overview (MVC, Layered Architecture)
  - Application Architecture (layered architecture, separation of concerns)
  - 12-Factor Applications (configuration, dependencies, backing services)

**Exercise:**
- Refactor Fantasy.Space with SOLID principles: separate concerns, inject dependencies, use design patterns

**Learning Objectives:**
- Apply SOLID principles to improve code design
- Implement dependency injection
- Recognize and apply common design patterns
- Balance OOP and functional programming approaches

---

### Lesson 9: Spring Boot Application
**Topics:**
- Spring Boot Application Design:
  - Spring Boot Introduction (what it is, ecosystem overview, core, beans, context)
  - Spring Boot Project Structure (main class, application properties, packages)
  - Spring Boot Setup (Spring Initializr, Gradle configuration)
  - Spring Framework (IoC container, dependency injection)
  - Application Lifecycle (startup, shutdown, events)
  - Application Properties & Configuration
- Architectural Patterns in Spring (MVC, layered architecture)
  - MVC Pattern (Model-View-Controller)
  - Controller Layer Pattern
  - Service Layer Pattern
  - Repository Pattern
  - Web-Flux Pattern (reactive programming)

**In-Class Exercise:**
- Setup Spring Boot application

**Learning Objectives:**
- Understand layered application architecture
- Learn Spring Boot ecosystem and purpose
- Set up Spring Boot project
- Configure Spring Boot applications

---

### Lesson 10: REST Architecture & Application Design
**Topics:**
- REST Architecture:
  - What is API? (Application Programming Interface)
  - What is REST? (RESTful principles, resource-oriented design)
  - HTTP Methods (GET, POST, PUT, PATCH, DELETE)
  - Request/Response structure (headers, body, status codes)
  - Statelessness (client-server separation, no session state)
- Spring Boot Application Design:
  - Controllers & Routing (@RestController, @RequestMapping, @GetMapping, @PostMapping)
  - Request Handling (@PathVariable, @RequestParam, @RequestBody)
  - DTOs & Request/Response Handling (data transfer objects, serialization)

**Assignment & Exercise:**
- Design Fantasy.Space API: endpoints, resources, request/response formats
- Build Fantasy.Space REST API:
    - GET /characters - list all characters
    - GET /characters/{id} - get character details
    - POST /characters - create new character
    - POST /battle - initiate battle between characters
    - 
**Learning Objectives:**
- Understand REST architectural principles
- Design RESTful APIs
- Create REST controllers with proper routing
- Handle different HTTP methods and request types
- Use DTOs for API contract

---

## Week 3: Spring Boot & Production

### Lesson 11: Spring Boot Business Logic
**Topics:**
- Service Layer in Spring (@Service, business logic separation)
- Dependency Injection in Spring (@Autowired, constructor injection)
- Spring Bean Scopes (singleton, prototype, request)
- Component Scanning (@Component, @ComponentScan)
- Exception Handling in Spring (@ExceptionHandler, @ControllerAdvice)
- Validation in Spring (@Valid, @NotNull, @Min, @Max, custom validators)
- Date/Time Handling:
    - LocalDate, LocalTime, LocalDateTime
    - ZonedDateTime, Instant
    - Duration & Period
    - Formatting and parsing

**Assignment & Exercise:**
- Add business logic to Fantasy.Space:
    - Character creation validation
    - Battle logic implementation
    - Game statistics tracking
    - Error responses with proper HTTP status codes
    - Timestamps for battles and character creation

**Learning Objectives:**
- Implement service layer with business logic
- Use Spring's dependency injection effectively
- Handle exceptions globally with @ControllerAdvice
- Validate input with Spring validation
- Work with date/time in applications

---

### Lesson 12: Spring Boot Data Layer
**Topics:**
- Repository Pattern in Spring
- Spring Data JPA (entities, repositories, CRUD operations)
- Database Access:
    - JDBC basics
    - JPA concepts (persistence, entities, relationships)
    - Exposed Framework (Kotlin SQL library)
- Entity Relationships (@OneToMany, @ManyToOne, @ManyToMany)
- Database Transactions (@Transactional)
- Testing Spring Applications:
    - @SpringBootTest
    - @WebMvcTest, @DataJpaTest
    - Mocking with MockK
    - Testing Layers (repository, service, controller)
    - Test containers

**Assignment & Exercise:**
- Add database to Fantasy.Space API:
    - Character persistence
    - Battle history storage
    - Query characters by type
    - Retrieve battle statistics
    - Write integration tests for database operations

**Learning Objectives:**
- Implement repository layer with Spring Data
- Persist data with JPA or Exposed
- Write database migrations
- Test Spring Boot applications at different layers
- Use mocking for isolated unit tests

---

### Lesson 13: Security & Authentication
**Topics:**
- Authentication vs Authorization
- Authentication Mechanisms:
  - Basic Authentication
  - Token-based Authentication
  - JWT (JSON Web Tokens) - structure, signing, verification
- Authorization (roles, permissions)
- Spring Security:
  - Security configuration
  - User authentication
  - Protecting endpoints
  - Password encoding
- Reflection (brief: @Annotations, runtime type inspection)
- Security Best Practices

**Assignment & Exercise:**
- Secure Fantasy.Space API:
  - User registration and login
  - JWT token generation
  - Protected endpoints (only authenticated users can create characters/battles)
  - Role-based access (admin vs player)
  - Secure password storage

**Learning Objectives:**
- Implement authentication with JWT
- Protect API endpoints
- Understand authorization and access control
- Use Spring Security framework
- Follow security best practices

---

### Lesson 14: Deployment, Observability & Final Project
**Topics:**
- Build & Packaging:
  - Gradle build configuration
  - Creating executable JARs
  - Docker containerization (Dockerfile, docker-compose)
- Deployment Strategies:
  - Cloud platforms (Heroku, AWS, Google Cloud)
  - Container orchestration (Kubernetes basics)
  - Environment management (dev, staging, production)
- Observability:
  - Logging strategies (structured logging, log aggregation)
  - Metrics (Micrometer, application metrics)
  - Tracing (distributed tracing basics)
  - Health checks and monitoring
- Resilient Applications:
  - Graceful degradation
  - Retry mechanisms
  - Circuit breakers
  - Timeouts

**Final Project:**
- Complete Fantasy.Space backend:
  - All CRUD operations for characters
  - Battle system with history
  - User authentication and authorization
  - Database persistence
  - Comprehensive test suite
  - Deployed to cloud platform
  - Monitoring and health checks
  - API documentation (Swagger/OpenAPI)
  - **Project presentation and demo**

**Learning Objectives:**
- Build and package Spring Boot applications
- Deploy applications to cloud platforms
- Implement observability for production systems
- Build resilient, production-ready applications
- Document and present technical work

---

### Lesson 15: Project Work & Presentation

**Topics:**
- Final project integration and completion
- Code review and refactoring
- Documentation finalization
- Presentation preparation
- Demo and Q&A

**Activities:**
- Complete all Fantasy.Space features
- Write comprehensive README and API documentation
- Ensure all tests pass and coverage is adequate
- Prepare presentation slides
- Deploy final version
- Present project to class

**Learning Objectives:**
- Integrate all course concepts into a complete application
- Document code and APIs professionally
- Present technical work effectively to an audience
- Demonstrate problem-solving and debugging skills
- Reflect on design decisions and trade-offs
- Deliver a production-ready application

---

## Fantasy.Space Project Evolution

The Fantasy.Space project is built incrementally throughout the course:

**Week 1:**
- Lesson 1: Basic character stats and calculations
- Lesson 2: Character data models (Character, CharacterType, GameStats)
- Lesson 3: Battle system with collections
- Lesson 5: Inheritance-based character types with polymorphic behavior

**Week 2:**
- Lesson 6: Error handling and comprehensive tests for Fantasy.Space models and battle logic
- Lesson 8: Refactored with SOLID principles and design patterns
- Lesson 10: API design planning

**Week 3:**
- Lesson 11: Business logic and validation
- Lesson 12: Database persistence
- Lesson 13: Authentication and authorization
- Lesson 14: Deployment and observability
- Lesson 15: Final project work and presentation

---

## Assessment

- **Weekly Assignments (30%):** Fantasy.Space milestones
- **In-Class Exercises (20%):** Participation and completion
- **Tests (20%):** Written code for test coverage
- **Final Project (30%):** Complete Fantasy.Space backend with presentation

---

## Required Tools

- IntelliJ IDEA (or VS Code with Kotlin plugin)
- JDK 17+
- Gradle
- Git & GitHub
- Docker (for deployment)
- Postman or similar API testing tool

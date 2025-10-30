package com.motycka.edu.content.topics.testing

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.twoColumns
import kotlinx.html.*

object TestingLayersTopic : Topic(
    title = "Testing Application Layers",
    slides = listOf(
        TestingLayersIntroSlide,
        TestingUnitsInIsolationSlide,
        TestingRoutingLayerSlide,
        TestingServiceLayerSlide,
        TestingRepositoryLayerSlide,
        ApplicationIntegrationTestingSlide
    )
)

object TestingLayersIntroSlide : Slide(
    header = "Testing Application Layers - General Idea",
    summary = {
        ul {
            li { +"Testing units in isolation" }
            li { +"Testing layers in isolation" }
            li { +"Testing integrations" }
        }
    },
    content = {
        p {
            +"This approach allows us in detail test each part of the application, verifying it's core functionality, "
            +"without the overhead of testing the entire application at once."
        }
        p {
            +"Interaction between layers and components can be tested using mocks and stubs."
        }
        p {
            +"Integration tests can be used to verify real interaction between different layers and components of the application, "
            +"but because we have tested each part in isolation, we don't need to cover all functionality so deeply."
        }
        p {
            +"The aim is to achieve maximum coverage with minimum resources (and time) "
            +"but still give us a high level of confidence that the application is working correctly."
        }
    }
)

object TestingUnitsInIsolationSlide : Slide(
    header = "Testing Units in Isolation",
    summary = {
        +"Unit tests verify individual units of code (functions, methods, classes) in complete isolation."
    },
    content = {
        twoColumns(
            left = {
                p {
                    strong { +"What is tested:" }
                }
                ul {
                    li { +"Individual functions and methods" }
                    li { +"Business logic calculations" }
                    li { +"Data transformations" }
                    li { +"Validation rules" }
                    li { +"Pure domain logic without dependencies" }
                }

                p {
                    strong { +"How it is tested:" }
                }
                ul {
                    li { +"Test the unit directly with known inputs" }
                    li { +"No mocking required - pure functions with no dependencies" }
                    li { +"Verify expected outputs for various inputs" }
                    li { +"Test edge cases and boundary conditions" }
                    li { +"Fast execution - milliseconds per test" }
                }
            },
            right = {
                p {
                    strong { +"Why we test this way:" }
                }
                ul {
                    li { +"Fastest tests to write and execute" }
                    li { +"Easiest to understand and maintain" }
                    li { +"Precise error localization - failures point exactly to the problem" }
                    li { +"No external dependencies means no flakiness" }
                    li { +"Forms the base of the testing pyramid - should have the most tests here" }
                }

                p {
                    strong { +"Example:" }
                    +" Testing an Order price calculation that applies customer discounts to order items. Test with different item counts, prices, and discount percentages."
                }
            },
        )
    }
)

object TestingRoutingLayerSlide : Slide(
    header = "Testing Routing Layer (Controller)",
    summary = {
        +"Controller tests verify HTTP routing, request handling, and response formatting without testing business logic."
    },
    content = {
        twoColumns(
            left = {
                p {
                    strong { +"What is tested:" }
                }
                ul {
                    li { +"Route definitions (correct URLs and HTTP methods)" }
                    li { +"Request parameter extraction (path variables, query params, request body)" }
                    li { +"Request validation ("; inlineCode("@Valid"); +" annotations)" }
                    li { +"Response status codes (200, 201, 404, 400, etc.)" }
                    li { +"Response serialization (DTOs to JSON)" }
                    li { +"Authentication and authorization checks" }
                }

                p {
                    strong { +"How it is tested:" }
                }
                ul {
                    li { +"Mock the service layer dependencies" }
                    li { +"Make HTTP requests to controller endpoints" }
                    li { +"Verify service methods are called with correct parameters" }
                    li { +"Assert response status codes and body content" }
                    li { +"Test both successful and error scenarios" }
                }
            },
            right = {
                p {
                    strong { +"Why we test this way:" }
                }
                ul {
                    li { +"Isolates routing concerns from business logic" }
                    li { +"Fast execution - no database or complex business logic" }
                    li { +"Verifies the API contract (endpoints, request/response formats)" }
                    li { +"Catches routing mistakes early (wrong paths, methods, parameter names)" }
                    li { +"Ensures proper error handling and status codes" }
                }

                p {
                    strong { +"Example:" }
                    +" Testing GET "; inlineCode("/api/tasks/{id}"); +" extracts the ID correctly, calls "; inlineCode("taskService.getTaskById(id)")
                    +", and returns 200 with task JSON or 404 if not found."
                }
            },
        )
    }
)

object TestingServiceLayerSlide : Slide(
    header = "Testing Service Layer",
    summary = {
        +"Service tests verify business logic, data transformations, and orchestration without database dependencies."
    },
    content = {
        twoColumns(
            left = {
                p {
                    strong { +"What is tested:" }
                }
                ul {
                    li { +"Business logic and rules" }
                    li { +"Data validation (business rules, not just format)" }
                    li { +"Authorization logic (who can do what)" }
                    li { +"Data transformation (Entity ↔ Domain ↔ DTO)" }
                    li { +"Orchestration of multiple repository calls" }
                    li { +"Exception handling and error messages" }
                }

                p {
                    strong { +"How it is tested:" }
                }
                ul {
                    li { +"Mock repository dependencies with MockK" }
                    li { +"Define expected repository behavior with "; inlineCode("every { ... } returns ...") }
                    li { +"Call service methods with various inputs" }
                    li { +"Assert returned values and business logic outcomes" }
                    li { +"Verify repository methods called with correct parameters" }
                    li { +"Test both happy path and error scenarios" }
                }
            },
            right = {
                p {
                    strong { +"Why we test this way:" }
                }
                ul {
                    li { +"Isolates business logic from database operations" }
                    li { +"Fast execution - no database queries" }
                    li { +"Full control over data scenarios (including edge cases)" }
                    li { +"Tests the core value of the application (business rules)" }
                    li { +"Easy to test error conditions (not found, conflicts, etc.)" }
                }

                p {
                    strong { +"Example:" }
                    +" Testing "; inlineCode("taskService.assignTask(taskId, userId)"); +" verifies: task exists, user exists, user not over task limit, "
                    +"task not completed/archived, calls repository to update assignment."
                }
            },
        )
    }
)

object TestingRepositoryLayerSlide : Slide(
    header = "Testing Repository Layer",
    summary = {
        +"Repository tests verify database operations using a real database (in-memory or containerized)."
    },
    content = {
        twoColumns(
            left = {
                p {
                    strong { +"What is tested:" }
                }
                ul {
                    li { +"CRUD operations (Create, Read, Update, Delete)" }
                    li { +"Query methods (find by ID, find by criteria, search)" }
                    li { +"Database constraints (unique, not null, foreign keys)" }
                    li { +"Transactions and rollback behavior" }
                    li { +"Data mapping (Entity to/from database tables)" }
                    li { +"Complex queries and joins" }
                }

                p {
                    strong { +"How it is tested:" }
                }
                ul {
                    li { strong { +"Never mock the database" }; +" - use a real database instance" }
                    li { +"Use in-memory database (H2) or containerized database (Testcontainers with PostgreSQL)" }
                    li { +"Set up schema before each test "; inlineCode("beforeEach") }
                    li { +"Seed test data as needed" }
                    li { +"Execute repository operations" }
                    li { +"Query database to verify changes" }
                    li { +"Clean up after each test "; inlineCode("afterEach") }
                }
            },
            right = {
                p {
                    strong { +"Why we test this way:" }
                }
                ul {
                    li { +"Verifies SQL queries are correct" }
                    li { +"Catches database-specific issues (constraints, types, indexes)" }
                    li { +"Tests real database behavior, not mocked behavior" }
                    li { +"Isolated from business logic - focuses only on data access" }
                    li { +"In-memory databases keep tests reasonably fast" }
                }

                p {
                    strong { +"Example:" }
                    +" Testing "; inlineCode("taskRepository.findByStatus(TaskStatus.TODO)"); +" creates tasks with various statuses, "
                    +"calls the method, verifies only TODO tasks returned."
                }
            }
        )
    }
)

object ApplicationIntegrationTestingSlide : Slide(
    header = "Integration Testing",
    summary = {
        +"Integration tests verify that all layers of the application work together correctly in a production-like environment."
    },
    fontSize = "70%",
    content = {
        twoColumns(
            left = {
                p {
                    strong { +"What is tested:" }
                }
                ul {
                    li { +"End-to-end functionality through the API" }
                    li { +"Real dependency injection (Spring application context)" }
                    li { +"Complete request flow: Controller → Service → Repository → Database" }
                    li { +"Data persistence and retrieval" }
                    li { +"Transaction management" }
                    li { +"Authentication and authorization" }
                    li { +"Exception handling across all layers" }
                }

                p {
                    strong { +"How it is tested:" }
                }
                ul {
                    li { +"Start the entire application in a test environment" }
                    li { +"Use a real database (containerized with Testcontainers)" }
                    li { +"Make HTTP requests as a client would (using test HTTP client)" }
                    li { +"Seed database with test data before each test" }
                    li { +"Verify responses and database state" }
                    li { +"Clean up database after each test" }
                }
            },
            right = {
                p {
                    strong { +"Why we test this way:" }
                }
                ul {
                    li { +"Catches integration issues between layers" }
                    li { +"Verifies dependency injection configuration" }
                    li { +"Tests the application as users will use it" }
                    li { +"Finds issues that unit tests cannot (configuration, wiring)" }
                    li { +"Provides confidence in deployment" }
                }

                p {
                    strong { +"Trade-offs:" }
                }
                ul {
                    li { strong { +"Slower" }; +" - seconds per test instead of milliseconds" }
                    li { strong { +"Complex setup" }; +" - database, test data, application startup" }
                    li { strong { +"Harder to debug" }; +" - failures can be in any layer" }
                    li { strong { +"Fewer tests needed" }; +" - complement unit tests, don't replace them" }
                }

                p {
                    strong { +"Example:" }
                    +" Test POST "; inlineCode("/api/tasks"); +" with task data, verify 201 Created response, "
                    +"then GET the task by ID to confirm it was saved to database correctly."
                }
            },
        )
    }
)

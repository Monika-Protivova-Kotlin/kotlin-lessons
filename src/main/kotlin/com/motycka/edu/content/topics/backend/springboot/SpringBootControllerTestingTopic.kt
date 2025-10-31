package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.*

object SpringBootControllerTestingTopic : Topic(
    title = "Testing Controllers",
    slides = listOf(
        ControllerTestingIntroSlide,
        WebMvcTestAnnotationSlide,
        MockMvcBasicsSlide,
        TestingGetEndpointSlide,
        TestingPostEndpointSlide,
        TestingErrorResponsesSlide
    )
)

object ControllerTestingIntroSlide : Slide(
    header = "Testing Controllers",
    summary = {
        +"Controller tests verify HTTP routing, request handling, and response formatting without testing business logic."
    },
    content = {
        twoColumns(
            left = {
                p {
                    strong { +"What we test in controllers:" }
                }
                ul {
                    li { +"Route definitions (correct URLs and HTTP methods)" }
                    li { +"Request parameter extraction (path variables, query params, body)" }
                    li { +"Request validation ("; inlineCode("@Valid"); +" annotations)" }
                    li { +"Response status codes (200, 201, 404, 400, etc.)" }
                    li { +"Response body serialization (objects to JSON)" }
                    li { +"Service layer is called with correct parameters" }
                }

                p {
                    strong { +"Why we test controllers separately:" }
                }
                ul {
                    li { +"Isolates routing concerns from business logic" }
                    li { +"Fast execution - no database or complex business logic" }
                    li { +"Verifies the API contract (endpoints, request/response formats)" }
                    li { +"Catches routing mistakes early (wrong paths, methods, parameter names)" }
                }
            },
            right = {
                p {
                    strong { +"Key tools:" }
                }
                ul {
                    li { inlineCode("@WebMvcTest"); +" - Loads only the web layer (controllers)" }
                    li { inlineCode("MockMvc"); +" - Simulates HTTP requests without starting a server" }
//                    li { inlineCode("@MockkBean"); +" - Mocks service layer dependencies" }
                    li { inlineCode("MockK"); +" - Mocking library for Kotlin" }
                }
            },
        )
    }
)

object WebMvcTestAnnotationSlide : Slide(
    header = "@WebMvcTest with MockK Configuration",
    summary = {
        +""
        inlineCode("@WebMvcTest")
        +" loads only the web layer. Use "
        inlineCode("@TestConfiguration")
        +" to provide MockK beans instead of Mockito's @MockBean."
    },
    content = {
        twoColumns(
            ratio = 2 to 3,
            left = {
                p {
                    +"The "
                    inlineCode("@WebMvcTest")
                    +" annotation tells Spring Boot to:"
                }
                ul {
                    li { +"Load only the controller layer (not services or repositories)" }
                    li { +"Configure "; inlineCode("MockMvc"); +" for testing" }
                    li { +"Auto-configure Spring MVC infrastructure" }
                    li { +"Not load the full application context (fast startup)" }
                }
                p {
                    strong { +"MockK setup with @TestConfiguration:" }
                }
                ul {
                    li { +"Create nested "; inlineCode("@TestConfiguration"); +" class" }
                    li { +"Define "; inlineCode("@Bean"); +" methods that return "; inlineCode("mockk()") }
                    li { +"Import config with "; inlineCode("@Import(TestConfig::class)") }
                    li { +"Use "; inlineCode("beforeEach"); +" to clear mocks between tests" }
                }
            },
            right = {
                kotlinPlayground(
                    code = """
                        @WebMvcTest(TaskController::class)
                        @Import(TaskControllerTest.TaskControllerTestConfig::class)
                        class TaskControllerTest : FunSpec() {

                            @Autowired
                            private lateinit var mockMvc: MockMvc

                            @Autowired
                            private lateinit var taskService: TaskService

                            @TestConfiguration
                            class TaskControllerTestConfig {
                                @Bean
                                fun taskService(): TaskService = mockk(relaxed = false)
                            }

                            init {
                                beforeEach {
                                    clearMocks(taskService)
                                }

                                test("controller is loaded") {
                                    // MockMvc and controller are auto-configured
                                }
                            }
                        }
                    """.trimIndent(),
                    executable = false
                )
            }
        )
    }
)

object MockMvcBasicsSlide : Slide(
    header = "MockMvc Basics",
//    summary = {
//        +""
//        inlineCode("MockMvc")
//        +" allows you to test controllers by simulating HTTP requests and verifying responses."
//    },
    content = {
        twoColumns(
            ratio = 2 to 3,
            left = {
                p {
                    strong { +"Common HTTP methods:" }
                }
                ul {
                    li { inlineCode("get(\"/api/tasks\")"); +" - GET request" }
                    li { inlineCode("post(\"/api/tasks\")"); +" - POST request" }
                    li { inlineCode("put(\"/api/tasks/{id}\", id)"); +" - PUT request" }
                    li { inlineCode("delete(\"/api/tasks/{id}\", id)"); +" - DELETE request" }
                }

                p {
                    strong { +"Common assertions:" }
                }
                ul {
                    li { inlineCode("status().isOk"); +" - 200" }
                    li { inlineCode("status().isCreated"); +" - 201" }
                    li { inlineCode("status().isNotFound"); +" - 404" }
                    li { inlineCode("status().isBadRequest"); +" - 400" }
                    li { inlineCode("jsonPath(\"\$.field\").value(expected)"); +" - Assert JSON field" }
                }
            },
            right = {
                p {
                    +"Basic MockMvc request structure:"
                }
                kotlinPlayground(
                    code = """
                        import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
                        import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

                        mockMvc.perform(
                            get("/api/tasks/{id}", taskId)                // HTTP method and URL
                                .contentType(MediaType.APPLICATION_JSON)   // Request content type
                                .accept(MediaType.APPLICATION_JSON)        // Expected response type
                        )
                            .andExpect(status().isOk)                      // Assert status code
                            .andExpect(content().contentType(MediaType.APPLICATION_JSON))  // Assert content type
                            .andExpect(jsonPath("$.id").value(taskId))     // Assert JSON fields
                    """.trimIndent(),
                    executable = false
                )
            }
        )
    }
)

object TestingGetEndpointSlide : Slide(
    header = "Testing GET Endpoint",
//    summary = {
//        +"Test GET endpoints by mocking the service layer and verifying the response."
//    },
    content = {
        twoColumns(
            ratio = 2 to 3,
            left = {
                p {
                    strong { +"Test structure:" }
                }
                ul {
                    li { strong { +"Arrange:" }; +" Mock service to return test data" }
                    li { strong { +"Act:" }; +" Perform GET request with MockMvc" }
                    li { strong { +"Assert:" }; +" Verify response status and body" }
                    li { strong { +"Verify:" }; +" Confirm service method was called" }
                }
                p {
                    strong { +"What we verify:" }
                }
                ul {
                    li { +"HTTP status code (200 OK)" }
                    li { +"Content type (application/json)" }
                    li { +"JSON response fields match expected values" }
                    li { +"Service method called exactly once with correct ID" }
                }
            },
            right = {
                kotlinPlayground(
                    code = """
                        import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
                        import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
                        import io.mockk.every
                        import io.mockk.verify

                        @WebMvcTest(TaskController::class)
                        @Import(TaskControllerTest.TaskControllerTestConfig::class)
                        class TaskControllerTest : FunSpec() {

                            @Autowired
                            private lateinit var mockMvc: MockMvc

                            @Autowired
                            private lateinit var taskService: TaskService

                            init {
                                beforeEach {
                                    clearMocks(taskService)
                                }

                                test("should return task when it exists") {
                                    // Arrange: Mock service to return a task
                                    val task = Task(id = 1, description = "Write tests", status = TaskStatus.TODO)
                                    every { taskService.getTask(1L) } returns task

                                    // Act & Assert: Perform request and verify response
                                    mockMvc.perform(get("/api/tasks/1"))
                                        .andExpect(status().isOk)
                                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                        .andExpect(jsonPath("$.id").value(1))
                                        .andExpect(jsonPath("$.description").value("Write tests"))
                                        .andExpect(jsonPath("$.status").value("TODO"))

                                    // Verify service was called
                                    verify(exactly = 1) { taskService.getTask(1L) }
                                }
                            }
                        }
                    """.trimIndent(),
                    executable = false
                )
            }
        )
    },
    fontSize = "70%"
)

object TestingPostEndpointSlide : Slide(
    header = "Testing POST Endpoint",
//    summary = {
//        +"Test POST endpoints by sending request body and verifying the created resource."
//    },
    content = {
        twoColumns(
            ratio = 2 to 3,
            left = {
                p {
                    strong { +"Key differences from GET:" }
                }
                ul {
                    li { +"Send request body as JSON" }
                    li { +"Use "; inlineCode("ObjectMapper"); +" to serialize DTO" }
                    li { +"Set content type to application/json" }
                    li { +"Expect 201 Created status" }
                }
                p {
                    strong { +"What we verify:" }
                }
                ul {
                    li { +"201 Created status code" }
                    li { +"Response contains created resource with ID" }
                    li { +"Service called with correct request DTO" }
                }
            },
            right = {
                kotlinPlayground(
                    code = """
                        import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
                        import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
                        import com.fasterxml.jackson.databind.ObjectMapper
                        import io.mockk.every
                        import io.mockk.verify

                        @WebMvcTest(TaskController::class)
                        @Import(TaskControllerTest.TaskControllerTestConfig::class)
                        class TaskControllerTest : FunSpec() {

                            @Autowired
                            private lateinit var mockMvc: MockMvc

                            @Autowired
                            private lateinit var objectMapper: ObjectMapper

                            @Autowired
                            private lateinit var taskService: TaskService

                            init {
                                beforeEach {
                                    clearMocks(taskService)
                                }

                                test("should create task and return 201 with task response") {
                                    // Arrange: Prepare request and mock response
                                    val request = NewTaskRequest(description = "New Task")
                                    val createdTask = Task(id = 1, description = "New Task", status = TaskStatus.NEW)
                                    every { taskService.addTask("New Task") } returns createdTask

                                    // Act & Assert
                                    mockMvc.perform(
                                        post("/api/tasks")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(objectMapper.writeValueAsString(request))
                                    )
                                        .andExpect(status().isCreated)
                                        .andExpect(jsonPath("$.id").value(1))
                                        .andExpect(jsonPath("$.description").value("New Task"))
                                        .andExpect(jsonPath("$.status").value("NEW"))

                                    // Verify service was called
                                    verify(exactly = 1) { taskService.addTask("New Task") }
                                }
                            }
                        }
                    """.trimIndent(),
                    executable = false
                )
            }
        )
    },
    fontSize = "70%"
)

object TestingErrorResponsesSlide : Slide(
    header = "Testing Error Responses",
//    summary = {
//        +"Test error handling by making the service throw exceptions and verifying error responses."
//    },
    content = {
        twoColumns(
            ratio = 2 to 3,
            left = {
                p {
                    strong { +"Testing strategy:" }
                }
                ul {
                    li { +"Mock service to throw exceptions" }
                    li { +"Verify correct HTTP status codes" }
                    li { +"Check error response structure" }
                    li { +"Confirm error messages are clear" }
                }
                p {
                    strong { +"Common error scenarios:" }
                }
                ul {
                    li { +"404 Not Found - resource doesn't exist" }
                    li { +"400 Bad Request - validation failure" }
                    li { +"403 Forbidden - unauthorized access" }
                    li { +"409 Conflict - state violation" }
                }
                p {
                    strong { +"What this verifies:" }
                }
                ul {
                    li { inlineCode("@ControllerAdvice"); +" handles exceptions" }
                    li { +"Error responses match API contract" }
                    li { +"Proper HTTP status codes returned" }
                }
            },
            right = {
                kotlinPlayground(
                    code = """
                        import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
                        import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
                        import io.mockk.every

                        @WebMvcTest(TaskController::class)
                        @Import(TaskControllerTest.TaskControllerTestConfig::class)
                        class TaskControllerTest : FunSpec() {

                            @Autowired
                            private lateinit var mockMvc: MockMvc

                            @Autowired
                            private lateinit var objectMapper: ObjectMapper

                            @Autowired
                            private lateinit var taskService: TaskService

                            init {
                                beforeEach {
                                    clearMocks(taskService)
                                }

                                test("should return 404 when task does not exist") {
                                    // Arrange: Mock service to throw exception
                                    every { taskService.getTask(1L) } throws TaskNotFoundException(1L)

                                    // Act & Assert: Verify 404 response
                                    mockMvc.perform(get("/api/tasks/1"))
                                        .andExpect(status().isNotFound)
                                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                        .andExpect(jsonPath("$.status").value(404))
                                        .andExpect(jsonPath("$.message").value("Task with id 1 not found"))
                                }

                                test("should return 400 when description is blank") {
                                    // Arrange: Mock service to throw validation exception
                                    val request = NewTaskRequest(description = "")
                                    every { taskService.addTask("") } throws
                                        InvalidTaskException("Task description cannot be blank")

                                    // Act & Assert: Verify 400 response
                                    mockMvc.perform(
                                        post("/api/tasks")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(objectMapper.writeValueAsString(request))
                                    )
                                        .andExpect(status().isBadRequest)
                                        .andExpect(jsonPath("$.status").value(400))
                                        .andExpect(jsonPath("$.message").value("Task description cannot be blank"))
                                }
                            }
                        }
                    """.trimIndent(),
                    executable = false
                )
            }
        )
    },
    fontSize = "65%"
)

package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.twoColumns
import kotlinx.html.*

object SpringBootServiceTestingTopic : Topic(
    title = "Testing Services",
    slides = listOf(
        ServiceTestingIntroSlide,
        ServiceIntegrationTestingSlide,
        ServiceUnitTestingSlide
    )
)

object ServiceTestingIntroSlide : Slide(
    header = "Testing Services",
    summary = {
        +"Service layer tests verify business logic without database dependencies by mocking the repository layer."
    },
    content = {
        p {
            +"Testing the service layer focuses on verifying business logic while isolating it from database concerns."
        }
        p {
            strong { +"What we test:" }
        }
        ul {
            li { +"Business logic and rules" }
            li { +"Data transformations (DTO → Domain → Entity)" }
            li { +"Authorization checks" }
            li { +"Validation logic" }
            li { +"Exception handling" }
        }
        p {
            strong { +"Two testing approaches:" }
        }
        ul {
            li {
                strong { +"Integration Testing" }
                +" - Uses Spring Boot context with real database (slower but tests DI)"
            }
            li {
                strong { +"Unit Testing" }
                +" - Manually injects mocked dependencies (faster, more isolated)"
            }
        }
        p {
            strong { +"Recommendation:" }
            +" Use unit tests with mocks for most service tests (fast feedback), "
            +"and complement with a few integration tests to verify dependency injection and database interaction."
        }
    }
)

object ServiceIntegrationTestingSlide : Slide(
    header = "Integration Testing with Spring Boot",
    summary = {
        +"Integration tests use Spring Boot's dependency injection and a real database to verify service behavior."
    },
    content = {
        twoColumns(
            ratio = 2 to 3,
            left = {
                p {
                    +"Use "
                    inlineCode("@SpringBootTest")
                    +" to load the full Spring application context with all beans and dependencies."
                }
                p {
                    strong { +"Key annotations:" }
                }
                ul {
                    li { inlineCode("@SpringBootTest"); +" - Loads full Spring context" }
                    li { inlineCode("@Transactional"); +" - Auto-rollback after each test" }
                    li { inlineCode("@Autowired"); +" - Inject real beans" }
                }
                p {
                    strong { +"What we test:" }
                }
                ul {
                    li { +"Business logic with real database" }
                    li { +"Dependency injection wiring" }
                    li { +"Data persistence verification" }
                    li { +"Full integration flow" }
                }
                p {
                    strong { +"Pros:" }
                    +" Tests DI, uses real database, tests full integration."
                }
                p {
                    strong { +"Cons:" }
                    +" Slower startup, more resource intensive, harder to isolate failures."
                }
            },
            right = {
                kotlinPlayground(
                    code = """
                        import io.kotest.core.spec.style.FunSpec
                        import io.kotest.matchers.shouldBe
                        import org.springframework.boot.test.context.SpringBootTest
                        import org.springframework.beans.factory.annotation.Autowired
                        import org.springframework.transaction.annotation.Transactional

                        @SpringBootTest
                        @Transactional
                        class TaskServiceIT : FunSpec() {

                            @Autowired
                            private lateinit var taskRepository: TaskRepository

                            @Autowired
                            private lateinit var taskService: TaskService

                            init {
                                test("createTask should save and return task") {
                                    // Arrange
                                    val request = NewTaskRequest(
                                        description = "Write integration tests"
                                    )
                                    val userId = 1L

                                    // Act
                                    val result = taskService.createTask(request, userId)

                                    // Assert
                                    result.description shouldBe "Write integration tests"
                                    result.status shouldBe TaskStatus.TODO

                                    // Verify saved in database
                                    val saved = taskRepository.findById(result.id.toLong())
                                    saved?.description shouldBe "Write integration tests"
                                }

                                test("assignTask should validate business rules") {
                                    // Arrange: Create a task
                                    val task = taskRepository.save(TaskEntity(
                                        description = "Task to assign",
                                        status = TaskStatus.TODO,
                                        createdBy = 1L
                                    ))

                                    // Act & Assert: Verify assignment logic
                                    val result = taskService.assignTask(task.id!!, userId = 2L)
                                    result.assignedTo shouldBe 2L
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

object ServiceUnitTestingSlide : Slide(
    header = "Unit Testing with Mocks",
    summary = {
        +"Unit tests manually inject mocked dependencies for fast, isolated testing of business logic."
    },
    content = {
        twoColumns(
            ratio = 2 to 3,
            left = {
                p {
                    +"Manually create the service with mocked repository dependencies using MockK."
                }
                p {
                    strong { +"Key setup:" }
                }
                ul {
                    li { +"Create mocks with "; inlineCode("mockk()") }
                    li { +"Define behavior with "; inlineCode("every { ... } returns ...") }
                    li { +"Verify calls with "; inlineCode("verify { ... }") }
                    li { +"No Spring context needed" }
                }
                p {
                    strong { +"What we test:" }
                }
                ul {
                    li { +"Business logic in isolation" }
                    li { +"Error handling (exceptions)" }
                    li { +"Authorization checks" }
                    li { +"Repository interaction" }
                }
                p {
                    strong { +"Pros:" }
                    +" Fast execution, fully isolated, easy to test error conditions."
                }
                p {
                    strong { +"Cons:" }
                    +" Doesn't test dependency injection wiring or database integration."
                }
            },
            right = {
                kotlinPlayground(
                    code = """
                        import io.kotest.core.spec.style.FunSpec
                        import io.kotest.matchers.shouldBe
                        import io.mockk.every
                        import io.mockk.mockk
                        import io.mockk.verify

                        class TaskServiceTest : FunSpec() {

                            private val taskRepository: TaskRepository = mockk()
                            private val taskService: TaskService = TaskService(
                                taskRepository = taskRepository
                            )

                            init {
                                test("createTask should save and return task") {
                                    // Arrange
                                    val request = NewTaskRequest(description = "Write unit tests")
                                    val userId = 1L
                                    val taskEntity = TaskEntity(
                                        id = 1L,
                                        description = "Write unit tests",
                                        status = TaskStatus.TODO,
                                        createdBy = userId
                                    )
                                    every { taskRepository.save(any()) } returns taskEntity

                                    // Act
                                    val result = taskService.createTask(request, userId)

                                    // Assert
                                    result.description shouldBe "Write unit tests"
                                    result.status shouldBe TaskStatus.TODO
                                    verify(exactly = 1) { taskRepository.save(any()) }
                                }

                                test("deleteTask should throw exception when task not found") {
                                    // Arrange
                                    every { taskRepository.findById(999L) } returns null

                                    // Act & Assert
                                    shouldThrow<TaskNotFoundException> {
                                        taskService.deleteTask(999L, userId = 1L)
                                    }
                                }

                                test("deleteTask should throw exception for unauthorized user") {
                                    // Arrange
                                    val task = TaskEntity(id = 1L, description = "Test", status = TaskStatus.TODO, createdBy = 1L)
                                    every { taskRepository.findById(1L) } returns task

                                    // Act & Assert
                                    shouldThrow<UnauthorizedException> {
                                        taskService.deleteTask(1L, userId = 999L)
                                    }
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

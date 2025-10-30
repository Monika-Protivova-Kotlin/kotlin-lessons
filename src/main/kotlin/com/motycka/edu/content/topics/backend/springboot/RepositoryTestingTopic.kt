package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object RepositoryTestingTopic : Topic(
    title = "Testing Repositories",
    slides = listOf(
        RepositoryTestingIntroSlide,
        JdbcTestSlide,
        JPATestSlide,
        TestContainersSlide
    )
)

object RepositoryTestingIntroSlide : Slide(
    header = "Testing Repositories",
    summary = {
        +"Repository tests verify database operations using a real database (in-memory or containerized)."
    },
    content = {
        p {
            +"Testing the repository layer requires a real database to verify SQL queries, constraints, and data mapping. "
            +"We need to ensure the database is in a known state before running each test."
        }
        p {
            strong { +"Key principles:" }
        }
        ul {
            li { strong { +"Never mock the database" }; +" - Use a real database instance" }
            li { +"Use in-memory database (H2) or containerized database (Testcontainers)" }
            li { +"Reset database state before each test" }
            li { +"Focus on data access logic, not business logic" }
        }
        p {
            +"To build the correct testing context with Spring, use:"
        }
        ul {
            li { inlineCode("@JdbcTest"); +" - For JDBC-based repositories" }
            li { inlineCode("@DataJpaTest"); +" - For JPA repositories" }
        }
        p {
            +"Additional useful annotations:"
        }
        ul {
            li { inlineCode("@Import"); +" - Import repository class if needed" }
            li { inlineCode("@Autowired"); +" - Inject repository and dependencies" }
            li { inlineCode("@DirtiesContext"); +" - Reset database state before each test" }
        }
        blockQuote {
            +"In-memory H2 database is convenient for testing, but production uses PostgreSQL/MySQL. "
            +"For more realistic tests, use "
            strong(classes = "inline") { +"TestContainers" }
            +" to run tests against containerized production databases."
        }
    }
)

object JdbcTestSlide : Slide(
    header = "Testing JDBC Repositories",
    summary = {
        +"Testing repositories using JdbcTemplate with "
        inlineCode("@JdbcTest")
        +"."
    },
    content = {
        p {
            inlineCode("@JdbcTest")
            +" configures an in-memory database and JdbcTemplate for testing JDBC repositories."
        }
        kotlinPlayground(
            code = """
                import io.kotest.core.spec.style.FunSpec
                import io.kotest.matchers.shouldBe
                import org.springframework.beans.factory.annotation.Autowired
                import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
                import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
                import org.springframework.context.annotation.Import
                import org.springframework.jdbc.core.JdbcTemplate
                import org.springframework.test.annotation.DirtiesContext

                @JdbcTest
                @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
                @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
                @Import(TaskJdbcRepository::class)
                class TaskJdbcRepositoryTest : FunSpec() {

                    @Autowired
                    private lateinit var jdbcTemplate: JdbcTemplate

                    @Autowired
                    private lateinit var taskRepository: TaskJdbcRepository

                    init {
                        test("findAll should return all tasks") {
                            // Arrange: Insert test data directly
                            jdbcTemplate.update(
                                "INSERT INTO tasks (description, status, created_by) VALUES (?, ?, ?)",
                                "Task 1", "TODO", 1
                            )
                            jdbcTemplate.update(
                                "INSERT INTO tasks (description, status, created_by) VALUES (?, ?, ?)",
                                "Task 2", "IN_PROGRESS", 1
                            )

                            // Act
                            val tasks = taskRepository.findAll()

                            // Assert
                            tasks.size shouldBe 2
                            tasks[0].description shouldBe "Task 1"
                            tasks[1].description shouldBe "Task 2"
                        }

                        test("findById should return task when exists") {
                            // Arrange
                            jdbcTemplate.update(
                                "INSERT INTO tasks (id, description, status, created_by) VALUES (?, ?, ?, ?)",
                                1, "Test Task", "TODO", 1
                            )

                            // Act
                            val task = taskRepository.findById(1)

                            // Assert
                            task?.description shouldBe "Test Task"
                            task?.status shouldBe "TODO"
                        }
                    }
                }
            """.trimIndent(),
            executable = false
        )
    },
    fontSize = "70%"
)

object JPATestSlide : Slide(
    header = "Testing JPA Repositories",
    summary = {
        +"Testing Spring Data JPA repositories with "
        inlineCode("@DataJpaTest")
        +"."
    },
    content = {
        p {
            inlineCode("@DataJpaTest")
            +" configures an in-memory database and JPA components for testing JPA repositories."
        }
        kotlinPlayground(
            code = """
                import io.kotest.core.spec.style.FunSpec
                import io.kotest.matchers.shouldBe
                import io.kotest.matchers.shouldNotBe
                import org.springframework.beans.factory.annotation.Autowired
                import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
                import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
                import org.springframework.test.annotation.DirtiesContext

                @DataJpaTest
                @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
                class TaskJpaRepositoryTest : FunSpec() {

                    @Autowired
                    private lateinit var entityManager: TestEntityManager

                    @Autowired
                    private lateinit var taskRepository: TaskJpaRepository

                    init {
                        test("save should persist task to database") {
                            // Arrange
                            val task = TaskEntity(
                                description = "Test JPA",
                                status = TaskStatus.TODO,
                                createdBy = 1L
                            )

                            // Act
                            val saved = taskRepository.save(task)
                            entityManager.flush()
                            entityManager.clear()

                            // Assert
                            saved.id shouldNotBe null
                            val found = taskRepository.findById(saved.id!!)
                            found.isPresent shouldBe true
                            found.get().description shouldBe "Test JPA"
                        }

                        test("findByStatus should return tasks with matching status") {
                            // Arrange: Create tasks with different statuses
                            val task1 = TaskEntity(description = "Task 1", status = TaskStatus.TODO, createdBy = 1L)
                            val task2 = TaskEntity(description = "Task 2", status = TaskStatus.IN_PROGRESS, createdBy = 1L)
                            val task3 = TaskEntity(description = "Task 3", status = TaskStatus.TODO, createdBy = 1L)
                            taskRepository.saveAll(listOf(task1, task2, task3))
                            entityManager.flush()

                            // Act
                            val todoTasks = taskRepository.findByStatus(TaskStatus.TODO)

                            // Assert
                            todoTasks.size shouldBe 2
                            todoTasks.all { it.status == TaskStatus.TODO } shouldBe true
                        }
                    }
                }
            """.trimIndent(),
            executable = false
        )
    },
    fontSize = "70%"
)

object TestContainersSlide : Slide(
    header = "TestContainers",
    summary = {
        +"TestContainers runs tests against real databases in Docker containers for production-like testing."
    },
    content = {
        p {
            inlineCode("TestContainers")
            +" is a library that allows you to run your tests against real databases in isolated Docker containers."
        }
        p {
            strong { +"Benefits:" }
        }
        ul {
            li { +"Test against the actual production database (PostgreSQL, MySQL, etc.)" }
            li { +"Avoid H2 compatibility issues" }
            li { +"Automatic container lifecycle management" }
            li { +"Isolated test environment (no shared state)" }
        }
        kotlinPlayground(
            code = """
                import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
                import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
                import org.springframework.test.context.DynamicPropertyRegistry
                import org.springframework.test.context.DynamicPropertySource
                import org.testcontainers.containers.PostgreSQLContainer
                import org.testcontainers.junit.jupiter.Container
                import org.testcontainers.junit.jupiter.Testcontainers

                @DataJpaTest
                @Testcontainers
                @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
                class TaskRepositoryContainerTest : FunSpec() {

                    companion object {
                        @Container
                        val postgres = PostgreSQLContainer<Nothing>("postgres:15-alpine").apply {
                            withDatabaseName("testdb")
                            withUsername("test")
                            withPassword("test")
                        }

                        @JvmStatic
                        @DynamicPropertySource
                        fun configureProperties(registry: DynamicPropertyRegistry) {
                            registry.add("spring.datasource.url", postgres::getJdbcUrl)
                            registry.add("spring.datasource.username", postgres::getUsername)
                            registry.add("spring.datasource.password", postgres::getPassword)
                        }
                    }

                    @Autowired
                    private lateinit var taskRepository: TaskJpaRepository

                    init {
                        test("should work with real PostgreSQL") {
                            val task = TaskEntity(
                                description = "Test with real database",
                                status = TaskStatus.TODO,
                                createdBy = 1L
                            )
                            val saved = taskRepository.save(task)
                            saved.id shouldNotBe null
                        }
                    }
                }
            """.trimIndent(),
            executable = false
        )
    },
    fontSize = "65%"
)

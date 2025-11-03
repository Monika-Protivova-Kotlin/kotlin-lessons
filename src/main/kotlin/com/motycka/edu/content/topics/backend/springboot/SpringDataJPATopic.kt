package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object SpringDataJPATopic : Topic(
    title = "Spring Data JPA",
    slides = listOf(
        SpringDataJPAIntroSlide,
        JPAEntitySlide,
        JPARepositorySlide,
        JPAServiceUsageSlide
    )
)

object SpringDataJPAIntroSlide : Slide(
    header = "Using Spring Data JPA",
    summary = {
        strong { highlight("Spring Data JPA") }
        +" is implementation of the "
        strong { highlight("Java Persistence API (JPA)") }
        +"."
    },
    content = {
        p {
            +"It provides a set of interfaces and classes that make it easy to work with databases in a Spring Boot application."
        }
        p {
            +"For simple queries, you don't need to write any SQL, you just need to extend "
            inlineCode("JpaRepository")
            +" interface."
        }
        p {
            +"The interface provides several methods for "
            strong { highlight("CRUD") }
            +" operations "
            +"(like "
            inlineCode("save()")
            +", "
            inlineCode("findById()")
            +", "
            inlineCode("findAll()")
            +", etc) "
            +"that you can use right away without writing any implementation code."
        }
        p {
            +"Simplest repository definition:"
        }
        kotlinPlayground(
            """
            |interface TaskRepository : JpaRepository<TaskEntity, Long>
            """,
            executable = false
        )
        ul {
            li {
                +"Repository is defined as "
                strong { highlight("TaskRepository") }
                +" interface, extending "
                strong { highlight("JpaRepository") }
                +"."
            }
            li {
                inlineCode("TaskEntity")
                +" is a class representing data object (entity) managed by the repository."
            }
            li {
                inlineCode("Long")
                +" is the primary key type of the "
                inlineCode("TaskEntity")
                +" type."
            }
        }
    }
)

object JPAEntitySlide : Slide(
    header = "Using Spring Data JPA",
    summary = {
        +"In order for JPA to work you need to provide class annotated with "
        inlineCode("@Entity")
        +" annotation."
    },
    content = {
        p {
            +"The "
            inlineCode("TaskEntity")
            +" entity may look like this"
        }
        kotlinPlayground(
            """
            |@Entity
            |@Table(name = "tasks")
            |data class TaskEntity(
            |    @Id
            |    @GeneratedValue(strategy = GenerationType.IDENTITY)
            |    val id: Long? = null,
            |
            |    @Column(name = "description")
            |    val description: String,
            |
            |    @Column(name = "status")
            |    @Enumerated(EnumType.STRING)
            |    val status: TaskStatus,
            |
            |    @Column(name = "created_by")
            |    val createdBy: Long
            |)
            """,
            executable = false
        )
    }
)

object JPARepositorySlide : Slide(
    header = "Using Spring Data JPA",
    summary = {
        +"Spring Data JPA naming conventions and custom queries"
    },
    content = {
        p {
            +"In the example below, "
            strong { highlight("Spring Data JPA") }
            +" will automatically generate a query using "
            +"the "
            strong { highlight("findByStatus") }
            +" method name because it follows a Spring Data JPA naming convention."
        }
        kotlinPlayground(
            """
            |interface TaskRepository : JpaRepository<TaskEntity, Long> {
            |
            |    fun findByStatus(status: TaskStatus): List<TaskEntity>
            |
            |}
            """,
            executable = false
        )
        p {
            +"If you need to add more advanced queries, you can define them using the "
            inlineCode("@Query")
            +" annotation."
        }
        kotlinPlayground(
            """
            |interface TaskRepository : JpaRepository<TaskEntity, Long> {
            |
            |    @Query("SELECT t FROM TaskEntity t WHERE t.createdBy = :userId AND t.status = :status")
            |    fun findTasksByUserAndStatus(@Param("userId") userId: Long, @Param("status") status: TaskStatus): List<TaskEntity>
            |
            |}
            """,
            executable = false
        )
    }
)

object JPAServiceUsageSlide : Slide(
    header = "Using Spring Data JPA",
    summary = {
        +"Injecting the repository into the service"
    },
    content = {
        p {
            +"Using the repository from service is as simple as injecting the dependency and calling the interface methods."
        }
        kotlinPlayground(
            """
            |import org.springframework.stereotype.Service
            |
            |@Service
            |class TaskService(
            |    private val taskRepository: TaskRepository
            |) {
            |
            |    fun getTasks(): List<Task> {
            |        return taskRepository.findAll()
            |            .map { it.toDomain() }
            |    }
            |
            |    fun getTasksByStatus(status: TaskStatus): List<Task> {
            |        return taskRepository.findByStatus(status)
            |            .map { it.toDomain() }
            |    }
            |
            |}
            """,
            executable = false
        )
    }
)

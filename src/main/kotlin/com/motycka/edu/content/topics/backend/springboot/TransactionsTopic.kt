package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.*

object TransactionsTopic : Topic(
    title = "Transactions",
    slides = listOf(
        TransactionsIntroSlide,
        TransactionsExampleSlide
    )
)

object TransactionsIntroSlide : Slide(
    header = "Transactions",
    summary = {
        +"Whenever we are performing more than one database operation in a single method is that "
        +"you should use transactions."
    },
    content = {
        p {
            +"Transactions are used to ensure that all operations are executed as a single unit of work. "
            +"So in case of an error, all operations are rolled back as a whole, preventing data inconsistency."
        }
        p {
            +"To setup a transaction, use "
            inlineCode("@Transactional")
            +" annotation on the method "
            +"that performs the operations."
        }
        blockQuote {
            p {
                +"There is one caveat to using annotations in Spring though! They do not work on methods called "
                +"from within the same class. This is because Spring uses proxies to implement the annotations."
            }
            p {
                +"So annotations such as "
                inlineCode("@Transactional")
                +" will only work when the annotated methods is called from another class."
            }
        }
    }
)

object TransactionsExampleSlide : Slide(
    header = "Transactions",
    content = {
        p {
            +"Spring transactions can be tricky due to the way Spring implements them using proxies. "
            +"Proxies are used to intercept method calls and apply additional behavior, such as starting and committing a transaction, but they only work when the method is called from outside the class."
        }
        twoColumns(
            left = {
//                p {
//                    +"Here's an example to illustrate the issue:"
//                }
                p {
                    +"If we called the "
                    inlineCode("assignTasks")
                    +", the call would not be transactional, because the method annotated with "
                    inlineCode("@Transactional")
                    +" is called from within the same class."
                }
                kotlinPlayground(
                    """
            |@Service
            |class TaskService(
            |    private val taskRepository: TaskRepository,
            |    private val auditLogRepository: AuditLogRepository
            |) {
            |
            |    fun assignTasks(tasks: List<TaskEntity>, userId: Long) {
            |        tasks.forEach { task -> assignTask(task, userId) }
            |    }
            |
            |    @Transactional
            |    private fun assignTask(task: TaskEntity, userId: Long): TaskEntity {
            |        val updatedTask = taskRepository.save(task.copy(assignedTo = userId))
            |        auditLogRepository.save(AuditLog(action = "ASSIGNED", taskId = updatedTask.id, userId = userId))
            |        return updatedTask
            |    }
            |
            |}
            """,
                    executable = false,
                    erroneous = true
                )
            },
            right = {
                p {
                    +"To solve this, we can move the "
                    inlineCode("@Transactional")
                    +" annotation to the "
                    inlineCode("assignTasks")
                    +" method."
                    br
                    br
                }
                kotlinPlayground(
                    """
            |@Service
            |class TaskService(
            |    private val taskRepository: TaskRepository,
            |    private val auditLogRepository: AuditLogRepository
            |) {
            |
            |    @Transactional
            |    fun assignTasks(tasks: List<TaskEntity>, userId: Long) {
            |        tasks.forEach { task -> assignTask(task, userId) }
            |    }
            |
            |    private fun assignTask(task: TaskEntity, userId: Long): TaskEntity {
            |        val updatedTask = taskRepository.save(task.copy(assignedTo = userId))
            |        auditLogRepository.save(AuditLog(action = "ASSIGNED", taskId = updatedTask.id, userId = userId))
            |        return updatedTask
            |    }
            |
            |}
            """,
                    executable = false,
                    erroneous = false
                )
            },
        )
    }
)

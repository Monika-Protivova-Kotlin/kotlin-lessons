package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
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
        kotlinPlayground(
            """
            |@Service
            |class UserService(
            |    private val userRepository: UserRepository,
            |    private val userGroupRepository: UserGroupRepository
            |) {
            |
            |    fun addToGroup(users: List<User>, group: String) {
            |        users.forEach { user -> updateUser(user, group) }
            |    }
            |
            |    @Transactional
            |    private fun updateUser(name: User, group: String): User {
            |        val updatedUser = userRepository.save(user)
            |        val userGroup = userGroupRepository.save(UserGroup(group = group, userId = updatedUser.id))
            |        return updatedUser
            |    }
            |
            |}
            """,
            executable = false
        )
        p {
            +"If we called the "
            inlineCode("addToGroup")
            +", the call would not be transactional, because the method annotated with "
            inlineCode("@Transactional")
            +" is called from within the same class."
        }
        kotlinPlayground(
            """
            |@Service
            |class GroupManagementService(
            |    val userService: UserService,
            |    // other services
            |) {
            |
            |    fun addUsersToGroup(users: List<User>, group: String) {
            |        userService.addToGroup(users, group)
            |    }
            |}
            """,
            executable = false
        )
        p {
            +"To solve this, we can move the "
            inlineCode("@Transactional")
            +" annotation to the "
            inlineCode("addToGroup")
            +" method."
        }
    }
)

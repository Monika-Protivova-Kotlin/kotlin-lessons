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
    content = {
        p {
            +"Test the repository layer is generally a little more complicated, because it usually involves "
            +"data sources and databases, and we need to make sure that the database is in a known state before running the tests."
        }
        p {
            +"To build the correct testing context with Spring, use the "
            inlineCode("@JdbcTest")
            +" or "
            inlineCode("@DataJpaTest")
            +" annotations."
        }
        p {
            +"We might also need to import the  repository class using "
            inlineCode("@Import")
            +" annotation, "
            +"and then inject it using "
            inlineCode("@Autowired")
            +" annotation."
        }
        p {
            +"You can use "
            inlineCode("@DirtiesContext")
            +" annotation to reset the database state before each test."
        }
        blockQuote {
            +"We have a in-memory H2 database for our project, which is not a database that would be used in production. "
            +"In real world, database in tests might be provided by "
            strong(classes = "inline") { +"Docker" }
            +" using "
            strong(classes = "inline") { +"TestContainers" }
            +" library."
        }
    }
)

object JdbcTestSlide : Slide(
    header = "Testing Repositories",
    summary = {
        +"JdbcTemplate"
    },
    content = {
        p { +"The rest is a regular unit test ..." }
        kotlinPlayground(
            """
            |import org.junit.jupiter.api.Assertions
            |import org.junit.jupiter.api.DisplayName
            |import org.junit.jupiter.api.Test
            |import org.springframework.beans.factory.annotation.Autowired
            |import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
            |import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
            |import org.springframework.context.annotation.Import
            |import org.springframework.test.annotation.DirtiesContext
            |
            |@JdbcTest
            |@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
            |@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
            |@Import(UserJdbcRepository::class)
            |class UserJdbcRepositoryTest {
            |
            |    @Autowired
            |    private lateinit var jdbcTemplate: JdbcTemplate
            |
            |    @Autowired
            |    private lateinit var userRepository: UserJdbcRepository
            |
            |    @Test
            |    @DisplayName("should select all users")
            |    fun testSelectAll() {
            |        Assertions.assertEquals(UserFixtures.users, userRepository.selectAll())
            |    }
            |}
            """,
            executable = false
        )
    }
)

object JPATestSlide : Slide(
    header = "Testing Repositories",
    summary = {
        +"Spring Data JPA"
    },
    content = {
        kotlinPlayground(
            """
            |import org.junit.jupiter.api.Assertions
            |import org.junit.jupiter.api.DisplayName
            |import org.junit.jupiter.api.Test
            |import org.springframework.beans.factory.annotation.Autowired
            |import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
            |import org.springframework.test.annotation.DirtiesContext
            |
            |@DataJpaTest
            |@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
            |class UserJpaRepositoryTest {
            |
            |    @Autowired
            |    private lateinit var userRepository: UserJpaRepository
            |
            |    @Test
            |    @DisplayName("should select all users")
            |    fun testSelectAll() {
            |        val users = userRepository.findAll()
            |
            |        for (i in UserFixtures.users.indices) {
            |            val expected = UserFixtures.users[i]
            |            val actual = users[i]
            |            Assertions.assertEquals(expected.id, actual.id)
            |            Assertions.assertEquals(expected.name, actual.name)
            |        }
            |    }
            |}
            """,
            executable = false
        )
    }
)

object TestContainersSlide : Slide(
    header = "TestContainers",
    content = {
        p {
            inlineCode("TestContainers")
            +" is a Java library that allows you to run your tests in isolated Docker containers."
        }
        p {
            +"It will start a Docker container with the database before running the tests, and stop it after the tests are done, ensuring that the database is in a known state before each test."
        }
    }
)

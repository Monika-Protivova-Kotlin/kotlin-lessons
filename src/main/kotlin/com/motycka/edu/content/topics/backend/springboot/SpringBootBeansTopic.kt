package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.*

object SpringBootBeansTopic : Topic(
    title = "Beans & Dependency Injection",
    slides = listOf(
        BeanSlide,
        ControllerBeanSlide,
        ServiceBeanSlide,
        RepositoryBeanSlide
    )
)

object BeanSlide : Slide(
    header = "Bean",
    summary = {
        strong { highlight("Bean") }
        +" is an object that is managed by the Spring IoC container. By defining a bean, "
        +"we are telling the Spring container to create an instance of the bean and manage its lifecycle."
    },
    content = {
        p {
            +"To define a bean, you simply need to annotate a method with the "
            inlineCode("@Bean")
            +" annotation."
        }
        div(classes = "row") {
            +"User Bean defined in UserComponent. "
            +"The bean is then available for dependency injection in other parts of the application."
            kotlinPlayground(
                """
                |@Component // or @Configuration
                |class UserComponent {
                |
                |    @Bean
                |    fun user(): User {
                |        return User("Monika")
                |    }
                |
                |}
                """,
                executable = false
            )
        }
        div(classes = "row") {
            twoColumns(
                left = {
                    +"Injection by constructor (preferred)"
                    kotlinPlayground(
                        """
                        |@Component
                        |class HigherLevelComponent(
                        |    private val user: User
                        |) {
                        |
                        |    fun doSomething() {
                        |        // do something with user
                        |    }
                        |
                        |}
                        """,
                        executable = false
                    )
                },
                right = {
                    +"Injection by "
                    inlineCode("@Autowired")
                    +" annotation"
                    kotlinPlayground(
                        """
                        |@Component
                        |class HigherLevelComponent {
                        |
                        |    @Autowired
                        |    private lateinit var user: User
                        |
                        |    fun doSomething() {
                        |        // do something with user
                        |    }
                        |
                        |}
                        """,
                        executable = false
                    )
                }
            )
        }
    }
)

object ControllerBeanSlide : Slide(
    header = "Controller",
    summary = {
        +"Controllers "
        strong { highlight("beans") }
        +" that are conventionally responsible for handling requests and returning the responses, for example, RESTful services."
    },
    content = {
        p {
            +"Annotating a class with "
            inlineCode("@Controller")
            +" or "
            inlineCode("@RestController")
            +" makes it a controller "
            +"that handles the HTTP requests and returns the HTTP responses."
        }
        p {
            +"In this example, the "
            inlineCode("HelloController")
            +" class handles the HTTP GET request to the "
            inlineCode("/hello")
            +" endpoint and returns the string \"Hello World!\"."
        }
        kotlinPlayground(
            """
            |import org.springframework.web.bind.annotation.RequestMapping
            |import org.springframework.web.bind.annotation.RequestParam
            |import org.springframework.web.bind.annotation.RestController
            |
            |@RestController
            |@RequestMapping("/api")
            |class HelloController(
            |    private val helloService: HelloService
            |) {
            |
            |    @RequestMapping("/hello")
            |    fun getHelloWorld(
            |        @RequestParam(value = "name") name: String,
            |        @RequestParam(value = "locale") locale: String
            |    ): String {
            |        return helloService.sayHello(name, locale)
            |    }
            |
            |}
            """,
            executable = false
        )
        p {
            inlineCode("@RestController")
            +" is actually just a combination of "
            inlineCode("@Controller")
            +" and "
            inlineCode("@ResponseBody")
            +" annotations."
        }
    }
)

object ServiceBeanSlide : Slide(
    header = "Service",
    summary = {
        +"Services are beans that contain the business logic of the application."
    },
    content = {
        p {
            +"Annotating a class with "
            inlineCode("@Service")
            +" makes it an injectable bean."
        }
        kotlinPlayground(
            """
            |import org.springframework.stereotype.Service
            |
            |@Service
            |class HelloService(
            |    private val helloRepository: HelloRepository
            |) {
            |
            |    fun sayHello(name: String, locale: String): String {
            |        val hello = helloRepository.selectHelloInLanguage(locale)
            |        return String.format("%s %s!", hello, name)
            |    }
            |
            |}
            """,
            executable = false
        )
    }
)

object RepositoryBeanSlide : Slide(
    header = "Repository",
    summary = {
        +"The repository layer should contain the data access logic."
    },
    content = {
        p {
            +"Annotation a class with "
            inlineCode("@Repository")
            +" makes it an injectable bean."
        }
        kotlinPlayground(
            """
            |import org.springframework.jdbc.core.JdbcTemplate
            |import org.springframework.stereotype.Repository
            |
            |@Repository
            |class HelloRepository(
            |    private val jdbcTemplate: JdbcTemplate
            |) {
            |
            |    fun selectHelloInLanguage(locale: String): String {
            |        return jdbcTemplate.queryForObject(
            |            "SELECT message_value FROM i18n WHERE locale = ? AND message_key = 'hello' LIMIT 1",
            |            String::class.java,
            |            arrayOf(locale),
            |        )
            |    }
            |
            |}
            """,
            executable = false
        )
    }
)

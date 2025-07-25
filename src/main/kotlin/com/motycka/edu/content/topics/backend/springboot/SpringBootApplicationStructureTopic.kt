package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object SpringBootApplicationStructureTopic : Topic(
    title = "Spring Boot Application",
    slides = listOf(
        SpringBootApplicationSlide,
        SpringBootApplicationMainClassSlide
    )
)

object SpringBootApplicationSlide : Slide(
    header = "Spring Boot Application",
    summary = {
        +"Spring Boot application consists of a main class and a set of beans. "
        +"It is independently deployable, and it can be run as a standalone application."
    },
    content = {
        p {
            +"Spring Boot is "
            strong { highlight("annotation-driven") }
            +", which means annotations are used to manage configurations and define so-called "
            strong { highlight("beans") }
            +"."
        }
        p {
            strong { highlight("Bean") }
            +" is an object that is managed by the Spring IoC container."
        }
        p {
            strong { highlight("Controller") }
            +", "
            strong { highlight("Service") }
            +" and "
            strong { highlight("Repository") }
            +" are actually all different types of beans."
        }
    }
)

object SpringBootApplicationMainClassSlide : Slide(
    header = "Spring Boot Application",
    content = {
        p {
            +"A basic Spring Boot application main class will look like this"
        }
        kotlinPlayground(
            """
            |import org.springframework.boot.autoconfigure.SpringBootApplication
            |import org.springframework.boot.runApplication
            |
            |@SpringBootApplication
            |class HelloApplication
            |
            |fun main(args: Array<String>) {
            |    runApplication<HelloApplication>(*args)
            |}
            """,
            executable = false
        )
        p {
            +"The "
            inlineCode("@SpringBootApplication")
            +" annotation is used to mark the "
            inlineCode("class Application")
            +" as the main class of the Spring Boot application."
        }
        p {
            +"The main method is used to start the application by calling the "
            inlineCode("runApplication")
            +" method with the main class and argument."
        }
        p {
            +"The application class may optionally contain other annotations, such as "
            inlineCode("@ComponentScan")
            +", "
            inlineCode("@EnableAutoConfiguration")
            +", and "
            inlineCode("@Configuration")
            +", which are used to further configure the application."
        }
    }
)

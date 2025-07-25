package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import kotlinx.html.*

object MVCPatternTopic : Topic(
    title = "Model-View-Controller",
    slides = listOf(
        MVCSlide,
        SpringMVCSlide
    )
)

object MVCSlide : Slide(
    header = "Model-View-Controller (MVC)",
    summary = {
        +"The Model-View-Controller (MVC) is a design pattern that separates an application into three main components:"
    },
    content = {
        ul {
            li {
                strong { highlight("Model") }
                br()
                +"represents the data and the business logic of the application"
            }
            li {
                strong { highlight("View") }
                br()
                +"represents the user interface"
            }
            li {
                strong { highlight("Controller") }
                br()
                +"handles the user input and updates the model and view"
            }
        }
    }
)

object SpringMVCSlide : Slide(
    header = "Spring MVC",
    summary = {
        +"Spring Boot follows the MVC pattern, but implementation may be slightly different from the traditional "
        +"MVC frameworks because of its nature as a web application framework."
    },
    content = {
        p {
            +"For example, for RESTful applications written in Spring Boot, "
            +"the controller is responsible for handling the HTTP requests and returning the HTTP responses. "
            +"There is no user interface, so the view is often represented as a JSON or XML document."
        }
        p {
            +"Spring Boot applications implementing RESTful services usually have this architecture:"
        }
        ul {
            li {
                strong { highlight("Controller") }
                br()
                +"handles the HTTP requests and returns the HTTP responses"
            }
            li {
                strong { highlight("Service") }
                br()
                +"contains the business logic"
            }
            li {
                strong { highlight("Repository") }
                br()
                +"contains the data access logic"
            }
        }
    }
)

package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import kotlinx.html.*

object ServiceDesignPatternsTopic : Topic(
    title = "Service Design Patterns",
    slides = listOf(
        ServiceArchitectureSlide
    )
)

object ServiceArchitectureSlide : Slide(
    header = "Service Architecture",
    summary = {
        +"When designing your application, it is important to consider the architecture of your services."
    },
    content = {
        h4 { +"Separation of concerns" }
        p {
            +"It is a good practice to split your code by domain, where each service is responsible for a specific domain or business logic. "
            +"If you domains need to interact with each other, be careful to not create cross dependencies that would break the domain separation."
        }
        p {
            +"Well designed dependencies might look like this:"
        }
        ul {
            li {
                strong { highlight("Controller -> Service") }
                +" within the same domain"
            }
            li {
                strong { highlight("Service -> Repository") }
                +" within the same domain"
            }
            li {
                strong { highlight("Service -> Service") }
                +" within the same domain"
            }
            li {
                strong { highlight("Service -> Service") }
                +" across a domain - you may want a dedicated cross domain service"
            }
            li {
                strong { highlight("Repository -> Repository") }
                +" within the same domain"
            }
        }
        p {
            +"A rule of thumb here is, when you can take your domain and spilt it into separate services with minimal effort, they you probably have a good design."
        }
        p {
            strong {
                +"A good approach to this is to use interfaces where services are defined and implemented in separate modules."
            }
        }
        h4 { +"Internal and external data models" }
        p {
            +"It is good idea to have separate classes representing the internal and external data models. "
            +"For example, you might have a "
            inlineCode("User")
            +" class that is used internally by your service and a "
            inlineCode("NewUserRequest")
            +" and "
            inlineCode("UserResponse")
            +" class that is used to represent the user in the API. "
            +"The internal object may have some properties logic and content that you don't want to expose externally. "
            +"Also, you do not want changes made to the internal object to affect the external object contract."
        }
    },
    fontSize = "70%"
)

package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object SpringBootConfigurationTopic : Topic(
    title = "Configuration",
    slides = listOf(
        ConfigurationSlide
    )
)

object ConfigurationSlide : Slide(
    header = "Configuration",
    summary = {
        +"Configuration classes contain the configuration logic."
    },
    content = {
        p {
            +"Annotation a class with "
            inlineCode("@Configuration")
            +" makes it a configuration class that contains the configuration logic."
        }
        kotlinPlayground(
            """
            |import org.springframework.context.annotation.Bean
            |import org.springframework.context.annotation.Configuration
            |import org.springframework.web.client.RestTemplate
            |
            |@Configuration
            |class HelloConfiguration {
            |
            |    @Bean
            |    fun restTemplate(): RestTemplate {
            |        return RestTemplate()
            |    }
            |
            |    @Bean
            |    fun myBean(): MyBean {
            |        // ... any custom configuration
            |    }
            |
            |}
            """,
            executable = false
        )
    }
)

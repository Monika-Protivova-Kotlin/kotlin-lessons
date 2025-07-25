package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.*

object ReactiveStreamsTopic : Topic(
    title = "Reactive Streams",
    slides = listOf(
        SpringWebFluxIntroSlide,
        SpringWebFluxComparisonSlide,
        SpringWebFluxExampleSlide,
        KotlinCoroutinesWithWebFluxSlide
    )
)

object SpringWebFluxIntroSlide : Slide(
    header = "Spring WebFlux",
    summary = {
        +"Spring WebFlux and Spring MVC are both part of the Spring Framework and are used to build web applications, "
        +"but they have different architectures and are suited for different types of workloads."
    },
    content = {}
)

object SpringWebFluxComparisonSlide : Slide(
    header = "Spring WebFlux vs Spring MVC",
    content = {
        twoColumns(
            left = {
                h4 { +"Spring MVC" }
            },
            right = {
                h4 { +"Spring WebFlux" }
            }
        )
        twoColumns(
            left = {
                p { strong { highlight("Programming Model") } }
            },
            right = {}
        )
        twoColumns(
            left = {
                ul {
                    li {
                        +"Follows the traditional blocking (synchronous) model where each request is processed by a dedicated thread."
                    }
                    li {
                        +"Uses the Servlet API, threads wait for I/O (e.g., database calls, external API responses)."
                    }
                }
            },
            right = {
                ul {
                    li {
                        +"Follows the non-blocking (reactive) programming model to process requests asynchronously,"
                    }
                    li { +"Users event-driven, non-blocking I/O to process requests without tying up threads." }
                    li { +"Based on Reactor and Netty." }
                }
            }
        )
        twoColumns(
            left = {
                p { strong { highlight("Return Types") } }
            },
            right = {}
        )
        twoColumns(
            left = {
                ul {
                    li {
                        +"Returns a  "
                        inlineCode("ModelAndView")
                        +", or data types like "
                        inlineCode("String")
                        +", "
                        inlineCode("Long")
                        +", ... or "
                        inlineCode("ResponseEntity")
                        +"."
                    }
                    li {
                        +"Suitable for thread-per-request handling, but it can lead to thread exhaustion under high loads."
                    }
                }
            },
            right = {
                ul {
                    li {
                        +"Often returns "
                        inlineCode("Mono<T>")
                        +" (for zero or one item) or "
                        inlineCode("Flux<T>")
                        +" (for multiple items) to represent asynchronous, reactive streams."
                    }
                    li {
                        +"Allows handling more concurrent requests with fewer threads by avoiding thread blocking."
                    }
                }
            }
        )
        twoColumns(
            left = {
                p { strong { highlight("Application Scenarios") } }
            },
            right = {}
        )
        twoColumns(
            left = {
                ul {
                    li {
                        +"Traditional web applications (e.g., form-based apps, content-heavy sites)."
                    }
                    li {
                        +"Easy to use for developers familiar with blocking I/O and imperative programming."
                    }
                }
            },
            right = {
                ul {
                    li {
                        +"Ideal for microservices, event-driven systems, or streaming data scenarios (e.g., WebSockets, real-time applications)."
                    }
                    li {
                        +"Great for APIs or services that require high throughput and low latency."
                    }
                }
            }
        )
    },
    fontSize = "70%"
)

object SpringWebFluxExampleSlide : Slide(
    header = "Spring WebFlux",
    summary = {
        +"Using Reactor Flux and Mono types"
    },
    content = {
        p {
            +"Controllers implemented using Spring "
            strong { highlight("WebFlux") }
            +" can return "
            inlineCode("Flux<T>")
            +" and "
            inlineCode("Mono<T>")
            +" types to represent asynchronous, reactive streams."
        }
        kotlinPlayground(
            """
            |@RestController
            |@RequestMapping("/api/v1/hello")
            |class HelloControllerV1(
            |    private val helloService: HelloService
            |) {
            |
            |
            |    @GetMapping
            |    fun getHello(): Flux<Hello> = Flux.fromIterable(helloService.getHello())
            |
            |    @GetMapping("/{locale}")
            |    fun getHello(
            |        @PathVariable(value = "locale") locale: String,
            |        @RequestParam(value = "name") name: String
            |    ): Mono<String> = Mono.just(helloService.sayHello(name, locale))
            |
            |}
            """,
            executable = false
        )
        kotlinPlayground(
            """
            |@Service
            |class HelloService(
            |    private val helloRepository: HelloRepository
            |) {
            |
            |    fun getHello(): List<Hello> = helloRepository.getHello()
            |
            |    fun sayHello(name: String, locale: String): String = helloRepository.sayHello(name, locale)
            |
            |}
            """,
            executable = false
        )
    }
)

object KotlinCoroutinesWithWebFluxSlide : Slide(
    header = "Spring WebFlux with Kotlin Coroutines",
    summary = {
        +"Using Kotlin coroutines with Flow types"
    },
    content = {
        p {
            +"There is an extension for Spring WebFlux that allows you to use Kotlin coroutines to write asynchronous, non-blocking code."
        }
        kotlinPlayground(
            """
            |@RestController
            |@RequestMapping("/api/v2/hello")
            |class HelloControllerV2(
            |    private val helloService: HelloService
            |) {
            |
            |    @GetMapping
            |    suspend fun getHello(): Flow<List<Hello>> = helloService.getHelloAsync()
            |
            |    @GetMapping("/{locale}")
            |    suspend fun getHello(
            |        @PathVariable(value = "locale") locale: String,
            |        @RequestParam(value = "name") name: String
            |    ): Flow<String> = helloService.sayHelloAsync(name, locale)
            |
            |}
            """,
            executable = false
        )
        kotlinPlayground(
            """
            |@Service
            |class HelloService(
            |    private val helloRepository: HelloRepository
            |) {
            |
            |    suspend fun getHelloAsync(): Flow<List<Hello>> = flow {
            |        emit(helloRepository.getHello())
            |    }
            |
            |    suspend fun sayHelloAsync(name: String, locale: String): Flow<String> = flow {
            |        emit(helloRepository.sayHello(name, locale))
            |    }
            |
            |}
            """,
            executable = false
        )
    }
)

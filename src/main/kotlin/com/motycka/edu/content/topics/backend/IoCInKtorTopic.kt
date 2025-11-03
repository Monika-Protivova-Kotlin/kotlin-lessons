package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import com.motycka.edu.model.highlight
import kotlinx.html.*
import com.motycka.edu.model.highlight

object IoCInKtorTopic : Topic(
    title = "IoC in Ktor",
    slides = listOf(
        IoCInKtorIntroSlide,
        IoCWithoutFrameworkSlide,
        IoCWithKoinSlide,
        KoinImplementationSlide
    )
)

object IoCInKtorIntroSlide : Slide(
    header = "IoC in Ktor - What do we have so far?",
    summary = {
        +"So far, we have been using the "
        strong { +"IoC" }
        +" principle in our Ktor application with the "
        strong { +"constructor injection" }
        +" in application main."
    },
    content = {
        kotlinPlayground(
            code = """
                fun main() {

                    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {

                        // ...

                        val menuRepository = MenuRepositoryImpl()
                        val menuService = MenuService(menuRepository = menuRepository)
                        val jwtGenerator = JwtService(config = applicationConfig)
                        val userRepository = UserRepositoryImpl()
                        val authenticationService = AuthenticationService(
                            userRepository = userRepository,
                            internalCustomerService = InternalCustomerService(customerRepository = CustomerRepositoryImpl()),
                            jwtService = jwtGenerator
                        )

                        // ...

                        install(Authentication) {
                            configureJWT(applicationConfig)
                        }

                        routing {
                            loginRoutes(authenticationService, API_PATH)

                            authenticate(AUTH_JWT) {
                                menuRoutes(menuService, API_PATH)
                                orderRoutes(orderService, API_PATH)
                            }
                        }
                    }.start(wait = true)
                }
            """.trimIndent(),
            executable = true
        )
    }
)

object IoCWithoutFrameworkSlide : Slide(
    header = "Limitations of Manual Dependency Injection",
    summary = {
        +"While dependency injection using constructor is a good start, it is not very practical for larger applications."
    },
    content = {
        p {
            +"Problems with manual dependency injection:"
        }
        ul {
            li {
                strong { +"Boilerplate Code" }
                +" - Lots of manual wiring in the main function"
            }
            li {
                strong { +"Dependency Order" }
                +" - Must create dependencies in the correct order"
            }
            li {
                strong { +"Complexity Growth" }
                +" - Becomes unmanageable as the application grows"
            }
            li {
                strong { +"Testing Difficulty" }
                +" - Hard to swap implementations for testing"
            }
            li {
                strong { +"Singleton Management" }
                +" - Manual management of object lifecycles"
            }
        }
        p {
            +"For larger applications, we can use a dependency injection framework, "
            +"such as "
            strong { +"Koin" }
            +" or "
            strong { +"Dagger" }
            +"."
        }
        p {
            +"Dependency injection framework allows us to define dependencies by their type, "
            +"and the framework will take care of creating and injecting them into the classes that need them."
        }
        p {
            +"Ktor framework has built-in support for dependency injection using "
            strong { +"Koin" }
            +"."
        }
    }
)

object IoCWithKoinSlide : Slide(
    header = "Using Dependency Injection Framework",
    summary = {
        +"Ktor with Koin provides a cleaner approach to dependency management."
    },
    content = {
        twoColumns(
            left = {
                p {
                    +"We use Koin module declaration to define our dependencies:"
                }
                kotlinPlayground(
                    code = """
                        fun appModule(config: ApplicationConfig) = module {
                            single { config }
                            singleOf(::MenuRepositoryImpl) { bind<MenuRepository>() }
                            singleOf(::MenuService)
                            singleOf(::JwtService)
                            singleOf(::AuthenticationService)
                        }
                    """.trimIndent(),
                    executable = false
                )
                p {
                    +"Start Koin in the "
                    inlineCode("main")
                    +" function and inject the dependencies into the Ktor application:"
                }
                kotlinPlayground(
                    code = """
                        fun main() {
                            val applicationConfig = ApplicationConfig("application.yaml")

                            embeddedServer(Netty, port = 8080, host = "0.0.0.0") {

                                startKoin {
                                    modules(
                                        appModule(applicationConfig)
                                    )
                                }

                                // ...

                                routing {
                                    loginRoutes(API_PATH)

                                    authenticate(AUTH_JWT) {
                                        menuRoutes(API_PATH)
                                    }
                                }
                            }.start(wait = true)
                        }
                    """.trimIndent(),
                    executable = true
                )
            },
            right = {
                p {
                    +"All that remains is to inject the dependencies into the routes:"
                }
                kotlinPlayground(
                    code = """
                        fun Route.menuRoutes(basePath: String) {

                            val menuService by inject<MenuService>()

                            route(basePath) {
                                // ...
                            }
                        }
                    """.trimIndent(),
                    executable = false
                )
                p {
                    +"We can use the same mechanism to inject the dependencies into the services. "
                    +"However, it is actually quite practical to do it through the constructor, "
                    +"as it makes the services easier to test (no need to instantiate Koin in tests)."
                }
                kotlinPlayground(
                    code = """
                        class MenuService(
                            private val menuRepository: MenuRepository
                        ) {
                            // ...
                        }
                    """.trimIndent(),
                    executable = false
                )
            }
        )
    }
)

object KoinImplementationSlide : Slide(
    header = "Benefits of Using Koin with Ktor",
    summary = {
        +"Koin provides several advantages for Ktor applications compared to manual dependency injection."
    },
    content = {
        p {
            highlight("Benefits of Koin integration:")
        }
        ul {
            li {
                strong { +"Declarative Configuration" }
                +" - Define all dependencies in one place using DSL"
            }
            li {
                strong { +"Automatic Resolution" }
                +" - Framework handles dependency creation and injection"
            }
            li {
                strong { +"Lifecycle Management" }
                +" - Control object lifecycles (singleton, factory, scoped)"
            }
            li {
                strong { +"Easy Testing" }
                +" - Simple to override modules for testing"
            }
            li {
                strong { +"Type Safety" }
                +" - Compile-time checking of dependency types"
            }
            li {
                strong { +"Lazy Loading" }
                +" - Dependencies created only when needed"
            }
        }
        p {
            highlight("Koin module types:")
        }
        ul {
            li {
                inlineCode("single { }")
                +" - Creates a singleton instance"
            }
            li {
                inlineCode("factory { }")
                +" - Creates a new instance each time"
            }
            li {
                inlineCode("singleOf(::Class)")
                +" - Shorthand for singleton with constructor injection"
            }
            li {
                inlineCode("bind<Interface>()")
                +" - Binds implementation to interface"
            }
        }
        p {
            em { +"This approach scales much better for larger applications and promotes cleaner architecture." }
        }
    }
)

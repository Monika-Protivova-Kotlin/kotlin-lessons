package com.motycka.edu.content.topics.testing

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.highlight
import kotlinx.html.*
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.highlight

object MockingTopic : Topic(
    title = "Mocking",
    slides = listOf(
        MockingIntroSlide,
        MockKUsageSlide,
        MockingBestPracticesSlide
    )
)

object MockingIntroSlide : Slide(
    header = "Mocking",
    summary = {
        +"Mocking is a technique used in unit testing to create a fake implementation of a class or interface, "
        +"which can be used to simulate the behavior of the real implementation."
    },
    content = {
        p {
            +"This allows us to isolate the unit being tested from its dependencies, "
            +"so we can focus on testing the unit's behavior without worrying about the behavior of its dependencies."
        }
        p {
            +"In Kotlin, we can use libraries like "
            strong { highlight("MockK") }
            +" or "
            strong { highlight("Mockito") }
            +" to create mocks and stubs for our tests."
        }
        p {
            +"Key benefits of mocking:"
        }
        ul {
            li {
                strong { +"Isolation" }
                +" - Test units in complete isolation from dependencies"
            }
            li {
                strong { +"Control" }
                +" - Full control over dependency behavior"
            }
            li {
                strong { +"Speed" }
                +" - Eliminate slow operations (database, network calls)"
            }
            li {
                strong { +"Reliability" }
                +" - Remove external factors that could cause test failures"
            }
        }
    }
)

object MockKUsageSlide : Slide(
    header = "Using MockK",
    summary = {
        +"MockK is a powerful mocking library for Kotlin that provides a simple and intuitive API for creating mocks and verifying their behavior."
    },
    content = {
        p {
            +"MockK is a powerful mocking library for Kotlin that allows you to create mocks, stubs, and spies for your tests. "
            +"It provides a simple and intuitive API for creating mocks and verifying their behavior."
        }
        p {
            +"We can mock any class or interface, and define the behavior of the mocked object."
        }
        ul {
            li {
                +"The mocked object is defined using "
                code { +"mockk()" }
                +" function."
            }
            li {
                +"We can define the behavior of the mocked object using "
                code { +"every { ... } returns ..." }
                +" syntax."
            }
            li {
                +"We can verify that the mocked object was called with the expected arguments using "
                code { +"verify { ... }" }
                +" syntax."
            }
        }
        kotlinPlayground(
            code = """
                // create the mocked object
                val myService: MyService = mockk() // or val myService = mockk<MyService>()

                // define the behavior of the mocked object
                every { myService.doSomething(any()) } returns "Mocked Result"

                // use the mocked object in your test
                val result = myService.doSomething("Test Input")

                // verify that the mocked object was called with the expected arguments
                verify(exactly = 1) { myService.doSomething("Test Input") }
            """.trimIndent(),
            executable = false
        )
        ul {
            li {
                +"We can also spy on an existing object, which allows us to verify its behavior without modifying the original object."
            }
        }
        kotlinPlayground(
            code = """
                val myRealObject = spyk(MyRealObject())
                verify { myRealObject.someMethod("Was called with an argument") }
            """.trimIndent(),
            executable = false
        )
    }
)

object MockingBestPracticesSlide : Slide(
    header = "Mocking Best Practices",
    summary = {
        +"Guidelines for effective mocking in your tests."
    },
    content = {
        p {
            highlight("Best practices for mocking:")
        }
        ul {
            li {
                strong { +"Mock External Dependencies Only" }
                +" - Don't mock the class under test"
            }
            li {
                strong { +"Use Interfaces" }
                +" - Mock interfaces rather than concrete classes when possible"
            }
            li {
                strong { +"Verify Interactions" }
                +" - Use verify() to ensure expected method calls"
            }
            li {
                strong { +"Clear Mock State" }
                +" - Reset mocks between tests using clearAllMocks()"
            }
            li {
                strong { +"Meaningful Assertions" }
                +" - Verify both return values and method invocations"
            }
        }
        p {
            highlight("What NOT to mock:")
        }
        ul {
            li { +"Simple data classes or value objects" }
            li { +"The class being tested" }
            li { +"Standard library classes" }
            li { +"Complex objects that are easy to create" }
        }
        p {
            highlight("When to use spies vs mocks:")
        }
        ul {
            li {
                strong { +"Mocks" }
                +" - When you want complete control over behavior"
            }
            li {
                strong { +"Spies" }
                +" - When you want to partially mock an existing object"
            }
        }
    }
)

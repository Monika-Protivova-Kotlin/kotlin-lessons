package com.motycka.edu.content.topics.testing

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode

object JUnitTopic : Topic(
    title = "JUnit",
    slides = listOf(
        JUnitIntroSlide,
        JUnitTestSlide,
        JUnitOverviewSlide,
        JUnitCleanTestsSlide,
        JUnitGoodTestsSlide,
        JUnitDescriptiveTestsSlide,
        JUnitDescriptiveAssertionsSlide,
        JUnitTestLifecycleSlide,
        JUnitLifecycleSlide
    )
)

object JUnitIntroSlide : Slide(
    header = "JUnit",
    summary = {
        strong { +"JUnit" }
        +" of the most commonly used testing frameworks for Java and also for Kotlin."
    },
    content = {
        p {
            +"Unit testing framework is a set of tools that provides features to:"
        }
        ul {
            li { +"Write test cases" }
            li { +"Executed test cases" }
            li { +"Evaluate test results" }
        }
        p {
            +"There are other of unit testing frameworks available in Kotlin, such as "
            strong { +"Kotest" }
        }
        p {
            +"The concepts that we will learn with JUnit generally apply to all unit testing frameworks."
        }
    }
)

object JUnitTestSlide : Slide(
    header = "JUnit Test",
    content = {
        ul {
            li { +"JUnit test, generally, is a class that contains one or more test methods." }
            li { +"Each test method is a method annotated with "
                inlineCode("@Test")
                +" annotation."
            }
            li {
                +"Besides being annotated with "
                inlineCode("@Test")
                +" annotation, in order for a method considered a test, "
                strong { +"it must contain at least one assertion" }
                +"."
            }
            li { +"By convention, test classes are usually suffixed with "
                inlineCode("Test")
            }
            li {
                +"While program sources are placed in "
                inlineCode("/src/main/kotlin")
                +" directory, "
                +"test sources are conventionally placed in "
                inlineCode("/src/test/kotlin")
                +" directory."
            }
            li {
                +"It is important, that the test code is as readable as possible. "
                +"Therefore, good naming conventions are essential."
            }
        }
    }
)

object JUnitOverviewSlide : Slide(
    header = "JUnit Test",
    summary = {
        +"JUnit tests are just regular Java classes, where methods annotated with "
        inlineCode("@Test")
        +" are treated little different from regular methods by the testing framework."
    },
    content = {
        p {
            +"Assertions are just regular method calls, where the testing framework evaluates the result of the assertion. "
            +"In JUnit, we can use "
            inlineCode("Assertions")
            +" class to make assertions."
        }
        kotlinPlayground(
            code = """
                package lesson03

                import org.junit.jupiter.api.Assertions
                import org.junit.jupiter.api.DisplayName
                import org.junit.jupiter.api.Test

                // By convention the name of the test class should be the name of the class under test + "Test"
                class TemperatureConverterTest {

                    @Test
                    @DisplayName("should convert Celsius to Fahrenheit - 0C = 32F")
                    fun testConvertCelsiusToFahrenheit() {
                        Assertions.assertEquals(32.0, TemperatureConverter.toFahrenheit(0.0));
                    }

                    // different style of test name
                    @Test
                    fun `should convert Fahrenheit to Celsius - 32F = 0C`() {
                        Assertions.assertEquals(0.0, TemperatureConverter.toCelsius(32.0));
                    }
                }
            """.trimIndent(),
            executable = false
        )
    }
)

object JUnitCleanTestsSlide : Slide(
    header = "Clean tests <=> clean code",
    content = {
        div("content content-center content-100") {
            h4 { +"Writing testable code matters!" }
            +"I can say through my own experience, that the more testable the code unit is, the better it usually is. "
            +"This is because testability is an indicator of good design and therefore indicator of "
            strong { +"internal quality" }
            +"."
            br()
            br()
            h4 { +"Writing clean tests matters!" }
            +"During real-world development, you will often be dealing with code you didn't write yourself. "
            +"You will come to appreciate well written tests, because they will help you understand the code you are working with."
            br()
            br()
            +"Same goes also in the other direction, your colleagues will appreciate good tests you write, because they will help them understand your code."
        }
    }
)

object JUnitGoodTestsSlide : Slide(
    header = "Good tests",
    summary = {
        +"Writing reliable and maintainable tests"
    },
    content = {
        p {
            +"The value of tests is that they give us feedback during development. "
            +"There are few rules that help us make sure that the feedback we get from tests is accurate and reliable."
        }
        h4 { +"Test should be:" }
        ul {
            li {
                strong { +"Deterministic" }
                +" - each test run should yield the same result."
            }
            li {
                strong { +"Easy to understand" }
                +" - this will help with interpreting results and maintenance."
            }
            li {
                strong { +"Fast" }
                +" - we want fast feedback loop."
            }
            li {
                strong { +"Independent" }
                +" - each test should be able to run in isolation and in any order."
            }
            li {
                strong { +"Repeatable" }
                +" - each test should be able to run multiple times."
            }
            li {
                strong { +"Focused" }
                +" - each test should focus on testing one thing only."
            }
        }
    }
)

object JUnitDescriptiveTestsSlide : Slide(
    header = "Descriptive tests",
    summary = {
        +"One of the ways you can make your test code easier to understand is using descriptive names and well-designed assertions."
    },
    content = {
        p {
            +"With "
            strong { +"JUnit" }
            +", we can use "
            inlineCode("@DisplayName")
            +" annotation to give our tests descriptive name. "
            +"Notice that the test names express state of the SUT, but also what we expect from the SUT."
        }
        kotlinPlayground(
            code = """
                @Test
                @DisplayName("should convert Celsius to Fahrenheit - 0C = 32F")
                fun testConvertCelsiusToFahrenheit() {
                    Assertions.assertEquals(32.0, TemperatureConverter.toFahrenheit(0.0));
                }
            """.trimIndent(),
            executable = false
        )
        p {
            +"Another option is to use Kotlin's string templates to make test names more descriptive."
        }
        kotlinPlayground(
            code = """
                @Test
                fun `should convert Fahrenheit to Celsius - 32F = 0C`() {
                    Assertions.assertEquals(0.0, TemperatureConverter.toCelsius(32.0));
                }
            """.trimIndent(),
            executable = false
        )
    }
)

object JUnitDescriptiveAssertionsSlide : Slide(
    header = "Descriptive assertions",
    summary = {
        +"Another important aspect of testing is understanding test results."
    },
    content = {
        p {
            +"To make understanding test results easier, we can use "
            inlineCode("Assertions")
            +" class methods in a way that when they fail, the output is as descriptive of the problem as possible."
        }
        p {
            +"For example, these two assertions would both work, the first one provides much clearer information "
            +"when it fails."
        }
        strong { +"Example 1" }
        kotlinPlayground(
            code = """
                @Test
                fun testConvertCelsiusToFahrenheit() {
                    Assertions.assertEquals(32.1, TemperatureConverter.toFahrenheit(0.0));
                }
            """.trimIndent(),
            executable = false
        )
        kotlinPlayground(
            code = """
                Expected :32.1
                Actual   :32.0
            """.trimIndent(),
            executable = false
        )
        strong { +"Example 2" }
        kotlinPlayground(
            code = """
                @Test
                fun testConvertCelsiusToFahrenheit() {
                    Assertions.assertTrue(32.1 == TemperatureConverter.toFahrenheit(0.0));
                }
            """.trimIndent(),
            executable = false
        )
        kotlinPlayground(
            code = """
                Expected :true
                Actual   :false
            """.trimIndent(),
            executable = false
        )
    }
)

object JUnitTestLifecycleSlide : Slide(
    header = "Test Lifecycle",
    summary = {
        +"Test lifecycle is the sequence of events that happen during the execution of a test."
    },
    content = {
        p {
            +"Several annotations can be used to control the test lifecycle. "
            +"These annotations are: "
            inlineCode("@BeforeAll")
            +", "
            inlineCode("@BeforeEach")
            +", "
            inlineCode("@AfterEach")
            +" and "
            inlineCode("@AfterAll")
        }
        p {
            strong { +"When JUnit test case is started:" }
        }
        ol {
            li { +"JUnit Test class gets loaded and scanned for "
                inlineCode("@Test")
                +" annotation marking individual test cases"
            }
            li {
                inlineCode("@BeforeAll")
                +" - is run before all "
                inlineCode("@Test")
                +" methods"
            }
            li {
                inlineCode("@BeforeEach")
                +" - is run before each "
                inlineCode("@Test")
                +" methods"
            }
            li {
                inlineCode("@AfterEach")
                +" - is run after each "
                inlineCode("@Test")
                +" methods"
            }
            li {
                inlineCode("@AfterAll")
                +" - is run after all "
                inlineCode("@Test")
                +" methods"
            }
        }
        p {
            +"Note that "
            inlineCode("@Test")
            +" methods may run in any order, or may run in parallel."
        }
    }
)

object JUnitLifecycleSlide : Slide(
    header = "JUnit Test Lifecycle",
    content = {
        kotlinPlayground(
            code = """
                import org.junit.jupiter.api.AfterAll
                import org.junit.jupiter.api.AfterEach
                import org.junit.jupiter.api.Assertions
                import org.junit.jupiter.api.BeforeAll
                import org.junit.jupiter.api.BeforeEach
                import org.junit.jupiter.api.DisplayName
                import org.junit.jupiter.api.Test

                class TemperatureConverterTest {

                    @BeforeEach
                    fun beforeEach() {
                        println("This runs before each test");
                    }

                    @AfterEach
                    fun afterEach() {
                        println("This runs after each test");
                    }

                    @Test
                    @DisplayName("should convert Celsius to Fahrenheit - 0C = 32F")
                    fun testConvertCelsiusToFahrenheit() {
                        Assertions.assertEquals(32.0, TemperatureConverter.toFahrenheit(0.0));
                    }

                    // different style of test name
                    @Test
                    fun `should convert Fahrenheit to Celsius - 32F = 0C`() {
                        Assertions.assertEquals(0.0, TemperatureConverter.toCelsius(32.0));
                    }

                    companion object {

                        @BeforeAll
                        @JvmStatic
                        fun setUp() {
                            println("This runs once before all tests");
                        }

                        @AfterAll
                        @JvmStatic
                        fun tearDown() {
                            println("This runs once after all tests");
                        }
                    }
                }
            """.trimIndent(),
            executable = false
        )
    }
)

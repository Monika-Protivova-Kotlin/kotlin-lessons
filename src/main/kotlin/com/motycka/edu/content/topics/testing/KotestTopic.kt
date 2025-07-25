package com.motycka.edu.content.topics.testing

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground

object KotestTopic : Topic(
    title = "Kotest",
    slides = listOf(
        TestingFrameworksSlide,
        KotestOverviewSlide,
        KotestTestsSlide,
        KotestStylesSlide,
        KotestAssertionsSlide,
        CleanTestsSlide,
        GoodTestsSlide,
        DescriptiveTestsSlide,
        DescriptiveAssertionsSlide,
        TestLifecycleSlide
    )
)

object TestingFrameworksSlide : Slide(
    header = "Testing Frameworks",
    summary = {
        +"Unit testing framework is a set of tools that provides features helpful for writing, executing and evaluating test cases."
    },
    content = {
        p { +"Main features of a unit testing framework include:" }
        ul {
            li { +"Write test cases" }
            li { +"Execute test cases" }
            li { +"Evaluate test results" }
        }
        p {
            +"There are several options available for Kotlin, such as "
            strong { +"JUnit" }
            +", which is a Java framework, and "
            strong { +"Kotest" }
            +"."
        }
        ul {
            li { strong { +"JUnit" }; +" is one of the most commonly used testing frameworks for Java and also for Kotlin." }
            li { strong { +"Kotest" }; +" is a Kotlin-specific testing framework." }
        }
        p {
            +"We will use "
            strong { +"Kotest" }
            +" in this course, but the concepts that we will learn with generally apply to all unit testing frameworks."
        }
    }
)

object KotestOverviewSlide : Slide(
    header = "Kotest",
    summary = {
        strong { +"Kotest" }
        +" is a Kotlin-specific testing framework that provides a rich set of features for writing tests in a more expressive and idiomatic way."
    },
    content = {
        p {
            +"It is actually based on the "
            strong { +"JUnit" }
            +", so it can be used alongside JUnit tests."
        }
        p {
            +"It supports various styles of testing, such as "
            strong { +"behavior-driven development (BDD)" }
            +", "
            strong { +"data-driven testing" }
            +", and "
            strong { +"property-based testing" }
            +"."
        }
    }
)

object KotestTestsSlide : Slide(
    header = "Tests written in Kotest",
    summary = {
        +"Kotlin provides a library of functions and abstract classes you can use to implement tests."
    },
    content = {
        p {
            +"There are several styles of writing tests in "
            strong { +"Kotest" }
            +", which you can chose by extending a specific Kotest class, or using a specific function."
        }
        kotlinPlayground(
            code = """
                class ExampleTest : FunSpec({
                    test("test name") {
                        // test code
                    }
                })
            """.trimIndent(),
            executable = false
        )
        p { +"A more realistic test might look like this:" }
        kotlinPlayground(
            code = """
                package com.motycka.edu.lesson04

                import com.motycka.edu.lesson04.Coffee.*
                import io.kotest.core.spec.style.FunSpec
                import io.kotest.core.spec.style.ShouldSpec
                import io.kotest.core.spec.style.StringSpec
                import io.kotest.matchers.shouldBe

                class PriceCalculatorTest : FunSpec({

                    val priceCalculator = PriceCalculator(applyDiscount = 4)

                    test("should apply discount for 4 coffees - cheapest one free") {
                        val order = listOf(ESPRESSO, ESPRESSO, CAPPUCCINO, AMERICANO)

                        val expectedTotal = order.sumOf { it.price } - AMERICANO.price

                        priceCalculator.calculatePrice(order).shouldBe(expectedTotal)
                    }
                })
            """.trimIndent(),
            executable = false
        )
    }
)

object KotestStylesSlide : Slide(
    header = "Kotest styles",
    summary = {
        +"As mentioned "
        strong { +"Kotest" }
        +" supports several styles of writing tests."
    },
    content = {
        p {
            strong { +"Kotest" }
            +" supports several styles of writing tests, such as:"
        }
        ul {
            li {
                strong { +"FunSpec" }
                +" - a style that uses functions to define tests."
                kotlinPlayground(
                    code = """
                        class ExampleFunTest : FunSpec({
                            test("test name") {
                                // test code
                            }
                        })
                    """.trimIndent(),
                    executable = false
                )
            }
            li {
                strong { +"StringSpec" }
                +" - a style that uses strings to define tests."
                kotlinPlayground(
                    code = """
                        class ExampleStringTest : StringSpec({
                            "test name" {
                                // test code
                            }
                        })
                    """.trimIndent(),
                    executable = false
                )
            }
            li {
                strong { +"ShouldSpec" }
                +" - a style that uses \"should\" to define tests."
                kotlinPlayground(
                    code = """
                        class ExampleTest : ShouldSpec({
                            should("test name") {
                                // test code
                            }
                        })
                    """.trimIndent(),
                    executable = false
                )
            }
        }
    }
)

object KotestAssertionsSlide : Slide(
    header = "Assertions in Kotest",
    summary = {
        +"Assertions are functions that evaluate the actual value against the expected value. "
        +"When the actual value does not match the expected value, the assertion fails."
    },
    content = {
        +"Example of assertion in Kotest:"
        kotlinPlayground(
            code = """
                number.shouldBeGreaterThan(3.0)
                number.shouldBeGreaterThanOrEqualTo(3.15)
                number.shouldBeLessThan(4.0)
                number.shouldBeBetween(a = 3.0, b = 4.0, tolerance = 0.01)

                string.shouldBe("Hello, Kotest!")
                string.shouldNotBeNull()
                string.shouldNotBeEmpty()

                list.shouldBe(listOf(1, 2, 3, 4, 5))
                list.shouldHaveSize(5)
                list.shouldNotBeEmpty()
                list.shouldBeSorted()
                list.shouldBeMonotonicallyIncreasing()
                list.shouldContain(2)
                list.shouldContainAll(1, 2, 3)

                booleanValue.shouldBeTrue()
                booleanValue.shouldBeFalse()
            """.trimIndent(),
            executable = false
        )
    }
)

object CleanTestsSlide : Slide(
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

object GoodTestsSlide : Slide(
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
            li { strong { +"Deterministic" }; +" - each test run should yield the same result." }
            li { strong { +"Easy to understand" }; +" - this will help with interpreting results and maintenance." }
            li { strong { +"Fast" }; +" - we want fast feedback loop." }
            li { strong { +"Independent" }; +" - each test should be able to run in isolation and in any order." }
            li { strong { +"Repeatable" }; +" - each test should be able to run multiple times." }
            li { strong { +"Focused" }; +" - each test should focus on testing one thing only." }
        }
    }
)

object DescriptiveTestsSlide : Slide(
    header = "Descriptive tests",
    summary = {
        +"One of the ways you can make your test code easier to understand is using descriptive names and well-designed assertions."
    },
    content = {
        p {
            strong { +"Kotest" }
            +" supports nesting of tests within contexts. "
            +"This allows you to logically group tests together and provide more context and better readability "
            +"in test results."
        }
        kotlinPlayground(
            code = """
                class PriceCalculatorTests: StringSpec({

                    "Price Calculator" {

                        "should not allow discount less than 2" {
                            val exception = kotlin.runCatching { PriceCalculator(applyDiscount = 1) }
                            exception.isFailure shouldBe true
                        }
                    }

                    "when calculating with discount on every 4th coffee" {

                        val priceCalculator = PriceCalculator(applyDiscount = 4)

                        "should apply no discount for 3 coffees" {
                            val order = listOf(ESPRESSO, CAPPUCCINO, AMERICANO)
                            val expectedTotal = order.sumOf { it.price }

                            priceCalculator.calculatePrice(order).shouldBe(expectedTotal)
                        }

                        "should apply discount for 4 coffees - cheapest one is free" {
                            val order = listOf(ESPRESSO, ESPRESSO, CAPPUCCINO, AMERICANO)
                            val expectedTotal = order.sumOf { it.price } - AMERICANO.price

                            priceCalculator.calculatePrice(order).shouldBe(expectedTotal)
                        }

                        "should apply discount for 9 coffees - cheapest two are free" {
                            val order = listOf(
                                ESPRESSO,
                                CAPPUCCINO, CAPPUCCINO, CAPPUCCINO,
                                FLAT_WHITE, FLAT_WHITE,
                                LATTE, LATTE,
                                AMERICANO
                            )
                            val expectedTotal = order.sumOf { it.price } - AMERICANO.price - ESPRESSO.price

                            priceCalculator.calculatePrice(order).shouldBe(expectedTotal)
                        }
                    }
                })
            """.trimIndent(),
            executable = false
        )
    }
)

object DescriptiveAssertionsSlide : Slide(
    header = "Descriptive assertions",
    summary = {
        +"Another important aspect of testing is understanding test results."
    },
    content = {
        p {
            +"To make understanding test results easier, we should choose the right assertion methods that will give us the most information about the failure. "
            +"All of these assertions would work, but the first two provide much clearer information"
        }
        strong { +"Example 1" }
        kotlinPlayground(
            code = """
                number.shouldBe(100.0)
            """.trimIndent(),
            executable = false
        )
        pre {
            code {
                classes = setOf("hljs", "text")
                +"""
                    expected:<100.0> but was:<99.0>
                    Expected :100.0
                    Actual   :99.0
                """.trimIndent()
            }
        }
        kotlinPlayground(
            code = """
                string.shouldBe("Hello, Kotlin!")
            """.trimIndent(),
            executable = false
        )
        pre {
            code {
                classes = setOf("hljs", "text")
                +"""
                    expected:<"Hello, Kotlin!"> but was:<"Hello, Kotest!">
                    Expected :"Hello, Kotlin!"
                    Actual   :"Hello, Kotest!"
                """.trimIndent()
            }
        }
        strong { +"Example 2" }
        kotlinPlayground(
            code = """
                (string == "Hello, Kotlin!").shouldBeTrue()
            """.trimIndent(),
            executable = false
        )
        pre {
            code {
                classes = setOf("hljs", "text")
                +"""
                    Expected :true
                    Actual   :false
                """.trimIndent()
            }
        }
    }
)

object TestLifecycleSlide : Slide(
    header = "Test Lifecycle",
    summary = {
        +"Test lifecycle is the sequence of events that happen during the execution of a test."
    },
    content = {
        p {
            +"Several annotations can be used to control the test lifecycle. They might be specific to the test style you chose, but in general they look like this. "
            +"These annotations are: "
            br()
        }
        kotlinPlayground(
            code = """
                class ExampleSpec : FunSpec({

                    beforeTest {
                        println("this block executes before each test")
                    }

                    afterTest {
                        println("this block executes after each test")
                    }

                    beforeSpec {
                        println("this block executes before spec")
                    }

                    afterSpec {
                        println("this block executes after spec")
                    }

                    test("test name") {
                        // test code
                    }
                })
            """.trimIndent(),
            executable = false
        )
    }
)

package com.motycka.edu.content.topics.career

import com.motycka.edu.model.*
import kotlinx.html.*

object AICodeGenerationExerciseTopic : Topic(
    title = "Exercise: AI Code Generation & Review",
    slides = listOf(
        ProblemStatementSlide,
        ExerciseInstructionsSlide,
        CodeReviewChecklistSlide,
        CommonAIPitfallsSlide,
        DiscussionSlide
    )
)

object ProblemStatementSlide : Slide(
    header = "Exercise: Build a Log File Analyzer",
    summary = {
        +"You will use AI to generate a Kotlin program that analyzes server log files. Your goal is not just to get working code, but to critically evaluate what the AI produces."
    },
    content = {
        twoColumns(
            left = {
                h4 { +"Requirements" }
                p {
                    +"Create a Kotlin program with a "
                    inlineCode("main")
                    +" function that:"
                }
                ul {
                    li { +"Reads a log file from a specified path" }
                    li { +"Parses each log line to extract: timestamp, HTTP method, endpoint, status code, response time" }
                    li { +"Calculates and prints statistics:" }
                    ul {
                        li { +"Total requests processed" }
                        li { +"Average response time" }
                        li { +"Count of requests by status code (200, 404, 500, etc.)" }
                        li { +"Top 5 slowest endpoints" }
                        li { +"List of all failed requests (status >= 400)" }
                    }
                    li { +"Handles errors gracefully (missing files, malformed lines)" }
                }
            },
            right = {
                h4 { +"Sample Input Format" }
                p { +"Each log line follows this format:" }
                pre {
                    code {
                        classes = setOf("hljs", "text")
                        +"""
                            2024-01-15 10:23:45 GET /api/users 200 45ms
                            2024-01-15 10:23:47 POST /api/orders 201 123ms
                            2024-01-15 10:23:50 GET /api/products 404 12ms
                            2024-01-15 10:23:52 GET /api/users/123 500 89ms
                            2024-01-15 10:23:55 DELETE /api/orders/456 204 67ms
                        """.trimIndent()
                    }
                }

                h4 { +"Expected Output Example" }
                pre {
                    code {
                        classes = setOf("hljs", "text")
                        +"""
                            === Log Analysis Results ===
                            Total Requests: 5
                            Average Response Time: 67.2ms

                            Status Code Distribution:
                            - 200: 1
                            - 201: 1
                            - 204: 1
                            - 404: 1
                            - 500: 1

                            Top 5 Slowest Endpoints:
                            1. POST /api/orders - 123ms
                            2. GET /api/users/123 - 89ms
                            ...

                            Failed Requests (4xx/5xx):
                            - GET /api/products (404) - 12ms
                            - GET /api/users/123 (500) - 89ms
                        """.trimIndent()
                    }
                }
            }
        )

        hintCard {
            +"Test your solution with various edge cases: empty files, malformed lines, missing fields, very large files."
        }
    }
)

object ExerciseInstructionsSlide : Slide(
    header = "How to Complete This Exercise",
    summary = {
        +"Follow these steps to generate and critically evaluate AI-generated code."
    },
    content = {
        twoColumns(
            left = {
                h4 { +"Step 1: Generate with AI (10 minutes)" }
                ol {
                    li { +"Choose your AI tool (ChatGPT, Claude, GitHub Copilot, etc.)" }
                    li { +"Copy the requirements from the previous slide" }
                    li { +"Ask the AI to generate a Kotlin solution with a main function" }
                    li { +"Review the generated code - does it look reasonable?" }
                    li { +"Create a test log file with sample data" }
                    li { +"Run the code and see if it works" }
                }

                h4 { +"Step 2: Critical Review (15 minutes)" }
                ol {
                    li { +"Go through the review checklist (next slide)" }
                    li { +"Test edge cases: empty file, malformed lines, missing file" }
                    li { +"Check for the common pitfalls AI makes" }
                    li { +"Identify what needs to be fixed or improved" }
                    li { +"Make necessary corrections yourself" }
                    li { +"Document what the AI got wrong" }
                }
            },
            right = {
                h4 { +"Sample Test Data" }
                p {
                    +"Create a file called "
                    inlineCode("server.log")
                    +" with this content:"
                }
                pre {
                    code {
                        classes = setOf("hljs", "text")
                        +"""
                            2024-01-15 10:23:45 GET /api/users 200 45ms
                            2024-01-15 10:23:47 POST /api/orders 201 123ms
                            2024-01-15 10:23:50 GET /api/products 404 12ms
                            2024-01-15 10:23:51 INVALID LINE
                            2024-01-15 10:23:52 GET /api/users/123 500 89ms
                            2024-01-15 10:23:55 DELETE /api/orders/456 204 67ms
                            2024-01-15 10:23:58 GET /api/products 200 23ms
                            2024-01-15 10:24:01 POST /api/users 500 156ms
                            2024-01-15 10:24:03 GET /api/orders 200 34ms
                        """.trimIndent()
                    }
                }

                warningCard {
                    +"Notice the invalid line on line 4. Does the AI-generated code handle this gracefully?"
                }
            }
        )

        importantCard("Learning Goals") {
            +"This exercise is about developing critical review skills, not just getting working code. "
            +"Pay attention to what the AI does well and where it falls short. "
            +"Professional developers must be able to evaluate and improve AI-generated solutions."
        }
    }
)

object CodeReviewChecklistSlide : Slide(
    header = "Code Review Checklist",
    summary = {
        +"Use this checklist to systematically evaluate the AI-generated code. Don't just check if it runs - evaluate its quality."
    },
    content = {
        twoColumns(
            left = {
                h4 { +"Correctness & Logic" }
                ul {
                    li {
                        +"☐ Does it parse all log fields correctly?"
                    }
                    li {
                        +"☐ Are calculations accurate (averages, counts)?"
                    }
                    li {
                        +"☐ Does it correctly identify failed requests (status >= 400)?"
                    }
                    li {
                        +"☐ Are the top 5 slowest endpoints sorted correctly?"
                    }
                    li {
                        +"☐ Does it handle the response time parsing (removing 'ms')?"
                    }
                }

                h4 { +"Error Handling" }
                ul {
                    li {
                        +"☐ What happens if the file doesn't exist?"
                    }
                    li {
                        +"☐ What happens with a malformed line?"
                    }
                    li {
                        +"☐ What if a field is missing?"
                    }
                    li {
                        +"☐ What if the file is empty?"
                    }
                    li {
                        +"☐ Are error messages helpful?"
                    }
                }

                h4 { +"Edge Cases" }
                ul {
                    li {
                        +"☐ Works with empty log file?"
                    }
                    li {
                        +"☐ Handles very large files efficiently?"
                    }
                    li {
                        +"☐ Works with single log entry?"
                    }
                    li {
                        +"☐ Handles unusual status codes (e.g., 301, 503)?"
                    }
                }
            },
            right = {
                h4 { +"Code Quality" }
                ul {
                    li {
                        +"☐ Is the code readable and well-organized?"
                    }
                    li {
                        +"☐ Are variable names meaningful?"
                    }
                    li {
                        +"☐ Is it properly structured (not one giant function)?"
                    }
                    li {
                        +"☐ Does it use appropriate data structures?"
                    }
                    li {
                        +"☐ Are there any code smells or anti-patterns?"
                    }
                }

                h4 { +"Performance" }
                ul {
                    li {
                        +"☐ Does it read the file efficiently?"
                    }
                    li {
                        +"☐ Are there unnecessary iterations?"
                    }
                    li {
                        +"☐ Does it handle large files without loading everything into memory?"
                    }
                    li {
                        +"☐ Are sorting operations efficient?"
                    }
                }

                h4 { +"Kotlin Idioms" }
                ul {
                    li {
                        +"☐ Uses Kotlin features appropriately (nullable types, collections, etc.)?"
                    }
                    li {
                        +"☐ Follows Kotlin naming conventions?"
                    }
                    li {
                        +"☐ Uses extension functions where appropriate?"
                    }
                    li {
                        +"☐ Leverages standard library functions (map, filter, groupBy, etc.)?"
                    }
                }
            }
        )

        hintCard {
            +"Test each item methodically. Create specific test cases for edge cases and error conditions. "
            +"Don't assume the code works just because it compiles or produces some output."
        }
    }
)

object CommonAIPitfallsSlide : Slide(
    header = "Common AI Mistakes to Look For",
    summary = {
        +"AI tools often make predictable mistakes. Knowing what to look for helps you review code more effectively."
    },
    content = {
        twoColumns(
            left = {
                h4 { +"Typical Issues in This Exercise" }
                ul {
                    li {
                        strong { +"Poor error handling" }
                        br()
                        +"AI might not handle missing files or throw generic exceptions that crash the program."
                        kotlinPlayground(
                            code = """
                                // Bad: crashes on missing file
                                val lines = File("server.log").readLines()

                                // Better: handles error gracefully
                                val file = File("server.log")
                                if (!file.exists()) {
                                    println("Error: Log file not found")
                                    return
                                }
                            """.trimIndent(),
                            executable = false
                        )
                    }
                    li {
                        strong { +"Ignoring malformed lines" }
                        br()
                        +"AI might silently skip invalid lines or crash when parsing fails."
                        kotlinPlayground(
                            code = """
                                // Bad: crashes on invalid format
                                val parts = line.split(" ")
                                val statusCode = parts[4].toInt()

                                // Better: validates and handles errors
                                val parts = line.split(" ")
                                if (parts.size < 6) {
                                    println("Skipping malformed line: ${'$'}line")
                                    continue
                                }
                            """.trimIndent(),
                            executable = false
                        )
                    }
                    li {
                        strong { +"Incorrect parsing" }
                        br()
                        +"Might forget to remove 'ms' from response time or parse fields in wrong order."
                    }
                    li {
                        strong { +"Off-by-one errors" }
                        br()
                        +"Top 5 might show 4 or 6 results, or include/exclude the wrong status codes."
                    }
                }
            },
            right = {
                h4 { +"General AI Code Problems" }
                ul {
                    li {
                        strong { +"Inefficient algorithms" }
                        br()
                        +"Might iterate multiple times instead of processing in one pass."
                    }
                    li {
                        strong { +"Overly complex solutions" }
                        br()
                        +"AI sometimes produces unnecessarily complicated code when simpler would work."
                    }
                    li {
                        strong { +"Missing null safety" }
                        br()
                        +"Might use !! (force non-null) or not handle nullable types properly."
                    }
                    li {
                        strong { +"Not using standard library" }
                        br()
                        +"Reinvents functionality that exists in Kotlin standard library (groupBy, averageBy, etc.)."
                    }
                    li {
                        strong { +"Poor naming" }
                        br()
                        +"Generic names like "
                        inlineCode("data")
                        +", "
                        inlineCode("temp")
                        +", "
                        inlineCode("x")
                        +" instead of meaningful names."
                    }
                    li {
                        strong { +"Incomplete requirements" }
                        br()
                        +"Might miss one of the statistics or format output differently than specified."
                    }
                }

                warningCard {
                    +"AI-generated code often works for the \"happy path\" but fails on edge cases. "
                    +"Always test with invalid inputs, empty data, and boundary conditions."
                }
            }
        )
    }
)

object DiscussionSlide : Slide(
    header = "Discussion & Learning Points",
    summary = {
        +"Reflect on what you learned about AI capabilities, limitations, and how to work effectively with AI coding assistants."
    },
    content = {
        h4 { +"Questions to Consider" }
        ul {
            li { +"What did the AI do well? What aspects of the solution were correct and well-structured?" }
            li { +"What did the AI get wrong or miss completely?" }
            li { +"How much effort was required to review and fix the code compared to writing it from scratch?" }
            li { +"Would you have made the same mistakes as the AI? Different mistakes?" }
            li { +"What would have happened if you deployed this code without careful review?" }
            li { +"How can you improve your prompts to get better results from AI?" }
            li { +"What types of problems is AI particularly good or bad at solving?" }
        }

        twoColumns(
            left = {
                h4 { +"Key Takeaways" }
                ul {
                    li {
                        strong { +"AI is a powerful tool, not a replacement" }
                        br()
                        +"It can accelerate development but requires human oversight and critical thinking."
                    }
                    li {
                        strong { +"Testing is essential" }
                        br()
                        +"Never trust AI-generated code without thorough testing, especially edge cases."
                    }
                    li {
                        strong { +"Code review skills matter more than ever" }
                        br()
                        +"Your ability to spot issues and improve code is increasingly valuable."
                    }
                    li {
                        strong { +"Understanding beats copying" }
                        br()
                        +"You must understand what the code does to know if it's correct and maintainable."
                    }
                }
            },
            right = {
                h4 { +"Best Practices for AI-Assisted Development" }
                ul {
                    li { +"Start with clear, detailed requirements" }
                    li { +"Generate code for well-defined, isolated problems" }
                    li { +"Review every line before committing" }
                    li { +"Test with edge cases and error conditions" }
                    li { +"Refactor AI code to match your project's style" }
                    li { +"Use AI to explore approaches, not blindly accept solutions" }
                    li { +"Learn from AI mistakes to improve your own code" }
                }
            }
        )

        importantCard("Your Competitive Advantage") {
            +"As AI coding tools become ubiquitous, your competitive advantage isn't whether you use AI - it's how effectively you use it. "
            +"Developers who can rapidly generate, evaluate, test, and improve AI-generated solutions will be far more productive than those who either avoid AI or blindly trust it. "
            +"Critical thinking, code review skills, and deep understanding of software engineering principles matter more than ever."
        }
    }
)

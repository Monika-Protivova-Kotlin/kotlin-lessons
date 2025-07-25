package com.motycka.edu.content.topics.introductory.kotlin

import com.motycka.edu.builders.*
import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import kotlinx.html.a
import kotlinx.html.br
import kotlinx.html.code
import kotlinx.html.h4
import kotlinx.html.li
import kotlinx.html.ul

object YourFirstKotlinProgramTopic : Topic(
    title = "Your first Kotlin program",
    subtitle = "Exercises, Submission & Grading",
    slides = listOf(
        Slide(
            header = "Prerequisites",
            content = {
                h4 { +"You will need:" }
                highlightNumberedList(
                    startFrom = 1,
                    "A text editor or IDE" to { +"with a modern operating system (Windows, macOS, Linux)." },
                    "Git" to {
                        +"for version control. You can download it from "
                        a(href = "https://git-scm.com/downloads") { +"https://git-scm.com/downloads" }
                    },
                    "A GitHub account" to {
                        +"for submitting your code and collaborating with others. You can create an account at "
                        a(href = "https://github.com") { +"https://github.com" }
                    }
                )
            }
        ),
        Slide(
            header = "Your first Kotlin program",
            summary = {
                +"We will use GitHub Classroom for all assignments in this course."
            },
            content = {
                h4 { +"Assignment" }
                highlightNumberedList(
                    startFrom = 4,
                    "Accept the assignment" to {
                        +"Click on the assignment link provided in the course materials. This will create a private repository for your work."
                    },
                    "Clone the repository" to {
                        code {
                            +"git clone [your-repository-url]"
                        }
                    },
                    "Work on your solution" to {
                        +"Complete the assignment in your local environment."
                        br()
                        +"See "
                        code(classes = "hljs-built_in") {
                            +"README.md"
                        }
                        +" in the repository for instructions on what to implement."
                    },
                )
            }
        ),
        Slide(
            header = "Solution Submission and Grading",
            summary = { +"Once your code is running, you need to submit it for grading." },
            content = {
                h4 { +"Submission" }
                highlightNumberedList(
                    startFrom = 7,
                    "Commit and push your changes" to {
                        code { +"git add ." }
                        br()
                        code { +"git commit -m \"Complete assignment\"" }
                        br()
                        code { +"git push" }
                    },
                    "Verify submission" to {
                        +"Check your GitHub repository to ensure all changes have been pushed."
                    }
                )
                br()
                h4 { +"Grading" }
                ul {
                    li { +"Assignments will be automatically graded using tests that run when you push your code." }
                    li { +"You can see the test results in the \"Actions\" tab of your GitHub repository." }
                    li { +"Some assignments may also include manual code review by instructors." }
                    li { +"Feedback will be provided as comments in your repository or through the classroom platform." }
                }
            }
        )
    )
)

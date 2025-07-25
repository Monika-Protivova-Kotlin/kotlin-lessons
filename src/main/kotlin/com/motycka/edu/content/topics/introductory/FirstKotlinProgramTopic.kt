package com.motycka.edu.content.topics.introductory

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.highlight
import kotlinx.html.a
import kotlinx.html.br
import kotlinx.html.div
import kotlinx.html.h4
import kotlinx.html.li
import kotlinx.html.ol
import kotlinx.html.strong

object FirstKotlinProgramTopic : Topic(
    title = "Your first Kotlin program",
    subtitle = "Exercises, Submission & Grading",
    slides = listOf(
        PrerequisitesSlide,
        FirstKotlinProgramSlide,
        SubmissionGradingSlide
    )
)

object PrerequisitesSlide : Slide(
    header = "Prerequisites",
    content = {
        h4 { +"You will need:" }
        ol {
            li {
                highlight("A computer")
                +" with a modern operating system (Windows, macOS, Linux)."
                br()
                br()
            }
            li {
                highlight("A text editor or IDE")
                +" for writing Kotlin code."
                br()
                +"We will use IntelliJ IDEA Community Edition, which is free and widely used."
                br()
                +"You can download it from "
                a(href = "https://www.jetbrains.com/idea/download/") { +"https://www.jetbrains.com/idea/download/" }
                +"."
                br()
                br()
            }
            li {
                highlight("Git")
                +" for version control."
                br()
                +"You can download it from "
                a(href = "https://git-scm.com/downloads") { +"https://git-scm.com/downloads" }
                +"."
                br()
                br()
            }
            li {
                highlight("A GitHub account")
                +" for submitting your code and collaborating with others."
                +"You can create an account at "
                a(href = "https://github.com") { +"https://github.com" }
                br()
                br()
            }
        }
    }
)

object FirstKotlinProgramSlide : Slide(
    header = "Your first Kotlin program",
    summary = {
        +"You will use GitHub Classroom for all assignments in this course."
    },
    content = {
        div {
            h4 { +"Assignment" }
            ol {
                attributes["start"] = "4"
                li {
                    highlight("Accept the assignment")
                    br()
                    +"Click on the assignment link provided in the course materials. This will create a private repository for your work."
                    br()
                    br()
                }
                li {
                    highlight("Clone the repository")
                    br()
                    inlineCode("git clone [your-repository-url]")
                    br()
                    br()
                }
                li {
                    highlight("Work on your solution")
                    br()
                    +"Complete the assignment in your local environment."
                    br()
                    +"See "
                    inlineCode("README.md")
                    +" in the repository for instructions on what to implement."
                    br()
                    br()
                }
            }
        }
    }
)

object SubmissionGradingSlide : Slide(
    header = "Solution Submission and Grading",
    summary = {
        +"Once your code is running, you need to submit it for grading."
    },
    content = {
        div {
            h4 { +"Submission" }
            ol {
                attributes["start"] = "7"
                li {
                    highlight("Commit and push your changes")
                    br()
                    inlineCode("git add .")
                    br()
                    inlineCode("git commit -m \"Complete assignment\"")
                    br()
                    inlineCode("git push")
                    br()
                    br()
                }
                li {
                    highlight("Verify submission")
                    br()
                    +"Check your GitHub repository to ensure all changes have been pushed."
                }
            }
        }
    }
)

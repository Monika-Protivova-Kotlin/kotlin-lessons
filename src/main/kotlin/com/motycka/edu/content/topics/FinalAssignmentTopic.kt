package com.motycka.edu.content.topics

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.twoColumns
import kotlinx.html.*

object FinalAssignmentTopic : Topic(
    title = "Final Assignment",
    slides = listOf(
        AssignmentOverviewSlide,
        DeliverableSlide,
        LearningOutcomesSlide
    )
)

object AssignmentOverviewSlide : Slide(
    header = "Final Assignment: Fantasy Game Simulation API",
    summary = {
        +"You will build a complete Spring Boot REST API for a fantasy game simulation where players create characters, engage in battles, and compete on leaderboards."
    },
    content = {
        p {
            strong { +"Purpose:" }
        }
        p {
            +"You will build a complete Spring Boot REST API for a fantasy game simulation where players create characters "
            +"(warriors and sorcerers), engage in battles, and compete on leaderboards. This capstone project integrates "
            +"all concepts learned throughout the course into a real-world application."
        }
        p {
            strong { +"What It's About:" }
        }
        p {
            +"A turn-based fantasy game backend where users manage characters with different classes and attributes, "
            +"simulate battles with strategic risk/reward mechanics, and track rankings through a leaderboard system. "
            +"Authentication and a web UI are provided; you will implement the core game logic and data layer."
        }
    }
)

object DeliverableSlide : Slide(
    header = "Expected Deliverable",
    summary = {
        +"You will deliver a working Spring Boot application with layered architecture, REST APIs, and comprehensive tests."
    },
    content = {
        p {
            +"You will build and deliver:"
        }
        ul {
            li { +"A working Spring Boot application with three main REST APIs (Characters, Matches, Leaderboards)" }
            li { +"Layered architecture implementation (Controller → Service → Repository)" }
            li { +"Proper data transformation between layers using DTOs" }
            li { +"Authentication-secured endpoints" }
            li { +"Comprehensive test coverage using JUnit and MockK" }
            li { +"Error handling and input validation throughout" }
        }
        twoColumns(
            left = {
                p {
                    strong { +"What's Provided:" }
                }
                ul {
                    li { +"User Management API - Complete reference implementation" }
                    li { +"Authentication System - Spring Security with Basic Auth configured" }
                    li { +"User Interface - A complete web UI that consumes your API" }
                    li { +"Database Schema - H2 database schema and initial data" }
                }
            },
            right = {
                p {
                    strong { +"What You Must Implement:" }
                }
                ul {
                    li { +"Characters API - All character management endpoints" }
                    li { +"Matches API - Match creation and battle simulation" }
                    li { +"Leaderboard API - Rankings and statistics" }
                }
            }
        )
    }
)

object LearningOutcomesSlide : Slide(
    header = "Learning Outcomes",
    summary = {
        +"You will apply all course concepts by building a complete application with proper architecture, testing, and Spring Boot integration."
    },
    content = {
        p {
            +"By completing this assignment, you will:"
        }
        ul {
            li { +"Apply layered architecture patterns in a complete application" }
            li { +"Implement RESTful API design with proper contracts" }
            li { +"Practice domain modeling with inheritance (character classes)" }
            li { +"Implement complex business logic (battle simulation, experience calculation)" }
            li { +"Design for testability and write comprehensive tests" }
            li { +"Work with Spring Security for authentication" }
            li { +"Integrate all course concepts: Spring Boot, JPA/JDBC, dependency injection, testing, DTOs, and architectural patterns" }
        }
        p {
            strong { +"Technologies You'll Use:" }
        }
        ul {
            li { +"Kotlin & Spring Boot" }
            li { +"Spring Data JPA or JDBC" }
            li { +"Spring Security (Basic Auth)" }
            li { +"H2 Database" }
            li { +"JUnit & MockK for testing" }
            li { +"REST API design principles" }
        }
    }
)

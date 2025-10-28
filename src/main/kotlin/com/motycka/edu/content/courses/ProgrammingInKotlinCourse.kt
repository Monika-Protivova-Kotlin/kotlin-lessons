package com.motycka.edu.content.courses

import com.motycka.edu.content.lessons.programming.*
import com.motycka.edu.content.topics.backend.*
import com.motycka.edu.content.topics.backend.springboot.*
import com.motycka.edu.content.topics.basics.*
import com.motycka.edu.content.topics.basics.ExceptionHandlingTopic
import com.motycka.edu.content.topics.basics.api.*
import com.motycka.edu.content.topics.basics.arrays.ArraysAdvancedTopic
import com.motycka.edu.content.topics.basics.collections.*
import com.motycka.edu.content.topics.basics.collections.CollectionTransformationsTopic
import com.motycka.edu.content.topics.basics.collections.CollectionTraversalTopic
import com.motycka.edu.content.topics.basics.collections.CollectionsTopic
import com.motycka.edu.content.topics.basics.concurrency.AtomicVariablesTopic
import com.motycka.edu.content.topics.basics.concurrency.CoffeeShopExerciseTopic
import com.motycka.edu.content.topics.basics.concurrency.ConcurrentUtilitiesTopic
import com.motycka.edu.content.topics.basics.concurrency.CoroutinesTopic
import com.motycka.edu.content.topics.basics.concurrency.FuturesTopic
import com.motycka.edu.content.topics.basics.concurrency.MultithreadingTopic
import com.motycka.edu.content.topics.basics.concurrency.SynchronizersTopic
import com.motycka.edu.content.topics.basics.concurrency.ThreadExercisesTopic
import com.motycka.edu.content.topics.basics.conditionals.ConditionalsTopic
import com.motycka.edu.content.topics.basics.datetime.*
import com.motycka.edu.content.topics.basics.essentials.*
import com.motycka.edu.content.topics.basics.functions.*
import com.motycka.edu.content.topics.basics.io.*
import com.motycka.edu.content.topics.design.ApplicationArchitecturePatternsTopic
import com.motycka.edu.content.topics.design.DesignPatternsTopic
import com.motycka.edu.content.topics.design.FunctionalProgrammingTopic
import com.motycka.edu.content.topics.design.InversionOfControlTopic
import com.motycka.edu.content.topics.design.SOLIDPracticeTopic
import com.motycka.edu.content.topics.design.solid.DesignForTestabilityTopic
import com.motycka.edu.content.topics.design.solid.SolidPrinciplesTopic
import com.motycka.edu.content.topics.introductory.DevelopmentEnvironmentTopic
import com.motycka.edu.content.topics.introductory.java.IntroductionToJavaTopic
import com.motycka.edu.content.topics.introductory.java.JREJDKCompilerTopic
import com.motycka.edu.content.topics.introductory.java.JavaVirtualMachineTopic
import com.motycka.edu.content.topics.introductory.kotlin.IntroductionToKotlinTopic
import com.motycka.edu.content.topics.introductory.kotlin.WhyKotlinTopic
import com.motycka.edu.content.topics.introductory.kotlin.YourFirstKotlinProgramTopic
import com.motycka.edu.content.topics.oop.*
import com.motycka.edu.content.topics.testing.*
import com.motycka.edu.model.Course
import com.motycka.edu.model.Lesson

val ProgrammingInKotlinCourse = Course(
    name = "Programming",
    subTitle = "in Kotlin",
    lessons = listOf(
        // Week 1: Kotlin Fundamentals
        KotlinEssentialsLesson,                       // L1
        ClassesAndFunctionsLesson,                    // L2
        CollectionsAndAdvancedFunctionsLesson,        // L3
        IOAndExceptionsLesson,                        // L4
        PolymorphismInheritanceLesson,                // L5

        // Week 2: Advanced Concepts & Architecture
        TestingLesson,                                // L6
        MemoryAndThreadsLesson,                       // L7
        SOLIDAndArchitectureLesson,                   // L8
        SpringBootIntroductionLesson,                 // L9
        RESTArchitectureLesson,                       // L10

        // Week 3: Spring Boot & Production
        SpringBootBusinessLogicLesson,                // L11
        SpringBootDataLayerLesson,                    // L12
        SecurityAndAuthenticationLesson,              // L13
        DeploymentAndObservabilityLesson,             // L14
        SpringBootApplicationV3Lesson                 // L15: Project Work
    )
)

object KotlinEssentialsLesson : Lesson(
    title = "Kotlin Essentials",
    preTitle = "Week 1 | Lesson 1",
    subTitle = "History, Syntax, Types, Operators, Conditionals",
    summary = "Core Kotlin syntax, data types, functions, and language fundamentals",
    topics = listOf(
        // Introduction to Kotlin
        WhyKotlinTopic,
        DevelopmentEnvironmentTopic,
        YourFirstKotlinProgramTopic,
        IntroductionToJavaTopic,
        IntroductionToKotlinTopic,

        // Kotlin Essentials
        KotlinLanguageSyntaxTopic,
        KotlinDataTypesTopic,
        ArraysAdvancedTopic,
        OperatorsTopic,
        ConditionalsTopic,
        WorkingWithStringsTopic,

        // Shape Analyzer Exercise 1 (combines arrays, conditionals, when expressions)
        ShapeAnalyzerExercise1Topic,

        // Function Basics
        FunctionsTopic,
        // Shape Analyzer Exercise 2 (refactor Exercise 1 using functions)
        ShapeAnalyzerExercise2Topic,

        // Assignment
        KotlinEssentialsAssignmentTopic
    )
)

object ClassesAndFunctionsLesson : Lesson(
    preTitle = "Week 1 | Lesson 2",
    title = "Object-Oriented Programming Principles & Models",
    summary = "Understanding and implementing object-oriented programming concepts in Kotlin",
    contentInfo = listOf(
        "Objects, Classes, Data Models, Access modifiers"
    ),
    topics = listOf(
        // OOP Basics
        ObjectOrientedProgrammingTopic,
        CommunicationOfIntentTopic,

        // Classes, Objects and Data Models
        ObjectsAndClassesTopic,
        ObjectsAndClassesExerciseTopic,
        SingletonObjectsTopic,
        SingletonObjectsExerciseTopic,
        DataModelsTopic,
        PairsAndTriplesTopic,

        // Packages, Imports, Modifiers
        PackagesImportsModifiersTopic,

        // Assignment
        ClassesAndFunctionsAssignmentTopic
    )
)

object CollectionsAndAdvancedFunctionsLesson : Lesson(
    title = "Collections, Advanced Functions & Lambdas",
    preTitle = "Week 1 | Lesson 3",
    subTitle = "Lambdas, Higher-Order Functions, Extension Functions, Scope Functions, Collections, Sequences",
    summary = "Lambdas, higher-order functions, collections, and functional programming techniques",
    topics = listOf(
        // Advanced Functions & Lambdas
        LambdaAndAnonymousFunctionsTopic,
        ExtensionFunctionsTopic,
        ExtensionFunctionsExerciseTopic,
        ScopeFunctionsTopic,

        // Ranges & Collections
        RangesAndProgressionsTopic,
        CollectionsTopic,
        CollectionOperationsTopic,
        CollectionTraversalTopic,
        CollectionTransformationsTopic,

        // Collection Exercises
        CollectionMappingExercisesTopic,

        // Sequences
        SequencesTopic,

        // Practice
        CollectionsPracticeTopic
    )
)

object IOAndExceptionsLesson : Lesson(
    title = "I/O, Errors and Exceptions",
    preTitle = "Week 1 | Lesson 4",
    subTitle = "File I/O, Exception Handling, Error Handling Strategies",
    summary = "File operations, command-line input, and comprehensive error handling",
    topics = listOf(
        // Exceptions & Error Handling
//        ExceptionHandlingTopic,
        ApplicationErrorHandlingTopic,

        ExceptionsTopic,
        ExceptionHandlingTopic,
        ExceptionsExercisesTopic,

        // CommandLineInputTopic,

        // I/O
        IOOverviewTopic,
        PathsAndResourcesTopic,
        FileOperationsTopic,
        StreamsTopic,
        IOPracticeTopic,

        // Assignment
        IOAndExceptionsAssignmentTopic
    )
)

object TestingLesson : Lesson(
    title = "Testing Fundamentals",
    preTitle = "Week 2 | Lesson 6",
    subTitle = "Dependency Management, Logging, Debugging, Documentation, Validation, Testing fundamentals, Levels of testing, Kotest",
    summary = "Testing principles, test design, unit testing, integration testing with Kotest",
    topics = listOf(
        DependencyManagementTopic,
        LoggingTopic,
        DebuggingDocumentationTopic,
        ValidationTopic,
        IntroductionToTestingTopic,
        TypesOfTestingTopic,
        TestCasesTopic,
        TestDesignTechniquesTopic,
        UnitTestingTopic,
        IntegrationTestingTopic,
//        TestingLayersTopic,
        KotestTopic,
        PracticeTopic
    )
)

object PolymorphismInheritanceLesson : Lesson(
    title = "OOP Principles & Inheritance",
    preTitle = "Week 1 | Lesson 5",
    subTitle = "Encapsulation, Inheritance, Polymorphism, Abstraction, Generics",
    summary = "Encapsulation, inheritance, polymorphism, abstraction, and generics",
    topics = listOf(
        CommunicationOfIntentTopic,
        OopPrinciplesOverviewTopic,
        EncapsulationTopic,
        InheritanceTopic,
        InheritanceExercisesTopic,
        PolymorphismTopic,
        AbstractionTopic,
        AbstractionExercisesTopic,
        GenericsTopic,
        GenericsExercisesTopic,
        PolymorphismInheritanceAssignmentTopic
    )
)

object MemoryAndThreadsLesson : Lesson(
    title = "Memory & Concurrency",
    preTitle = "Week 2 | Lesson 7",
    subTitle = "JVM, JRE, JDK, Compiler, Memory Management, Multithreading, Atomic Variables, Synchronizers, Concurrent Utilities, Coroutines",
    summary = "JVM memory management and concurrent programming with threads and coroutines",
    topics = listOf(
        JavaVirtualMachineTopic,
        JREJDKCompilerTopic,
        JavaMemoryManagementTopic,
        GarbageCollectionTopic,
        MultithreadingTopic,
        ThreadExercisesTopic,
        ConcurrentUtilitiesTopic,
        AtomicVariablesTopic,
        SynchronizersTopic,
        FuturesTopic,
        CoroutinesTopic,
        CoffeeShopExerciseTopic
    )
)

object SOLIDAndArchitectureLesson : Lesson(
    title = "SOLID Principles, Architecture & Application Design",
    preTitle = "Week 2 | Lesson 8",
    subTitle = "SOLID Principles, Design for Testability, Dependency Injection, Application Architecture, Functional Programming, Design Patterns",
    summary = "SOLID principles, design patterns, and application architecture",
    topics = listOf(
        SolidPrinciplesTopic,
        DesignForTestabilityTopic,
        InversionOfControlTopic,
        ApplicationArchitecturePatternsTopic,
        FunctionalProgrammingTopic,
        DesignPatternsTopic,
        SOLIDPracticeTopic
    )
)

object RESTArchitectureLesson : Lesson(
    title = "REST Architecture & Application Design",
    preTitle = "Week 2 | Lesson 10",
    subTitle = "REST Principles, HTTP Methods, Request/Response, Controllers, Routing, DTOs, Reflection",
    summary = "REST principles, HTTP methods, controllers, and API design",
    topics = listOf(
        WhatIsAPITopic,
        WhatIsRESTTopic,
        SpringBootControllersTopic,
        SpringBootRequestHandlingTopic,
        DTOsAndSerializationTopic,
        ReflectionTopic,
        APIPracticeTopic
    )
)

object SpringBootIntroductionLesson : Lesson(
    title = "Spring Boot Application",
    preTitle = "Week 2 | Lesson 9",
    subTitle = "Spring Boot Introduction, Frameworks, MVC Pattern, Application Structure, Beans & DI",
    summary = "Introduction to Spring Boot framework, MVC pattern, beans and dependency injection",
    topics = listOf(
        KotlinApplicationsTopic,
        FrameworksTopic,
        SpringFrameworkTopic,
        MVCPatternTopic,
        SpringBootApplicationStructureTopic,
        SpringBootBeansTopic,
        SpringBootConfigurationTopic
    )
)

object SpringBootBusinessLogicLesson : Lesson(
    title = "Spring Boot Business Logic",
    preTitle = "Week 3 | Lesson 11",
    subTitle = "Service Layer, Dependency Injection, Validation, Exception Handling, Testing, Date/Time",
    summary = "Service layer, dependency injection, validation, exception handling, and testing",
    topics = listOf(
        ServiceLayerTopic,
        SpringBootDependencyInjectionTopic,
        ValidationTopic,
        SpringBootExceptionHandlingTopic,
        SpringBootControllerTestingTopic,
        SpringBootServiceTestingTopic,
        // Date/Time topics
        DateTimeTopic,
        LocalDateTopic,
        LocalDateTimeTopic,
        ZonedDateTimeTopic,
        DurationAndPeriodTopic
    )
)

object SpringBootDataLayerLesson : Lesson(
    title = "Spring Boot Data Layer",
    preTitle = "Week 3 | Lesson 12",
    subTitle = "Repository Pattern, JDBC, Spring Data JPA, JOOQ, Transactions, Testing",
    summary = "Repository pattern, JDBC, JPA, JOOQ, and database interactions",
    topics = listOf(
        JDBCTopic,
        SpringDataJPATopic,
        JOOQTopic,
        TransactionsTopic,
        RepositoryTestingTopic
    )
)

object SecurityAndAuthenticationLesson : Lesson(
    title = "Security & Authentication",
    preTitle = "Week 3 | Lesson 13",
    subTitle = "Authentication vs Authorization, JWT, Spring Security, Security Best Practices",
    summary = "Authentication, authorization, JWT, and Spring Security",
    topics = listOf(
        AuthenticationTopic,
        AuthorizationTopic,
        BasicAuthTopic,
        JWTTopic,
        // Note: Spring Security topics are mostly missing
    )
)

object DeploymentAndObservabilityLesson : Lesson(
    title = "Deployment, Observability & Final Project",
    preTitle = "Week 3 | Lesson 14",
    subTitle = "Build & Packaging, Docker, Cloud Deployment, Logging, Metrics, Resilient Applications",
    summary = "Deployment, logging, metrics, and resilient applications",
    topics = listOf(
        DeploymentTopic,
        ObservabilityTopic,
        ResilientApplicationsTopic
        // Note: Build/packaging and containerization topics are mostly missing
    )
)

object SpringBootApplicationV3Lesson : Lesson(
    title = "Final Project & Assignment Work",
    preTitle = "Week 3 | Lesson 15",
    subTitle = "Service Design Patterns, Event-Based Architecture, Reactive Streams, Project Work",
    summary = "Service design patterns, event-based architecture, and project work",
    topics = listOf(
        ServiceDesignPatternsTopic,
        EventBasedArchitectureTopic,
        ReactiveStreamsTopic,
        ApplicationLifecycleTopic,
        PracticeTopic // Project work
    )
)

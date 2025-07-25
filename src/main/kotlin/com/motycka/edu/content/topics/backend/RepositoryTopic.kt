package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.highlight
import kotlinx.html.*
import com.motycka.edu.model.highlight

object RepositoryTopic : Topic(
    title = "Repository Pattern",
    slides = listOf(
        RepositoryIntroSlide,
        RepositoryResponsibilitiesSlide,
        DataAccessFrameworksSlide,
        RepositoryPatternBenefitsSlide
    )
)

object RepositoryIntroSlide : Slide(
    header = "Repository",
    summary = {
        +"The repository layer is responsible for managing the data access logic and performing CRUD "
        sub { +"(Create, Read, Update, Delete)" }
        +" operations on the database."
    },
    content = {
        p {
            +"Repository is typically a layer between service and data storage (e.g., database)."
        }
        p {
            +"It provides methods for interacting with the data, such as retrieving, creating, updating, and deleting data. "
            +"These methods don't contain any business logic, but rather focus on data access and manipulation."
        }
        p {
            +"Repositories are often implemented using some data access technology, "
            +"such as an ORM (Object-Relational Mapping) framework or a SQL library."
        }
        p {
            +"Service layer uses repository to access data and perform business logic."
        }
    }
)

object RepositoryResponsibilitiesSlide : Slide(
    header = "Repository Responsibilities",
    summary = {
        +"The repository layer focuses solely on data access and manipulation, without business logic."
    },
    content = {
        p {
            +"Key responsibilities of the repository layer:"
        }
        ul {
            li {
                strong { +"Data Access" }
                +" - Retrieving data from the database using queries"
            }
            li {
                strong { +"Data Persistence" }
                +" - Saving new data and updating existing records"
            }
            li {
                strong { +"Data Mapping" }
                +" - Converting between database records and domain objects"
            }
            li {
                strong { +"Query Optimization" }
                +" - Writing efficient database queries"
            }
            li {
                strong { +"Transaction Handling" }
                +" - Managing database transactions at the data level"
            }
            li {
                strong { +"Data Validation" }
                +" - Basic data integrity checks (constraints, foreign keys)"
            }
        }
        p {
            highlight("What repositories should NOT do:")
        }
        ul {
            li { +"Business logic implementation" }
            li { +"Authorization decisions" }
            li { +"Complex data transformations" }
            li { +"External service integrations" }
        }
    }
)

object DataAccessFrameworksSlide : Slide(
    header = "Data Access Frameworks",
    summary = {
        +"There is a variety of ways and frameworks you can use to implement the repository layer."
    },
    content = {
        ul {
            li {
                strong { highlight("Exposed") }
                br()
                +"Kotlin SQL framework that provides a DSL for building SQL queries and mapping results to Kotlin objects."
                br()
                br()
            }
            li {
                strong { highlight("JOOQ") }
                br()
                +"Java SQL framework that provides a fluent API for building SQL queries and mapping results to Java objects. "
                +"It supports generating code from database schemas, allowing for typesafe SQL queries."
                br()
                br()
            }
            li {
                strong { highlight("SQLDelight") }
                br()
                +"Kotlin Multiplatform library that generates typesafe Kotlin APIs from SQL statements."
                br()
                br()
            }
            li {
                strong { highlight("Raw SQL") }
                br()
                +"You can use raw SQL queries to interact with the database directly. "
                +"I would advise this only for simple applications or for specific cases where you need full control over the SQL queries."
                br()
                br()
            }
            li {
                strong { highlight("and more ..") }
                br()
                br()
            }
        }
    }
)

object RepositoryPatternBenefitsSlide : Slide(
    header = "Repository Pattern Benefits",
    summary = {
        +"The repository pattern provides several important benefits for application architecture and maintainability."
    },
    content = {
        p {
            +"Key benefits of using the repository pattern:"
        }
        ul {
            li {
                strong { +"Separation of Concerns" }
                +" - Clear separation between business logic and data access logic"
            }
            li {
                strong { +"Testability" }
                +" - Easy to mock repositories for unit testing"
            }
            li {
                strong { +"Flexibility" }
                +" - Can switch between different data sources without changing business logic"
            }
            li {
                strong { +"Maintainability" }
                +" - Centralized data access code that's easier to maintain"
            }
            li {
                strong { +"Reusability" }
                +" - Repository methods can be used across multiple services"
            }
            li {
                strong { +"Type Safety" }
                +" - Strong typing with domain models and DTOs"
            }
        }
        p {
            +"The repository pattern also enables:"
        }
        ul {
            li { +"Dependency injection and inversion of control" }
            li { +"Consistent error handling for data access operations" }
            li { +"Centralized query optimization and performance tuning" }
            li { +"Easy migration between different database technologies" }
        }
    }
)
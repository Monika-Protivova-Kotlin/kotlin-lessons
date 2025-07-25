package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import kotlinx.html.*

object ServiceLayerTopic : Topic(
    title = "Service Layer",
    slides = listOf(
        ServiceLayerIntroSlide,
        ServiceLayerResponsibilitiesSlide,
        BusinessLogicHandlingSlide,
        DomainModelMappingSlide,
        TransactionManagementSlide
    )
)

object ServiceLayerIntroSlide : Slide(
    header = "Service Layer",
    summary = {
        +"The Service Layer is a logical layer that sits between the controller and the data access layer. "
        +"It encapsulates business logic and orchestrates various operations."
    },
    content = {
        p {
            +"The Service Layer acts as a bridge between the presentation layer (controllers) and the data access layer (repositories). "
            +"It's where the core business logic resides and where complex operations are coordinated."
        }
        p {
            +"Key characteristics of the Service Layer:"
        }
        ul {
            li { +"Contains business logic and rules" }
            li { +"Orchestrates multiple data access operations" }
            li { +"Provides transaction boundaries" }
            li { +"Handles data transformation and validation" }
            li { +"Implements authorization and security checks" }
        }
    }
)

object ServiceLayerResponsibilitiesSlide : Slide(
    header = "Service Layer Responsibilities",
    content = {
        p {
            +"The Service Layer has several key responsibilities:"
        }
        ul {
            li {
                strong { +"Business Logic Handling" }
                +" - Implements complex business rules and workflows"
            }
            li {
                strong { +"Domain Model Mapping" }
                +" - Converts between DTOs and domain models"
            }
            li {
                strong { +"Transaction Management" }
                +" - Ensures data consistency across multiple operations"
            }
            li {
                strong { +"Authorization" }
                +" - Enforces access control and permissions"
            }
            li {
                strong { +"Data Validation" }
                +" - Validates business rules beyond basic input validation"
            }
            li {
                strong { +"Error Handling" }
                +" - Manages exceptions and provides meaningful error messages"
            }
        }
    }
)

object BusinessLogicHandlingSlide : Slide(
    header = "Business Logic Handling",
    summary = {
        +"The Service Layer is responsible for implementing complex business rules and workflows that span multiple entities or operations."
    },
    content = {
        p {
            +"Business logic includes:"
        }
        ul {
            li { +"Validation of business rules" }
            li { +"Complex calculations and computations" }
            li { +"Workflow orchestration" }
            li { +"Integration with external services" }
            li { +"State management and transitions" }
        }
        p {
            +"Example: An order processing service might:"
        }
        ul {
            li { +"Validate inventory availability" }
            li { +"Calculate discounts and taxes" }
            li { +"Process payment through external gateway" }
            li { +"Update inventory levels" }
            li { +"Send confirmation emails" }
            li { +"Log audit information" }
        }
    }
)

object DomainModelMappingSlide : Slide(
    header = "Domain Model Mapping",
    summary = {
        +"The Service Layer handles the conversion between Data Transfer Objects (DTOs) and domain models, "
        +"ensuring proper separation of concerns between layers."
    },
    content = {
        p {
            +"Mapping responsibilities include:"
        }
        ul {
            li {
                strong { +"DTO to Domain Model" }
                +" - Converting incoming requests to internal domain objects"
            }
            li {
                strong { +"Domain Model to DTO" }
                +" - Converting internal objects to response formats"
            }
            li {
                strong { +"Data Enrichment" }
                +" - Adding computed fields or related data"
            }
            li {
                strong { +"Data Filtering" }
                +" - Removing sensitive or unnecessary information"
            }
        }
        p {
            +"This mapping ensures that:"
        }
        ul {
            li { +"Internal domain models remain clean and focused" }
            li { +"External interfaces can evolve independently" }
            li { +"Security is maintained by controlling data exposure" }
            li { +"Performance is optimized by transferring only necessary data" }
        }
    }
)

object TransactionManagementSlide : Slide(
    header = "Transaction Management",
    summary = {
        +"The Service Layer defines transaction boundaries to ensure data consistency across multiple operations."
    },
    content = {
        p {
            +"Transaction management ensures:"
        }
        ul {
            li {
                strong { +"ACID Properties" }
                +" - Atomicity, Consistency, Isolation, and Durability"
            }
            li {
                strong { +"Data Consistency" }
                +" - All related operations succeed or fail together"
            }
            li {
                strong { +"Rollback Capability" }
                +" - Ability to undo changes when errors occur"
            }
            li {
                strong { +"Isolation Levels" }
                +" - Control over concurrent access to data"
            }
        }
        p {
            +"Common transaction patterns in services:"
        }
        ul {
            li { +"Single service method = Single transaction" }
            li { +"Nested transactions for complex workflows" }
            li { +"Distributed transactions for microservices" }
            li { +"Compensating actions for saga patterns" }
        }
    }
)
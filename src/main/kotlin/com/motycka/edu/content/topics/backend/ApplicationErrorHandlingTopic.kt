package com.motycka.edu.content.topics.backend

import com.motycka.edu.builders.codeOrderedList
import com.motycka.edu.builders.highlightOrderedList
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import kotlinx.html.*

object ApplicationErrorHandlingTopic : Topic(
    title = "Application Error Handling",
    slides = listOf(
        ApplicationErrorsIntroSlide,
        ValidationFunctionsSlide,
    )
)

object ValidationFunctionsSlide : Slide(
    header = "Built-in Functions",
    summary = {
        +"Kotlin comes with a set of convenience functions to help us validate inputs and application state and raise errors."
    },
    content = {
        p { +"Here are few examples of validation functions:" }
        codeOrderedList(
            "require(conditon) { \"Message if condition not met\" }"  to {
                +"Throws IllegalArgumentException with provided message if the condition is false."
            },
            "requireNotNull(value) { \"Message if value is null\" }" to {
                +"Throws IllegalArgumentException with provided message if the value is null."
            },
            "check(conditon) { \"Message if condition not met\" }" to {
                +"Throws IllegalStateException with provided message if the condition is false."
            },
            "checkNotNull(value) { \"Message if value is null\" " to {
                +"Throws IllegalStateException with provided message if the value is null."
            },
            "error(\"Message\")" to {
                +"Throws IllegalStateException with provided message unconditionally."
            }
        )
    }
)

object ApplicationErrorsIntroSlide : Slide(
    header = "Application Errors - General Concepts",
    summary = {
        +"Applications can encounter various types of errors during their execution. "
        +"These errors can be due to user input, system state, or business logic violations."
    },
    content = {
        p {
            +"So far, we have not paid great attention to the errors that can occur in our applications."
        }
//        p {
//            +"We know how to respond with different HTTP status codes based on expected service returned values, "
//            +"but we didn't deal with error states that can occur in our applications."
//        }
        p {
            +"Under normal operation, most application calls will not result in an exception. "
            +"But there are valid reasons why an exception might be thrown:"
        }
        highlightOrderedList(
            "Input validation" to {
                +"For example, we may want to validate user inputs, such as valid JSON request body, or valid query parameter values. "
                +"Example: non empty list, string, valid email address, etc."
            },
            "State validation" to {
                +"For example, we may want to validate the state of the application, such as whether a user is logged in, or whether a resource exists."
            },
            "Business logic validation" to {
                +"For example, we may want to validate the business logic of the application, such as whether a user has permission to perform an action, or whether a resource is available."
            }
        )
        p {
            strong {
                +"You also need to account for unexpected errors, such as network failures, database errors, or other unforeseen issues, as well as third-party (library, service) failures."
            }
        }
    }
)

object CustomExceptionsSlide : Slide(
    header = "Defining Custom Exceptions",
    summary = {
        +"It is often a good idea to define custom exceptions for specific error conditions in your application."
    },
    content = {
        p {
            +"Defining custom exceptions allows you to provide more meaningful error messages and handle specific error conditions in a more granular way. "
            +"It also allows you to treat same class of errors consistently across your application."
        }
        p { +"Here are few examples of programmer-defined exceptions:" }
        codeOrderedList(
            "InvalidCredentialsException" to {
                +"Thrown when a user fails to authenticate."
            },
            "UnauthorizedAccessException" to {
                +"Thrown when a user is not authorized to perform an action."
            },
            "ResourceNotFoundException" to {
                +"Thrown when a requested resource is not found."
            },
            "InvalidInputException" to {
                +"Thrown when the input provided by the user is invalid."
            },
            "ConflictException" to {
                +"Thrown when the action would result in a conflict (duplicate)."
            }
        )
    }
)


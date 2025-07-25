package com.motycka.edu.content.topics.introductory

import com.motycka.edu.model.highlight
import com.motycka.edu.builders.*
import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import kotlinx.html.blockQuote
import kotlinx.html.p
import com.motycka.edu.model.highlight

object ResponsibilitiesOfBackendTopic : Topic(
    title = "Responsibilities of Backend",
    slides = listOf(
        ResponsibilitiesOfBackendSlide,
        BusinessLogicSlide,
        DataPersistenceSlide,
        MultiUserCoordinationSlide,
        SecuritySlide,
        InterfacingWithOtherServicesSlide,
        PerformanceAndResourceManagementSlide
    )
)

object ResponsibilitiesOfBackendSlide : Slide(
    header = "Responsibilities of Backend",
    summary = { +"What does the backend do?" },
    content = {
        highlightNumberedList(
            startFrom = 1,
            "Business Logic" to { +"Enforcing rules and workflows (e.g., checking if a user can book a ticket)." },
            "Data Persistence" to { +"Storing and retrieving user or application data (e.g., accounts, messages, files, transactions)." },
            "Multi-user Coordination" to { +"Managing interactions between different users and devices." },
            "Security" to { +"Safely managing sensitive operations (e.g., password handling, access control)." },
            "Interfacing with Other Services" to { +"Talking to external APIs (e.g., payment gateways, recommendation engines)." },
            "Performance and Resource Management" to { +"Offloading heavy computation or long-running processes." },
        )
    }
)

object BusinessLogicSlide : Slide(
    header = "Business Logic",
    summary = { +"Business Logic is a set of rules, calculations and workflows that determine how data is processed to fulfill the business requirements." },
    content = {
        p {
            highlight("Business Logic")
            + "refers to the fact that this code is specific to the business domain (e.g., e-commerce, healthcare, finance, entertainment, ...)."
        }
        p {
            +"It is the part of the application that defines how the application behaves and operates to fulfill the business requirements (to address customer needs and problems)."
        }
        p {
            +"It may include rules for processing data, calculations, workflows based on user actions, and interactions with other systems or services."
        }
    }

)

object DataPersistenceSlide : Slide(
    header = "Data Persistence",
    summary = { +"Data persistence is the ability to store, retrieve and update application data." },
    content = {
        p {
            +"Typically, this is done using a database which itself is an external resource that the backend interacts with."
        }
        p {
            +"The backend is responsible for managing the connection to the database, executing queries, and ensuring data integrity in accordance the business rules."
        }
    }
)

object MultiUserCoordinationSlide : Slide(
    header = "Multi-user Coordination",
    summary = { +"Multi-user coordination is the ability to manage interactions between different users and devices." },
    content = {
        p {
            +"This is especially important in applications that allow multiple users to interact with each other, such as social networks, messaging apps, or collaborative tools."
        }
        p {
            +"The backend is responsible for managing user sessions, handling concurrent requests, and ensuring that users can interact with each other in a consistent and reliable way."
        }
    }
)

object SecuritySlide : Slide(
    header = "Security",
    summary = { +"Security involves protecting sensitive data and operations." },
    content = {
        blockQuote { +"Security is a critical aspect of backend development." }
        p {
            +"The backend is responsible for implementing security measures such as user authentication, authorization, data encryption, and protection against common vulnerabilities (e.g., SQL injection, cross-site scripting)."
        }
        p {
            +"User "
            highlight("authentication")
            +" is the process of verifying the identity of a user."
        }
        p {
            +"User "
            highlight("authorization")
            +" is the process of determining what actions a user is allowed to perform."
        }
    }
)
object InterfacingWithOtherServicesSlide : Slide(
    header = "Interfacing with Other Services",
    summary = { +"Interfacing with other services allows the backend to extend its functionality by third party integrations." },
    content = {
        p {
            +"Quite often, the backend needs to interact with external services or APIs to provide additional functionality, such as payment processing, email notifications, or data enrichment."
        }
        p {
            +"The backend is responsible for managing these integrations, handling API requests and responses, and ensuring that the data exchanged with external services is consistent and secure."
        }
    }
)

object PerformanceAndResourceManagementSlide : Slide(
    header = "Performance and Resource Management",
    summary = {
        +"Backend applications often need to handle large volumes of data, process complex calculations, or perform long-running tasks."
    },
    content = {
        p {
            +"The backend is responsible for optimizing performance, managing resources efficiently, and ensuring that the application can scale to handle increased the load without compromising user experience."
        }
        p {
            +"This may involve techniques such as caching, load balancing, asynchronous processing, and using distributed systems."
        }
        p {
            +"A well-designed backend should be able to be scaled horizontally (adding more servers) or vertically (upgrading existing servers)"
        }
        p {
            +"Also, the backend should be designed to be resilient and fault-tolerant, so that it can recover from failures quickly and without losing data or disrupting user experience."
        }
        p {
            +"Backend should also provide metrics and logging to monitor performance, detect issues, and analyze usage patterns."
        }
    }
)


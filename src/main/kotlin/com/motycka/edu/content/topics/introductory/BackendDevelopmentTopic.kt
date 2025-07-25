package com.motycka.edu.content.topics.introductory

import com.motycka.edu.builders.*
import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import kotlinx.html.blockQuote
import kotlinx.html.li
import kotlinx.html.ol
import kotlinx.html.p
import kotlinx.html.table
import kotlinx.html.tbody
import kotlinx.html.td
import kotlinx.html.thead
import kotlinx.html.tr
import kotlinx.html.ul

object BackendDevelopmentTopic : Topic(
    title = "Backend Development",
    slides = listOf(
        WhatIsBackend,
        WhyApplicationsNeedABackend,
        WhatKindsOfApplicationsHaveABackend
    )
)

object WhatIsBackend : Slide(
    header = "What is \"Backend\"?",
    summary = {
        +"Backend commonly refers to the server-side of an application, which is a process that runs on a server and handles core logic, data management, user authentication, business rules, and integrations with other systems."
    },
    content = {
        blockQuote { +"It handles things that can’t, or shouldn’t, happen in the user’s browser or app directly." }
        p { +"A typical backend of an application consists of several components that work together to provide the functionality." }
        highlightOrderedList(
            "Server" to { +"The machine or cloud service that runs the backend code." },
            "Application Logic" to { +"The code that implements the business rules and processes." },
            "Database" to { +"Where data is stored persistently (e.g., user accounts, application state)." },
            "APIs" to { +"Interfaces for clients (web, mobile, etc.) to interact with the backend." },
            "Security" to { +"Mechanisms for authentication and authorization and data protection." },
        )
    }
)

object WhyApplicationsNeedABackend : Slide(
    header = "Why Applications Need a Backend?",
    summary = {
        +"Most applications that require user accounts, data storage or real-time interactions will have a backend."
    },
    content = {
        p { +"What is handled by the backend?" }
        ol {
            li {
                +"For multi-platform applications, the backend provides a common foundation that all clients (web, mobile, desktop) can rely on for consistent behavior and data access."
            }
            li {
                +"For multi-user applications, the backend allows user interactions, roles, data sharing, and real-time updates."
            }
            li {
                +"Having a backend allows applications to scale, manage resources and maintain security across different devices and users."
            }
        }
        p { +"Some applications may not need a backend:" }
        ul {
            li { +"Simple static websites" }
            li { +"Standalone tools, calculators or utilities" }
            li { +"Local-only games" }
        }
        p { +"But even these can evolve to require one — for analytics, syncing, personalization, or collaboration." }
    }
)

object WhatKindsOfApplicationsHaveABackend : Slide(
    header = "What Kinds of Applications Have a Backend?",
    summary = {
        + "And what do they use it for?"
    },
    content = {
        table {
            thead {
                tr {
                    td { +"Application Type" }
                    td { +"Example" }
                    td { +"Backend Role" }
                }
            }
            tbody {
                tr {
                    td { +"Web Apps" }
                    td { +"E-commerce site, dashboard" }
                    td { +"Data APIs, authentication, payment handling" }
                }
                tr {
                    td { +"Mobile Apps" }
                    td { +"Messaging, social, travel apps" }
                    td { +"Sync data, store media, push notifications" }
                }
                tr {
                    td { +"Desktop Apps" }
                    td { +"Collaboration tools, IDEs" }
                    td { +"Real-time sync, cloud storage, user accounts" }
                }
                tr {
                    td { +"IoT / Smart Devices" }
                    td { +"Smart lights, wearables" }
                    td { +"Device management, remote control, firmware updates" }
                }
                tr {
                    td { +"Games" }
                    td { +"Online multiplayer games" }
                    td { +"Matchmaking, scoring, anti-cheat, player data" }
                }
                tr {
                    td { +"APIs / Developer Tools" }
                    td { +"Stripe, Firebase, OpenAI" }
                    td { +"Pure backend — no UI, only serves data or actions to other apps" }
                }
            }
        }
    }
)

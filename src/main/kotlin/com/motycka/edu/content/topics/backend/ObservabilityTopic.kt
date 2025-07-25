package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import kotlinx.html.*

object ObservabilityTopic : Topic(
    title = "Observability and Monitoring",
    slides = listOf(
        ObservabilitySlide,
        MonitoringSlide
    )
)

object ObservabilitySlide : Slide(
    header = "Observability",
    summary = {
        +"Observability is the ability to measure the internal states of a system by examining its outputs."
    },
    content = {
        p {
            +"The three pillars of observability are:"
        }
        ul {
            li { strong { +"Logs" }; +" - Records of events that happened in your application" }
            li { strong { +"Metrics" }; +" - Numerical data about your application's performance" }
            li { strong { +"Traces" }; +" - Records of the path taken by requests through your application" }
        }
        p {
            +"These help you understand what is happening in your production systems and debug issues when they occur."
        }
    }
)

object MonitoringSlide : Slide(
    header = "Monitoring",
    summary = {
        +"Monitoring is the practice of collecting, processing, and analyzing observability data to understand system health."
    },
    content = {
        p {
            +"Key aspects of monitoring include:"
        }
        ul {
            li { +"Setting up dashboards to visualize system health" }
            li { +"Creating alerts for when things go wrong" }
            li { +"Tracking SLIs (Service Level Indicators) and SLOs (Service Level Objectives)" }
            li { +"Performance monitoring and capacity planning" }
        }
        p {
            +"Good monitoring helps you catch issues before they affect users and helps you make data-driven decisions about your system."
        }
    }
)
package com.motycka.edu.content.topics.backend.deployment

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object MetricsAndMonitoringTopic : Topic(
    title = "Metrics & Monitoring",
    slides = listOf(
        MonitoringIntroSlide,
        SpringBootActuatorSlide,
        MetricsTypesSlide,
        MonitoringToolsSlide
    )
)

object MonitoringIntroSlide : Slide(
    header = "Application Monitoring",
    summary = {
        +"Monitoring provides real-time visibility into your application's health, performance, and behavior"
    },
    content = {
        p {
            +"While logs tell you what happened in the past, metrics and monitoring tell you what's happening right now. "
            +"They help you detect issues before they impact users and understand system behavior over time."
        }

        p { strong { +"Why Monitor?" } }
        ul {
            li {
                strong { +"Detect Issues Early" }
                +" - Catch problems before users report them"
            }
            li {
                strong { +"Understand Performance" }
                +" - Track response times, throughput, error rates"
            }
            li {
                strong { +"Capacity Planning" }
                +" - Know when to scale resources"
            }
            li {
                strong { +"SLA Compliance" }
                +" - Ensure you meet service level agreements"
            }
            li {
                strong { +"Troubleshooting" }
                +" - Diagnose performance bottlenecks"
            }
        }

        p { highlight("Observability Three Pillars:") }
        ol {
            li { strong { +"Logs" }; +" - What happened (events)" }
            li { strong { +"Metrics" }; +" - How much/how many (measurements)" }
            li { strong { +"Traces" }; +" - Request flow across services" }
        }
    }
)

object SpringBootActuatorSlide : Slide(
    header = "Spring Boot Actuator",
    summary = {
        +"Spring Boot Actuator provides production-ready features for monitoring and managing your application"
    },
    content = {
        p { strong { +"Adding Actuator:" } }
        pre {
            +"""
            // build.gradle.kts
            dependencies {
                implementation("org.springframework.boot:spring-boot-starter-actuator")
                implementation("io.micrometer:micrometer-registry-prometheus")
            }
            """.trimIndent()
        }

        p { strong { +"Configuration:" } }
        pre {
            +"""
            # application.yml
            management:
              endpoints:
                web:
                  exposure:
                    include: health,info,metrics,prometheus
              endpoint:
                health:
                  show-details: always
              metrics:
                tags:
                  application: ${'$'}{spring.application.name}
            """.trimIndent()
        }

        p { highlight("Built-in Endpoints:") }
        ul {
            li { inlineCode("/actuator/health"); +" - Application health status" }
            li { inlineCode("/actuator/info"); +" - Application information" }
            li { inlineCode("/actuator/metrics"); +" - Application metrics" }
            li { inlineCode("/actuator/prometheus"); +" - Prometheus-formatted metrics" }
            li { inlineCode("/actuator/env"); +" - Environment properties" }
            li { inlineCode("/actuator/loggers"); +" - Logger configuration" }
        }
    }
)

object MetricsTypesSlide : Slide(
    header = "Types of Metrics",
    summary = {
        +"Understanding different metric types and when to use them"
    },
    content = {
        p { highlight("Common Metric Types:") }

        ul {
            li {
                strong { +"Counter" }
                +" - Cumulative value that only increases"
                br()
                +"Examples: Total requests, total errors, total orders"
            }
            li {
                strong { +"Gauge" }
                +" - Current value that can go up or down"
                br()
                +"Examples: Current memory usage, active connections, queue size"
            }
            li {
                strong { +"Histogram" }
                +" - Distribution of values over time"
                br()
                +"Examples: Request duration, response size"
            }
            li {
                strong { +"Timer" }
                +" - Combines duration and rate of events"
                br()
                +"Examples: API endpoint response times"
            }
        }

        p { strong { +"Custom Metrics in Spring Boot:" } }
        kotlinPlayground(
            code = """
                @Service
                class OrderService(
                    private val meterRegistry: MeterRegistry
                ) {
                    private val ordersCounter = meterRegistry.counter("orders.created.total")
                    private val orderValueGauge = meterRegistry.gauge("orders.current.value", 0.0)!!

                    fun createOrder(order: Order) {
                        // Increment counter
                        ordersCounter.increment()

                        // Time the operation
                        meterRegistry.timer("orders.create.duration").record {
                            processOrder(order)
                        }

                        // Update gauge
                        orderValueGauge.set(getCurrentOrderValue())
                    }
                }
            """.trimIndent(),
            executable = false
        )
    },
    fontSize = "75%"
)

object MonitoringToolsSlide : Slide(
    header = "Monitoring Tools & Platforms",
    summary = {
        +"Popular tools for collecting, storing, and visualizing metrics"
    },
    content = {
        p { highlight("Metrics Collection & Storage:") }
        ul {
            li {
                strong { +"Prometheus" }
                +" - Open-source metrics collection and time-series database"
            }
            li {
                strong { +"Micrometer" }
                +" - Vendor-neutral metrics facade for Spring Boot"
            }
            li {
                strong { +"InfluxDB" }
                +" - Time-series database optimized for metrics"
            }
        }

        p { highlight("Visualization & Dashboards:") }
        ul {
            li {
                strong { +"Grafana" }
                +" - Create beautiful dashboards from metrics data"
            }
            li {
                strong { +"Kibana" }
                +" - Visualize logs and metrics from Elasticsearch"
            }
        }

        p { highlight("All-in-One Platforms:") }
        ul {
            li {
                strong { +"Datadog" }
                +" - Full-stack observability platform"
            }
            li {
                strong { +"New Relic" }
                +" - Application performance monitoring (APM)"
            }
            li {
                strong { +"Dynatrace" }
                +" - AI-powered observability"
            }
            li {
                strong { +"Elastic Stack (ELK)" }
                +" - Elasticsearch, Logstash, Kibana for logs and metrics"
            }
        }

        p { highlight("Key Metrics to Monitor:") }
        ul {
            li { +"Request rate and response time" }
            li { +"Error rate and types" }
            li { +"CPU and memory usage" }
            li { +"Database query performance" }
            li { +"External API latency" }
            li { +"Queue length and processing time" }
        }
    }
)

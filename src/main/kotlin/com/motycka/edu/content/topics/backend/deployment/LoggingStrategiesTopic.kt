package com.motycka.edu.content.topics.backend.deployment

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object LoggingStrategiesTopic : Topic(
    title = "Logging Strategies",
    slides = listOf(
        LoggingImportanceSlide,
        LogLevelsSlide,
        StructuredLoggingSlide,
        LoggingBestPracticesSlide
    )
)

object LoggingImportanceSlide : Slide(
    header = "Why Logging Matters",
    summary = {
        +"Logs are essential for debugging, monitoring, and understanding application behavior in production"
    },
    content = {
        p {
            +"Logging provides visibility into your application's runtime behavior. "
            +"Good logging practices help you diagnose issues, track performance, and audit system activity."
        }

        p { strong { +"What to Log:" } }
        ul {
            li {
                strong { +"Application Events" }
                +" - Startup, shutdown, configuration changes"
            }
            li {
                strong { +"Business Logic" }
                +" - Important business transactions and decisions"
            }
            li {
                strong { +"Errors and Exceptions" }
                +" - All errors with context and stack traces"
            }
            li {
                strong { +"Performance Metrics" }
                +" - Slow operations, query times, external API calls"
            }
            li {
                strong { +"Security Events" }
                +" - Authentication attempts, authorization failures, suspicious activity"
            }
            li {
                strong { +"Integration Points" }
                +" - External service calls, database queries, message queue operations"
            }
        }

        p { strong { +"What NOT to Log:" } }
        ul {
            li { +"❌ Passwords or credentials" }
            li { +"❌ Personal Identifiable Information (PII)" }
            li { +"❌ Credit card or payment information" }
            li { +"❌ Session tokens or API keys" }
        }
    }
)

object LogLevelsSlide : Slide(
    header = "Log Levels",
    summary = {
        +"Use appropriate log levels to categorize messages by severity"
    },
    content = {
        p { highlight("Standard Log Levels (from most to least severe):") }

        ul {
            li {
                strong { +"ERROR" }
                +" - Something went wrong that prevents normal execution"
                pre { +"logger.error(\"Failed to process payment: {}\", error.message)" }
            }
            li {
                strong { +"WARN" }
                +" - Something unexpected happened, but the application can continue"
                pre { +"logger.warn(\"API rate limit approaching: {} requests remaining\", remaining)" }
            }
            li {
                strong { +"INFO" }
                +" - Important business events and milestones"
                pre { +"logger.info(\"User {} logged in successfully\", userId)" }
            }
            li {
                strong { +"DEBUG" }
                +" - Detailed information useful for debugging"
                pre { +"logger.debug(\"Executing SQL query: {}\", sql)" }
            }
            li {
                strong { +"TRACE" }
                +" - Very detailed information, typically only enabled temporarily"
                pre { +"logger.trace(\"Entering method processOrder with params: {}\", params)" }
            }
        }

        blockQuote {
            +"💡 In production, typically set log level to INFO or WARN. "
            +"Use DEBUG and TRACE only when troubleshooting specific issues."
        }
    },
    fontSize = "80%"
)

object StructuredLoggingSlide : Slide(
    header = "Structured Logging",
    summary = {
        +"Use structured logging for better log analysis and monitoring"
    },
    content = {
        p {
            +"Structured logging outputs logs in a machine-readable format (usually JSON), making it easier to "
            +"search, filter, and analyze logs in log management systems."
        }

        p { strong { +"Traditional vs Structured Logging:" } }
        kotlinPlayground(
            code = """
                // Traditional logging (text)
                logger.info("User john@example.com created order #12345 for ${'$'}99.99")

                // Structured logging (JSON-friendly)
                logger.info(
                    "Order created",
                    kv("userId", "john@example.com"),
                    kv("orderId", 12345),
                    kv("amount", 99.99),
                    kv("currency", "USD")
                )
            """.trimIndent(),
            executable = false
        )

        p { strong { +"JSON Output:" } }
        pre {
            +"""
            {
              "timestamp": "2024-03-15T10:30:45.123Z",
              "level": "INFO",
              "message": "Order created",
              "userId": "john@example.com",
              "orderId": 12345,
              "amount": 99.99,
              "currency": "USD",
              "service": "order-service",
              "traceId": "abc-123-def-456"
            }
            """.trimIndent()
        }

        p { highlight("Benefits:") }
        ul {
            li { +"Easy to parse and analyze" }
            li { +"Search by specific fields" }
            li { +"Aggregate and visualize metrics" }
            li { +"Works well with log management tools (ELK, Splunk, Datadog)" }
        }
    },
    fontSize = "75%"
)

object LoggingBestPracticesSlide : Slide(
    header = "Logging Best Practices",
    content = {
        p { highlight("Do's:") }
        ul {
            li { +"✓ Use meaningful, descriptive messages" }
            li { +"✓ Include context (user ID, request ID, trace ID)" }
            li { +"✓ Log at appropriate levels" }
            li { +"✓ Use parameterized logging (avoid string concatenation)" }
            li { +"✓ Include timestamps and service name" }
            li { +"✓ Log exceptions with full stack traces" }
            li { +"✓ Use correlation IDs to trace requests across services" }
        }

        p { highlight("Don'ts:") }
        ul {
            li { +"✗ Don't log in loops without throttling" }
            li { +"✗ Don't log sensitive information" }
            li { +"✗ Don't use System.out.println() or printStackTrace()" }
            li { +"✗ Don't log too much (impacts performance)" }
            li { +"✗ Don't log too little (makes debugging impossible)" }
        }

        p { strong { +"Example:" } }
        kotlinPlayground(
            code = """
                @Service
                class OrderService(private val logger: Logger = LoggerFactory.getLogger(OrderService::class.java)) {

                    fun createOrder(userId: String, items: List<Item>): Order {
                        logger.info("Creating order for user: {}", userId)

                        try {
                            val order = processOrder(userId, items)
                            logger.info("Order created successfully: orderId={}, amount={}",
                                order.id, order.totalAmount)
                            return order
                        } catch (e: PaymentException) {
                            logger.error("Payment failed for user: {}", userId, e)
                            throw e
                        }
                    }
                }
            """.trimIndent(),
            executable = false
        )
    },
    fontSize = "75%"
)

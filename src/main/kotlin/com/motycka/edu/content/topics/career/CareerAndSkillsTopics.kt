package com.motycka.edu.content.topics.career

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.twoColumns
import kotlinx.html.*

object SkillsThatMatterMostTopic : Topic(
    title = "Skills That Matter Most",
    slides = listOf(
        ArchitecturalThinkingSlide,
        TestingAndQualitySlide,
        DebuggingAndProblemSolvingSlide,
        ProductionReadinessSlide
    )
)

object ArchitecturalThinkingSlide : Slide(
    header = "Architectural Thinking",
    summary = {
        +"The ability to design systems and make architectural decisions is becoming more valuable, not less."
    },
    content = {
        p {
            strong { +"Why architecture matters more than ever:" }
        }
        ul {
            li {
                strong { +"AI generates components, not systems" }
                br()
                +"It can create individual classes but struggles with overall system design"
            }
            li {
                strong { +"Trade-offs require context" }
                br()
                +"Should this be synchronous or async? Monolith or microservices? AI doesn't know your constraints"
            }
            li {
                strong { +"Evolution matters" }
                br()
                +"Systems must grow and change. Architecture determines how painful that will be"
            }
            li {
                strong { +"Integration complexity" }
                br()
                +"How services communicate, where boundaries are, what depends on what"
            }
        }
        p {
            highlight("This course gives you architectural thinking:")
        }
        ul {
            li { +"Layered architecture (Controller → Service → Repository)" }
            li { +"Separation of concerns (DTOs vs Entities)" }
            li { +"Dependency management (Dependency Injection)" }
            li { +"Data access patterns (JDBC vs JPA vs JOOQ)" }
            li { +"Transaction boundaries and consistency" }
        }
        blockQuote {
            +"AI can build the bricks. You design the building."
        }
    }
)

object TestingAndQualitySlide : Slide(
    header = "Testing and Quality",
    summary = {
        +"Writing tests becomes even more critical when AI generates implementation code."
    },
    content = {
        p {
            strong { +"Why testing matters with AI:" }
        }
        ul {
            li {
                strong { +"Verification of correctness" }
                br()
                +"How do you know AI-generated code actually works? Tests."
            }
            li {
                strong { +"Edge case discovery" }
                br()
                +"AI often misses edge cases. Good tests catch them."
            }
            li {
                strong { +"Documentation of intent" }
                br()
                +"Tests show what code should do, not just what it does"
            }
            li {
                strong { +"Refactoring safety" }
                br()
                +"Tests let you safely improve AI-generated code"
            }
            li {
                strong { +"Integration validation" }
                br()
                +"Does this component work with the rest of your system?"
            }
        }
        p {
            highlight("Testing skills you've learned:")
        }
        ul {
            li { +"Unit testing with JUnit and Kotest" }
            li { +"Mocking with MockK" }
            li { +"Controller testing with MockMvc" }
            li { +"Service and repository testing patterns" }
            li { +"Test organization and best practices" }
        }
        p {
            +"These skills let you confidently validate any code, regardless of its source."
        }
    }
)

object DebuggingAndProblemSolvingSlide : Slide(
    header = "Debugging and Problem Solving",
    summary = {
        +"When things go wrong (and they will), AI can suggest possibilities but you must investigate and verify."
    },
    content = {
        p {
            strong { +"Debugging in the AI era:" }
        }
        twoColumns(
            left = {
                p { strong { +"AI can help with:" } }
                ul {
                    li { +"Suggesting possible causes" }
                    li { +"Explaining error messages" }
                    li { +"Proposing solutions to try" }
                    li { +"Finding similar issues" }
                }
            },
            right = {
                p { strong { +"You must handle:" } }
                ul {
                    li { +"Understanding your system state" }
                    li { +"Reproducing the issue" }
                    li { +"Verifying the actual cause" }
                    li { +"Testing the fix works" }
                    li { +"Ensuring no side effects" }
                }
            }
        )
        p {
            strong { +"Complex debugging scenarios AI struggles with:" }
        }
        ul {
            li { +"Race conditions and concurrency issues" }
            li { +"Production-only bugs with partial information" }
            li { +"Performance degradation in specific scenarios" }
            li { +"Integration issues across multiple services" }
            li { +"Security vulnerabilities and their implications" }
        }
        p {
            +"Your ability to systematically investigate, form hypotheses, and verify solutions "
            +"is what makes you valuable when problems occur."
        }
    }
)

object ProductionReadinessSlide : Slide(
    header = "Production Readiness",
    summary = {
        +"Getting code to work in development is one thing. Making it production-ready is where experience matters."
    },
    content = {
        p {
            strong { +"What makes code production-ready?" }
        }
        ul {
            li {
                strong { +"Error handling" }
                br()
                +"What happens when things go wrong? How do you recover?"
            }
            li {
                strong { +"Security" }
                br()
                +"Authentication, authorization, input validation, SQL injection prevention"
            }
            li {
                strong { +"Performance" }
                br()
                +"Database query optimization, caching, resource management"
            }
            li {
                strong { +"Observability" }
                br()
                +"Logging, metrics, monitoring - can you debug issues in production?"
            }
            li {
                strong { +"Reliability" }
                br()
                +"Transaction handling, data consistency, graceful degradation"
            }
            li {
                strong { +"Maintainability" }
                br()
                +"Clear code, good naming, reasonable complexity, documentation"
            }
        }
        p {
            highlight("AI-generated code often lacks these considerations.")
        }
        p {
            +"Your knowledge of production concerns - learned through this course and experience - "
            +"is what transforms working code into reliable systems."
        }
    }
)

object DeepUnderstandingVsSurfaceKnowledgeTopic : Topic(
    title = "Deep Understanding vs Surface Knowledge",
    slides = listOf(
        WhyUnderstandingMattersSlide,
        BuildingDeepKnowledgeSlide,
        CompoundingFundamentalsSlide
    )
)

object WhyUnderstandingMattersSlide : Slide(
    header = "Why Deep Understanding Matters",
    summary = {
        +"In an age where AI can generate code, your understanding becomes your competitive advantage."
    },
    content = {
        p {
            strong { +"The difference between using AI and being replaceable by AI:" }
        }
        twoColumns(
            left = {
                p { strong { +"Surface Knowledge:" } }
                ul {
                    li { +"\"AI says to do it this way\"" }
                    li { +"Can't explain why code works" }
                    li { +"Struggles when AI suggestions fail" }
                    li { +"Unable to evaluate trade-offs" }
                    li { +"Copies without understanding" }
                    li { +"Can't debug unexpected issues" }
                }
                p {
                    em { +"This person adds little value beyond what AI provides." }
                }
            },
            right = {
                p { strong { +"Deep Understanding:" } }
                ul {
                    li { +"\"Here's why this approach works\"" }
                    li { +"Explains trade-offs and implications" }
                    li { +"Debugs when things go wrong" }
                    li { +"Makes informed architectural decisions" }
                    li { +"Reviews and improves AI suggestions" }
                    li { +"Adapts solutions to context" }
                }
                p {
                    em { +"This person multiplies AI's value." }
                }
            }
        )
        blockQuote {
            +"If you don't understand the code better than the AI that wrote it, "
            +"why would anyone hire you instead of just using the AI?"
        }
    }
)

object BuildingDeepKnowledgeSlide : Slide(
    header = "Building Deep Knowledge",
    summary = {
        +"Deep understanding comes from deliberate practice, curiosity, and connecting concepts."
    },
    content = {
        p {
            strong { +"How to build deep knowledge (not just surface familiarity):" }
        }
        ol {
            li {
                strong { +"Don't just copy - understand why" }
                br()
                +"When AI gives you code, ask: Why does this work? What are the alternatives? What could go wrong?"
            }
            li {
                strong { +"Experiment and break things" }
                br()
                +"Change the code. What happens? Why? This builds mental models."
            }
            li {
                strong { +"Explain it to others (or yourself)" }
                br()
                +"If you can't explain it clearly, you don't understand it deeply enough."
            }
            li {
                strong { +"Read the actual documentation" }
                br()
                +"AI summaries are useful, but reading docs builds deeper understanding of design decisions and edge cases."
            }
            li {
                strong { +"Connect concepts" }
                br()
                +"How does JPA relate to JDBC? Why do we use DTOs? How do transactions work? The connections matter."
            }
            li {
                strong { +"Solve problems without AI first" }
                br()
                +"Try solving on your own, then use AI to compare approaches. This builds problem-solving skills."
            }
        }
        p {
            highlight("This course is designed to build deep understanding:")
        }
        p {
            +"We don't just show you code. We explain why, explore trade-offs, and connect concepts across lessons."
        }
    }
)

object CompoundingFundamentalsSlide : Slide(
    header = "The Compounding Value of Fundamentals",
    summary = {
        +"Strong fundamentals compound over time. Surface knowledge becomes obsolete quickly."
    },
    content = {
        p {
            strong { +"Why fundamentals matter more in the AI era:" }
        }
        ul {
            li {
                strong { +"Fundamentals transfer" }
                br()
                +"Understanding HTTP, databases, architecture, testing - these apply everywhere"
            }
            li {
                strong { +"New tools, same problems" }
                br()
                +"Frameworks change. The problems they solve don't. Fundamentals let you quickly master new tools."
            }
            li {
                strong { +"Foundation for judgment" }
                br()
                +"You can only evaluate AI suggestions if you understand the underlying concepts"
            }
            li {
                strong { +"Enables learning" }
                br()
                +"Deep fundamentals make learning new technologies much faster"
            }
            li {
                strong { +"Career resilience" }
                br()
                +"As tools and technologies change, fundamentals keep you relevant"
            }
        }
        p {
            highlight("Fundamentals you've learned in this course:")
        }
        ul {
            li { +"HTTP and REST principles" }
            li { +"Database design and transactions" }
            li { +"Layered architecture patterns" }
            li { +"Testing strategies" }
            li { +"Object-oriented programming" }
            li { +"Dependency injection and inversion of control" }
        }
        p {
            +"These concepts will be relevant regardless of what frameworks or tools you use in 5 or 10 years."
        }
    }
)

object YourCareerInAIWorldTopic : Topic(
    title = "Your Career in an AI World",
    slides = listOf(
        JobsBecomingValuableSlide,
        HowToPositionYourselfSlide,
        ContinuousLearningSlide,
        FinalEncouragementSlide
    )
)

object JobsBecomingValuableSlide : Slide(
    header = "What Jobs Are Becoming More Valuable",
    summary = {
        +"Some developer roles are becoming more valuable with AI. Others are at risk."
    },
    content = {
        twoColumns(
            left = {
                p {
                    strong { +"Increasing in value:" }
                }
                ul {
                    li {
                        strong { +"Architects" }
                        br()
                        +"Design systems, make technical decisions"
                    }
                    li {
                        strong { +"Senior engineers" }
                        br()
                        +"Review code, mentor, solve complex problems"
                    }
                    li {
                        strong { +"Technical leads" }
                        br()
                        +"Bridge business and technology"
                    }
                    li {
                        strong { +"Platform engineers" }
                        br()
                        +"Build tools and infrastructure"
                    }
                    li {
                        strong { +"Security engineers" }
                        br()
                        +"Identify and prevent vulnerabilities"
                    }
                    li {
                        strong { +"Performance engineers" }
                        br()
                        +"Optimize systems at scale"
                    }
                }
            },
            right = {
                p {
                    strong { +"Declining in value:" }
                }
                ul {
                    li {
                        strong { +"Pure code writers" }
                        br()
                        +"Converting specs to code without judgment"
                    }
                    li {
                        strong { +"Boilerplate generators" }
                        br()
                        +"Creating repetitive CRUD code"
                    }
                    li {
                        strong { +"Syntax experts" }
                        br()
                        +"Knowing language syntax but not architecture"
                    }
                    li {
                        strong { +"Copy-paste developers" }
                        br()
                        +"Using code without understanding"
                    }
                }
                p {
                    em { +"These tasks can increasingly be automated" }
                }
            }
        )
        p {
            highlight("The pattern:")
            +" Roles requiring judgment, experience, and context are becoming more valuable. "
            +"Roles focused on mechanical code production are being automated."
        }
    }
)

object HowToPositionYourselfSlide : Slide(
    header = "How to Position Yourself",
    summary = {
        +"Practical advice for building a career as an AI-augmented developer."
    },
    content = {
        p {
            strong { +"What you should focus on:" }
        }
        ol {
            li {
                strong { +"Master fundamentals" }
                br()
                +"Deep knowledge of architecture, databases, testing, security, performance"
            }
            li {
                strong { +"Learn to work effectively with AI" }
                br()
                +"Use AI to accelerate development, but maintain high standards for code quality"
            }
            li {
                strong { +"Build real projects" }
                br()
                +"Complete applications that solve real problems (like your final project!)"
            }
            li {
                strong { +"Develop debugging skills" }
                br()
                +"Practice systematic problem-solving in complex scenarios"
            }
            li {
                strong { +"Understand business context" }
                br()
                +"Learn to connect technical solutions to business problems"
            }
            li {
                strong { +"Communicate clearly" }
                br()
                +"Explain technical concepts to non-technical stakeholders"
            }
            li {
                strong { +"Stay production-focused" }
                br()
                +"Always think about reliability, security, performance, monitoring"
            }
        }
        blockQuote {
            +"Your goal: Become the developer who can take an AI-generated solution from "
            +"\"it runs on my machine\" to \"it runs reliably in production at scale.\""
        }
    }
)

object ContinuousLearningSlide : Slide(
    header = "Continuous Learning",
    summary = {
        +"The pace of change is accelerating. Continuous learning isn't optional - it's essential."
    },
    content = {
        p {
            strong { +"How to stay relevant:" }
        }
        ul {
            li {
                strong { +"Build things" }
                br()
                +"Projects > Tutorials. Build complete applications, deploy them, maintain them."
            }
            li {
                strong { +"Read quality sources" }
                br()
                +"Official documentation, engineering blogs from companies at scale, research papers"
            }
            li {
                strong { +"Learn from production" }
                br()
                +"Real bugs, real performance issues, real scaling challenges teach the most"
            }
            li {
                strong { +"Explore new tools thoughtfully" }
                br()
                +"Try new technologies, but focus on understanding problems they solve, not just features"
            }
            li {
                strong { +"Teach others" }
                br()
                +"Teaching forces you to develop deeper understanding"
            }
            li {
                strong { +"Network with other developers" }
                br()
                +"Share knowledge, learn from their experiences, stay aware of industry trends"
            }
        }
        p {
            highlight("This course is your foundation, not your destination.")
        }
        p {
            +"What you've learned here gives you the fundamentals to continue learning throughout your career. "
            +"Technology changes fast. Your ability to learn, adapt, and understand deeply is your greatest asset."
        }
    }
)

object FinalEncouragementSlide : Slide(
    header = "Your Future as a Developer",
    summary = {
        +"AI is a tool that makes skilled developers more powerful, not less relevant."
    },
    content = {
        p {
            strong { +"Remember:" }
        }
        ul {
            li {
                +"Software development has always been about solving problems, not just writing code"
            }
            li {
                +"AI can generate code, but it can't understand your users' needs or your business context"
            }
            li {
                +"The skills you've learned - architecture, testing, debugging, production thinking - "
                +"are becoming more valuable, not less"
            }
            li {
                +"Developers who embrace AI as a tool will be far more productive than those who ignore it"
            }
            li {
                +"But developers who rely only on AI without deep understanding will struggle"
            }
        }
        p {
            highlight("The opportunity:")
        }
        p {
            +"We're entering an era where skilled developers can build more, faster, and better than ever before. "
            +"AI removes the tedium and lets you focus on what matters: architecture, design, problem-solving, "
            +"and creating software that actually solves real problems."
        }
        blockQuote {
            +"The best developers have always been those who understand problems deeply, "
            +"make good decisions, and build reliable systems. That hasn't changed. "
            +"AI is just a new tool in your toolbox."
        }
        p {
            strong { +"Your value is not in typing code. It's in understanding what to build and how to build it well." }
        }
        p {
            +"Now go build something amazing."
        }
    }
)

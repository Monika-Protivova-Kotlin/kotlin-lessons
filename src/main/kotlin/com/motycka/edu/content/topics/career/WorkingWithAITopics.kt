package com.motycka.edu.content.topics.career

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.hintCard
import com.motycka.edu.model.twoColumns
import com.motycka.edu.model.warningCard
import kotlinx.html.*

object WorkingEffectivelyWithAITopic : Topic(
    title = "Working Effectively with AI",
    slides = listOf(
        AIAsLearningToolSlide,
        AIAsProductivityToolSlide,
        WhenToTrustAISlide,
        AIAsPairProgrammerSlide,
        CodeReviewPrinciplesSlide,
        CommonAIMistakesSlide,
        TestingAICodeSlide
    )
)

object AIAsLearningToolSlide : Slide(
    header = "AI as a Learning Tool",
    summary = {
        +"Use AI to learn faster, but always verify and understand what you learn."
    },
    content = {
        twoColumns(
            left = {
                p {
                    h4 { +"How to use AI for learning" }
                }
                ul {
                    li {
                        strong { +"Ask for explanations" }
                        br()
                        em { +"\"Explain how Spring's @Transactional annotation works\"" }
                    }
                    li {
                        strong { +"Request examples" }
                        br()
                        em { +"\"Show me an example of using MockK to mock a repository\"" }
                    }
                    li {
                        strong { +"Explore alternatives" }
                        br()
                        em { +"\"What are the trade-offs between JDBC and JPA?\"" }
                    }
                    li {
                        strong { +"Debug your understanding" }
                        br()
                        em { +"\"Why doesn't this code work?\" (paste error)" }
                    }
                }
            },
            right = {
                p {
                    h4 { +"Critical rules" }
                }
                ol {
                    li { strong { +"Always verify" }; +" - AI can be confidently wrong" }
                    li { strong { +"Test the code" }; +" - Don't assume it works" }
                    li { strong { +"Read the docs" }; +" - AI summarizes, docs are authoritative" }
                    li { strong { +"Understand before using" }; +" - If you can't explain it, don't use it" }
                }
            }
        )
        warningCard {
            +"AI is a brilliant tutor with occasional hallucinations. Use it, but verify everything."
        }
    }
)

object AIAsProductivityToolSlide : Slide(
    header = "AI as a Productivity Tool",
    summary = {
        +"Let AI handle boilerplate so you can focus on architecture and business logic."
    },
    content = {
        twoColumns(
            left = {
                p {
                    h4 { +"Effective uses for productivity" }
                }
                ul {
                    li {
                        strong { +"Generate boilerplate" }
                        br()
                        em { +"Data classes, DTOs, entity mapping functions" }
                    }
                    li {
                        strong { +"Write repetitive code" }
                        br()
                        em { +"Similar endpoints, CRUD operations, basic validation" }
                    }
                    li {
                        strong { +"Create test templates" }
                        br()
                        em { +"Setup code, common test patterns, mock structures" }
                    }
                    li {
                        strong { +"Documentation" }
                        br()
                        em { +"KDoc comments, README sections, API documentation" }
                    }
                    li {
                        strong { +"Refactoring assistance" }
                        br()
                        em { +"Renaming, restructuring, extracting methods" }
                    }
                }
            },
            right = {
                p {
                    h4 { + "The productivity pattern" }
                }
                ol {
                    li { +"You design the solution" }
                    li { +"AI generates initial implementation" }
                    li { +"You review and refine" }
                    li { +"You write tests to verify" }
                    li { +"You integrate into the larger system" }
                }
            }
        )
        hintCard {
            highlight { +"You're still in control." }
            +" AI is your assistant, not your replacement."
        }
    }
)

object WhenToTrustAISlide : Slide(
    header = "When to Trust AI",
    summary = {
        +"Learn to distinguish between tasks where AI is reliable vs where it needs heavy verification."
    },
    content = {
        twoColumns(
            left = {
                p { h4 { +"Generally reliable" } }
                ul {
                    li { +"Standard patterns you recognize" }
                    li { +"Well-documented APIs" }
                    li { +"Simple transformations" }
                    li { +"Common use cases" }
                    li { +"Syntax you can verify quickly" }
                }
                p {
                    highlight { +"Quick review" }
                    +" → "
                    highlight { +"Test" }
                    +" → "
                    highlight { +"Accept" }
                }

                p { h4 { +"Needs careful verification" } }
                ul {
                    li { +"Security-sensitive code" }
                    li { +"Complex business logic" }
                    li { +"Performance-critical sections" }
                    li { +"Unfamiliar libraries or patterns" }
                    li { +"Anything you don't understand" }
                }
                p {
                    highlight { +"Thorough review" }
                    +" → "
                    highlight { +"Understand" }
                    +" → "
                    highlight { +"Test extensively" }
                    +" → "
                    highlight { +"Verify" }
                    +" → "
                    highlight { +"Accept" }
                }
            },
            right = {
                p {
                    h4 { +"Red flags - Don't trust AI when" }
                }
                ul {
                    li { +"You don't understand the generated code" }
                    li { +"It uses libraries or patterns unfamiliar to you" }
                    li { +"The explanation doesn't make sense" }
                    li { +"It contradicts official documentation" }
                    li { +"Multiple AI sessions give different answers" }
                }
                hintCard {
                    +"If you wouldn't accept code like this from a junior developer, don't accept it from AI."
                }
            }
        )

    }
)

object AIAsPairProgrammerSlide : Slide(
    header = "AI as Your Pair Programmer",
    summary = {
        +"Treat AI like a junior partner: valuable for brainstorming, but needs your guidance and review."
    },
    content = {
        twoColumns(
            left = {
                p {
                    h4 { +"The pair programming mindset" }
                }
                p {
                    +"In traditional pair programming, one person writes code while the other reviews. "
                    +"With AI, you can switch roles dynamically:"
                }
                ul {
                    li {
                        strong { +"AI generates, you review" }
                        br()
                        em { +"\"Create a repository method for finding tasks by status\"" }
                        br()
                        +"You verify correctness, check for edge cases, ensure it fits your architecture"
                    }
                    li {
                        strong { +"You write the code, AI reviews" }
                        br()
                        em { +"\"Here's my implementation of the service layer. Suggest improvements...\"" }
                        br()
                        +"AI suggests optimizations, points out potential bugs, recommends best practices"
                    }
                    li {
                        strong { +"You design, AI implements" }
                        br()
                        em { +"\"I need a service that validates task permissions. Here's the interface...\"" }
                        br()
                        +"You define structure, AI fills in implementation details"
                    }
                    li {
                        strong { +"Collaborative debugging" }
                        br()
                        em { +"\"This test is failing. Here's the code and error...\"" }
                        br()
                        +"AI suggests possibilities, you investigate and verify"
                    }
                }
            },
            right = {
                p {
                    h4 { +"Best practices" }
                }
                ul {
                    li { +"Give AI context (show related code, explain the system)" }
                    li { +"Be specific in your requests" }
                    li { +"Ask AI to explain its suggestions" }
                    li { +"Challenge AI's assumptions" }
                    li { +"Use AI to explore alternatives before deciding" }
                }
            }
        )
    }
)

object CodeReviewPrinciplesSlide : Slide(
    header = "Reviewing AI-Generated Code",
    summary = {
        +"Apply the same rigor to AI code as you would to code from any other source."
    },
    content = {
        twoColumns(
            left = {
//                p { h4 { +"Code review checklist" } }
                ol {
                    li {
                        strong { +"Does it do what you asked?" }
                        br()
                        +"Test the happy path first"
                    }
                    li {
                        strong { +"Does it handle edge cases?" }
                        br()
                        +"Null values, empty lists, invalid input, boundary conditions"
                    }
                    li {
                        strong { +"Is it secure?" }
                        br()
                        +"SQL injection, XSS, authentication, authorization"
                    }
                    li {
                        strong { +"Does it follow your architecture?" }
                        br()
                        +"Correct layer, proper dependencies, consistent patterns"
                    }
                    li {
                        strong { +"Is it testable?" }
                        br()
                        +"Can you write tests for it? Are dependencies injectable?"
                    }
                    li {
                        strong { +"Is it maintainable?" }
                        br()
                        +"Clear naming, reasonable complexity, proper error handling"
                    }
                    li {
                        strong { +"Does it perform well?" }
                        br()
                        +"N+1 queries, unnecessary loops, memory usage"
                    }
                }
            },
            right = {
                warningCard("Remember") {
                    +"You're responsible for the code, not the AI."
                }
            }
        )
    }
)

object CommonAIMistakesSlide : Slide(
    header = "Common AI Mistakes",
    summary = {
        +"AI has predictable failure modes. Learn to recognize and catch them."
    },
    content = {
        twoColumns(
            left = {
                p { h4 { +"Watch out for" } }
                ul {
                    li {
                        strong { +"Hallucinated APIs" }
                        br()
                        em { +"AI invents methods or parameters that don't exist" }
                        br()
                        +"→ Always check documentation"
                    }
                    li {
                        strong { +"Missing error handling" }
                        br()
                        em { +"Assumes happy path, ignores exceptions" }
                        br()
                        +"→ Add try-catch, validation, null checks"
                    }
                    li {
                        strong { +"Incorrect assumptions" }
                        br()
                        em { +"Assumes data structure, state, or behavior" }
                        br()
                        +"→ Verify assumptions match your system"
                    }
                    li {
                        strong { +"Outdated patterns" }
                        br()
                        em { +"Uses deprecated APIs or old approaches" }
                        br()
                        +"→ Check if code is current"
                    }
                    li {
                        strong { +"Over-complicated solutions" }
                        br()
                        em { +"Uses complex patterns when simple would work" }
                        br()
                        +"→ Simplify if possible"
                    }
                    li {
                        strong { +"Missing security checks" }
                        br()
                        em { +"Forgets authentication, authorization, validation" }
                        br()
                        +"→ Add security layers"
                    }
                }
            },
            right = {
                warningCard {
                    +"AI is optimizing for code that looks right, not code that is right."
                }
            }
        )

    }
)

object TestingAICodeSlide : Slide(
    header = "Testing AI-Generated Code",
    summary = {
        +"AI-generated code needs even more thorough testing than human code."
    },
    content = {
        twoColumns(
            left = {
                p {
                    h4 { +"Why test AI code more thoroughly?" }
                }
                ul {
                    li { +"You didn't write it, so you might miss edge cases in review" }
                    li { +"AI doesn't understand your business rules" }
                    li { +"AI might make subtle logical errors" }
                    li { +"AI doesn't consider integration with your existing code" }
                }
            },
            right = {
                p {
                    h4 { +"Testing strategy" }
                }
                ol {
                    li {
                        strong { +"Start with the tests AI suggests" }
                        br()
                        +"They often cover basic cases"
                    }
                    li {
                        strong { +"Add edge case tests" }
                        br()
                        +"Null, empty, boundary values, invalid input"
                    }
                    li {
                        strong { +"Test error scenarios" }
                        br()
                        +"What happens when things go wrong?"
                    }
                    li {
                        strong { +"Integration tests" }
                        br()
                        +"Does it work with your actual database, services, etc?"
                    }
                    li {
                        strong { +"Manual testing" }
                        br()
                        +"Use the API/UI yourself - does it feel right?"
                    }
                }
            }
        )
        warningCard {
            +" If you wouldn't trust your own code without tests, don't trust AI code without tests."
        }
    }
)

package com.motycka.edu.content.topics.career

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.hintCard
import com.motycka.edu.model.infoCard
import com.motycka.edu.model.twoColumns
import kotlinx.html.*

object ChangingLandscapeTopic : Topic(
    title = "The Changing Landscape",
    slides = listOf(
        SoftwareDevelopmentEvolvingSlide,
        OpportunityNotThreatSlide,
        WhatStaysSameSlide
    )
)

object SoftwareDevelopmentEvolvingSlide : Slide(
    header = "How Software Development is Evolving",
    summary = {
        +"AI is changing how we write code, but not why we write it or what makes great software."
    },
    content = {
//        p {
//            h4 { +"The landscape is shifting rapidly" }
//        }
        twoColumns(
            left = {
                p { h4 { +"What's Changing:" } }
                ul {
                    li { +"AI can generate code from descriptions" }
                    li { +"Boilerplate code is automated" }
                    li { +"Syntax lookup is instant" }
                    li { +"Simple bugs are caught automatically" }
                    li { +"Documentation is AI-assisted" }
                    li { +"Basic testing can be generated" }
                }
            },
            right = {
                p { h4 { +"What Remains Essential:" } }
                ul {
                    li { +"Understanding system architecture" }
                    li { +"Making design decisions" }
                    li { +"Debugging complex issues" }
                    li { +"Understanding business context" }
                    li { +"Ensuring security and reliability" }
                    li { +"Validating correctness" }
                }
            }
        )
        hintCard {
            +"The question isn't whether AI will change development."
            br
            br
            em { +"It already has. "}
            br
            br
            +"The real question is: "
            br
            em { +"What kind of developer will thrive in this new world?" }
        }
    }
)

object OpportunityNotThreatSlide : Slide(
    header = "Opportunity, Not Threat",
    summary = {
        +"AI tools make good developers more productive, not obsolete."
    },
    content = {
        p {
            +"Think of AI as the latest in a long line of productivity tools:"
        }
        ul {
            li { strong { +"Assembly → High-level languages" }; +" - We didn't stop needing programmers" }
            li { strong { +"Manual memory → Garbage collection" }; +" - We focused on higher-level problems" }
            li { strong { +"Writing HTML → React/frameworks" }; +" - We built more complex applications" }
            li { strong { +"Manual testing → Automated testing" }; +" - We improved software quality" }
        }
        p {
            strong { +"Each tool raised the bar and changed what developers do, but increased demand." }
        }
        p {
            highlight("The Pattern:")
        }
        ol {
            li { +"New tool automates routine tasks" }
            li { +"Developers solve harder problems" }
            li { +"Software becomes more sophisticated" }
            li { +"Demand for skilled developers increases" }
        }
        p {
            strong { +"AI follows the same pattern." }
            +" The difference? It's happening faster."
        }
    }
)

object WhatStaysSameSlide : Slide(
    header = "What Stays the Same",
    summary = {
        +"Fundamental principles of good software don't change with AI."
    },
    content = {
        p {
            +"Regardless of who (or what) writes the code, great software requires:"
        }
        ul {
            li {
                strong { +"Clear architecture" }
                +" - Systems must be maintainable and evolvable"
            }
            li {
                strong { +"Proper testing" }
                +" - Correctness must be verified"
            }
            li {
                strong { +"Security mindset" }
                +" - Vulnerabilities must be prevented"
            }
            li {
                strong { +"Performance consideration" }
                +" - Resources are not infinite"
            }
            li {
                strong { +"Operational excellence" }
                +" - Software must run reliably in production"
            }
            li {
                strong { +"Business alignment" }
                +" - Solutions must solve real problems"
            }
        }
        p {
            +"AI can help with implementation, but these concerns require human judgment, "
            +"understanding of context, and experience with trade-offs."
        }
        hintCard {
            +"Your value as a developer has never been just about typing code. "
            +"It's about understanding problems and crafting solutions."
        }
    }
)

object WhatAICanAndCannotDoTopic : Topic(
    title = "What AI Can and Cannot Do",
    slides = listOf(
        WhatAIDoesWellSlide,
        WhatAIStrugglesWithSlide,
        TheHumanGapSlide
    )
)

object WhatAIDoesWellSlide : Slide(
    header = "What AI Does Well",
    summary = {
        +"AI excels at pattern recognition, code generation from clear specs, and automating repetitive tasks."
    },
    content = {
        p {
            strong { +"AI's strengths in software development:" }
        }
        ul {
            li {
                strong { +"Code generation from descriptions" }
                br()
                em { +"\"Create a REST endpoint that returns all users\" → Working code" }
            }
            li {
                strong { +"Boilerplate and scaffolding" }
                br()
                em { +"Data classes, repositories, basic CRUD operations" }
            }
            li {
                strong { +"Syntax and API usage" }
                br()
                em { +"How to use a library, correct syntax, method signatures" }
            }
            li {
                strong { +"Code transformation" }
                br()
                em { +"Refactoring, translating between languages" }
            }
            li {
                strong { +"Documentation and comments" }
                br()
                em { +"Explaining what code does" }
            }
            li {
                strong { +"Simple test generation" }
                br()
                em { +"Basic unit tests for straightforward functions" }
            }
        }
        p {
            strong { +"These are all valuable!" }
            +" Use AI for these tasks. It will make you faster."
        }
    }
)

object WhatAIStrugglesWithSlide : Slide(
    header = "What AI Struggles With",
    summary = {
        +"AI has difficulty with architecture, business context, and complex system-level decisions."
    },
    content = {
        p {
            strong { +"AI's limitations:" }
        }
        ul {
            li {
                strong { +"System architecture" }
                br()
                em { +"How should services communicate? What belongs where? What are the boundaries?" }
            }
            li {
                strong { +"Business context" }
                br()
                em { +"Why are we building this? What problem does it solve? What are the priorities?" }
            }
            li {
                strong { +"Complex debugging" }
                br()
                em { +"Production issues, race conditions, performance bottlenecks" }
            }
            li {
                strong { +"Security implications" }
                br()
                em { +"Subtle vulnerabilities, authentication flows, data exposure" }
            }
            li {
                strong { +"Performance trade-offs" }
                br()
                em { +"When to cache? What data structure? How to optimize?" }
            }
            li {
                strong { +"Maintaining consistency" }
                br()
                em { +"Keeping large codebases coherent across many files" }
            }
        }
        hintCard {
            +"AI sees code. You see the system. That's your advantage."
        }
    }
)

object TheHumanGapSlide : Slide(
    header = "The Human Gap",
    summary = {
        +"The gap between AI capabilities and business needs is where developers provide value."
    },
    content = {
        p {
            +"Consider building a task management API (your final project):"
        }
        twoColumns(
            left = {
                p { strong { +"AI can:" } }
                ul {
                    li { +"Generate CRUD endpoints" }
                    li { +"Create entity classes" }
                    li { +"Write basic validation" }
                    li { +"Generate repository code" }
                    li { +"Create simple tests" }
                }
            },
            right = {
                p { strong { +"You must:" } }
                ul {
                    li { +"Design the overall architecture" }
                    li { +"Decide on data model and relationships" }
                    li { +"Implement business rules" }
                    li { +"Ensure security (who can delete whose tasks?)" }
                    li { +"Handle error cases properly" }
                    li { +"Make it production-ready" }
                }
            }
        )
        p {
            strong { +"The difference:" }
            +" AI gives you working code. You create a working system."
        }
        p {
            +"AI is a tool in your toolbox. Like any tool, its value depends on the skill of the person using it."
        }
    }
)

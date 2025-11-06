package com.motycka.edu.content.topics.career

import com.motycka.edu.model.*
import kotlinx.html.*

object GitWorkflowsTopic : Topic(
    title = "Git Workflows & Collaboration",
    slides = listOf(
        IntroductorySlide,
        GitHubFlowSlide,
        GitFlowSlide,
        TrunkBasedDevelopmentSlide,
        FinalSlide
    )
)

object IntroductorySlide : Slide(
    header = "Git Workflows & Branching Strategies",
    summary = {
        +"Different teams need different approaches to collaboration. Git workflows provide structure for how code flows from development to production."
    },
    content = {
//        h4 { +"Why Git Workflows Matter" }
        p {
            +"As teams grow and products mature, the way you manage branches and releases becomes critical. "
            +"A good workflow prevents chaos, reduces bugs, and ensures everyone can work efficiently without stepping on each other's toes."
        }

        twoColumns(
            left = {
                h4 { +"Without a Workflow" }
                ul {
                    li { +"Merge conflicts everywhere" }
                    li { +"Unclear what's in production" }
                    li { +"Hard to revert bad changes" }
                    li { +"No coordination between developers" }
                    li { +"Difficult to manage releases" }
                    li { +"Testing becomes a nightmare" }
                }
            },
            right = {
                h4 { +"With a Workflow" }
                ul {
                    li { +"Clear branching strategy" }
                    li { +"Predictable release process" }
                    li { +"Easy rollbacks when needed" }
                    li { +"Parallel development possible" }
                    li { +"Quality gates (code review, testing)" }
                    li { +"Traceable history" }
                }
            }
        )
//        p { h4 { +"Factors to Consider" } }
//        ul {
//            li {
//                highlight { +"Team size" }
//                +" - Small teams can work with simpler workflows; large teams need more structure"
//            }
//            li {
//                highlight { +"Release cadence" }
//                +" - Continuous deployment vs scheduled releases require different approaches"
//            }
//            li {
//                highlight { +"Production environment" }
//                +" - Single production instance vs multiple versions affects branching strategy"
//            }
//            li {
//                highlight { +"Team maturity" }
//                +" - Some workflows require strong testing discipline and CI/CD infrastructure"
//            }
//            li {
//                highlight { +"Regulatory requirements" }
//                +" - Some industries require audit trails and approval processes"
//            }
//        }

        hintCard {
            +"There's no \"best\" workflow - only the workflow that best fits your team's needs, size, and deployment model."
        }
    }
)

object GitHubFlowSlide : Slide(
    header = "GitHub Flow - Simple & Modern",
    summary = {
        +"The simplest workflow: branch from main, develop your feature, create a pull request, merge back to main, deploy immediately."
    },
    content = {
//        h4 { +"How GitHub Flow Works" }
        p {
            +"Everything happens on the "; inlineCode("main"); +" branch or short-lived feature branches. "
            +"When you're ready to work on something, create a branch. When it's done, open a pull request for review. "
            +"After approval, merge to main and deploy. Main is always deployable."
        }

        svgDiagram(
            width = 900,
            height = 180,
            svgContent = """
                <!-- Main branch -->
                <line x1="20" y1="90" x2="860" y2="90" stroke="#22C55E" stroke-width="6"/>
                <text x="20" y="75" font-size="13" font-weight="bold" fill="#15803D">main</text>

                <!-- Commit dots on main -->
                <circle cx="80" cy="90" r="6" fill="#15803D"/>
                <circle cx="440" cy="90" r="6" fill="#15803D"/>
                <circle cx="800" cy="90" r="6" fill="#15803D"/>

                <!-- Feature branch 1 -->
                <line x1="80" y1="90" x2="80" y2="30" stroke="#3B82F6" stroke-width="3"/>
                <line x1="80" y1="30" x2="320" y2="30" stroke="#3B82F6" stroke-width="3"/>
                <line x1="320" y1="30" x2="320" y2="90" stroke="#3B82F6" stroke-width="3" stroke-dasharray="5,5"/>
                <text x="200" y="20" text-anchor="middle" font-size="11" font-weight="bold" fill="#1E40AF">feature/login</text>
                <circle cx="160" cy="30" r="5" fill="#1E40AF"/>
                <circle cx="240" cy="30" r="5" fill="#1E40AF"/>

                <!-- PR and merge -->
                <text x="360" y="55" text-anchor="middle" font-size="10" fill="#7C3AED" font-weight="bold">Pull Request</text>
                <text x="360" y="68" text-anchor="middle" font-size="9" fill="#7C3AED">Code Review</text>
                <circle cx="320" cy="90" r="8" fill="#7C3AED" stroke="#fff" stroke-width="2"/>

                <!-- Feature branch 2 -->
                <line x1="440" y1="90" x2="440" y2="30" stroke="#F59E0B" stroke-width="3"/>
                <line x1="440" y1="30" x2="680" y2="30" stroke="#F59E0B" stroke-width="3"/>
                <line x1="680" y1="30" x2="680" y2="90" stroke="#F59E0B" stroke-width="3" stroke-dasharray="5,5"/>
                <text x="560" y="20" text-anchor="middle" font-size="11" font-weight="bold" fill="#D97706">feature/payment</text>
                <circle cx="520" cy="30" r="5" fill="#D97706"/>
                <circle cx="600" cy="30" r="5" fill="#D97706"/>
                
                <!-- PR and merge -->
                <text x="720" y="55" text-anchor="middle" font-size="10" fill="#F59E0B" font-weight="bold">Pull Request</text>
                <text x="720" y="68" text-anchor="middle" font-size="9" fill="#F59E0B">Code Review</text>
                <circle cx="680" cy="90" r="8" fill="#7C3AED" stroke="#fff" stroke-width="2"/>

                <!-- Deploy indicators -->
                <path d="M 320 95 L 320 115 L 310 110 M 320 115 L 330 110" stroke="#EF4444" stroke-width="2" fill="none"/>
                <text x="320" y="130" text-anchor="middle" font-size="9" fill="#DC2626">Deploy</text>

                <path d="M 680 95 L 680 115 L 670 110 M 680 115 L 690 110" stroke="#EF4444" stroke-width="2" fill="none"/>
                <text x="680" y="130" text-anchor="middle" font-size="9" fill="#DC2626">Deploy</text>
            """.trimIndent()
        )

        twoColumns(
            left = {
//                p { strong { +"Pros:" } }
                ul {
                    style = "list-style: none; padding-left: 20px;"
                    li { +"+ Simple and easy to learn" }
                    li { +"+ Fast iteration and deployment" }
                    li { +"+ Works perfectly with CI/CD" }
                    li { +"+ Single source of truth (main)" }
                }
            },
            right = {
//                p { strong { +"Cons:" } }
                ul {
                    style = "list-style: none; padding-left: 20px;"
                    li { +"- Less control over releases" }
                    li { +"- Not ideal for scheduled releases" }
                    li { +"- Can be chaotic for large teams" }
                }
            }
        )

        hintCard("Best For") {
            +"GitHub Flow is ideal for web applications with continuous deployment, small to medium teams, and products with a single production version. "
            +"Used by GitHub, many startups, and modern SaaS companies."
        }
    }
)

object GitFlowSlide : Slide(
    header = "Git Flow - Structured & Controlled",
    summary = {
        +"A comprehensive branching model with dedicated branches for features, releases, and hotfixes. Provides maximum control for scheduled releases."
    },
    content = {
//        h4 { +"How Git Flow Works" }
        p {
            +"Two long-lived branches: "; inlineCode("main"); +" (production) and "; inlineCode("develop"); +" (integration). "
            +"Feature branches merge into develop. When ready for release, create a release branch for testing. "
            +"After QA approval, merge to main and back to develop. Hotfixes branch from main and merge to both main and develop."
        }

        svgDiagram(
            width = 900,
            height = 200,
            svgContent = """
                <!-- Master/Main branch -->
                <line x1="20" y1="40" x2="650" y2="40" stroke="#22C55E" stroke-width="5"/>
                <text x="20" y="25" font-size="12" font-weight="bold" fill="#15803D">main</text>
                <circle cx="80" cy="40" r="6" fill="#15803D"/>
                <circle cx="360" cy="40" r="6" fill="#15803D"/>
                <circle cx="610" cy="40" r="6" fill="#15803D"/>

                <!-- Develop branch -->
                <line x1="20" y1="100" x2="650" y2="100" stroke="#3B82F6" stroke-width="5"/>
                <text x="20" y="85" font-size="12" font-weight="bold" fill="#1E40AF">develop</text>
                <circle cx="80" cy="100" r="5" fill="#1E40AF"/>
                <circle cx="180" cy="100" r="5" fill="#1E40AF"/>
                <circle cx="290" cy="100" r="5" fill="#1E40AF"/>
                <circle cx="450" cy="100" r="5" fill="#1E40AF"/>
                <circle cx="560" cy="100" r="5" fill="#1E40AF"/>

                <!-- Feature branch -->
                <line x1="180" y1="100" x2="180" y2="160" stroke="#FCD34D" stroke-width="3"/>
                <line x1="180" y1="160" x2="290" y2="160" stroke="#FCD34D" stroke-width="3"/>
                <line x1="290" y1="160" x2="290" y2="100" stroke="#FCD34D" stroke-width="3" stroke-dasharray="5,5"/>
                <text x="235" y="180" text-anchor="middle" font-size="10" font-weight="bold" fill="#D97706">feature/xyz</text>
                <circle cx="230" cy="160" r="4" fill="#D97706"/>

                <!-- Release branch -->
                <line x1="450" y1="100" x2="450" y2="70" stroke="#F97316" stroke-width="3"/>
                <line x1="450" y1="70" x2="560" y2="70" stroke="#F97316" stroke-width="3"/>
                <line x1="560" y1="70" x2="560" y2="40" stroke="#F97316" stroke-width="3" stroke-dasharray="5,5"/>
                <line x1="560" y1="70" x2="560" y2="100" stroke="#F97316" stroke-width="3" stroke-dasharray="5,5"/>
                <text x="505" y="65" text-anchor="middle" font-size="10" font-weight="bold" fill="#EA580C">release/1.0</text>
                <circle cx="495" cy="70" r="4" fill="#EA580C"/>

                <!-- Hotfix branch -->
                <line x1="360" y1="40" x2="360" y2="10" stroke="#EF4444" stroke-width="3"/>
                <line x1="360" y1="10" x2="430" y2="10" stroke="#EF4444" stroke-width="3"/>
                <line x1="430" y1="10" x2="430" y2="40" stroke="#EF4444" stroke-width="3" stroke-dasharray="5,5"/>
                <line x1="430" y1="10" x2="430" y2="100" stroke="#EF4444" stroke-width="3" stroke-dasharray="5,5"/>
                <text x="395" y="5" text-anchor="middle" font-size="10" font-weight="bold" fill="#DC2626">hotfix/bug</text>
                <circle cx="395" cy="10" r="4" fill="#DC2626"/>

                <!-- Legend on the right -->
                <text x="690" y="35" font-size="11" font-weight="bold" fill="#666">Branch Types:</text>

                <rect x="690" y="50" width="15" height="8" fill="#22C55E"/>
                <text x="710" y="58" font-size="10" fill="#666">Production (main)</text>

                <rect x="690" y="70" width="15" height="8" fill="#3B82F6"/>
                <text x="710" y="78" font-size="10" fill="#666">Integration (develop)</text>

                <rect x="690" y="90" width="15" height="8" fill="#FCD34D"/>
                <text x="710" y="98" font-size="10" fill="#666">Features</text>

                <rect x="690" y="110" width="15" height="8" fill="#F97316"/>
                <text x="710" y="118" font-size="10" fill="#666">Releases</text>

                <rect x="690" y="130" width="15" height="8" fill="#EF4444"/>
                <text x="710" y="138" font-size="10" fill="#666">Hotfixes</text>
            """.trimIndent()
        )

        twoColumns(
            left = {
//                h4 { +"Advantages" }
                ul {
                    style = "list-style: none; padding-left: 20px;"
                    li { +"+ Clear structure for large teams" }
                    li { +"+ Great for scheduled releases" }
                    li { +"+ Parallel development streams" }
                    li { +"+ Hotfix workflow built-in" }
                }
            },
            right = {
//                h4 { +"Disadvantages" }
                ul {
                    style = "list-style: none; padding-left: 20px;"
                    li { +"- More complex to manage" }
                    li { +"- Slower feedback loops" }
                    li { +"- Can accumulate merge conflicts" }
                    li { +"- Overkill for small teams" }
                }
            }
        )

        hintCard("Best For") {
            +"Git Flow works well for enterprise software, desktop applications, mobile apps with app store releases, "
            +"and any project requiring scheduled releases, multiple versions in production, or formal QA cycles."
        }
    }
)

object TrunkBasedDevelopmentSlide : Slide(
    header = "Trunk-Based Development - Continuous Integration",
    summary = {
        +"All developers work on a single trunk (main) with very short-lived feature branches. Maximum integration speed with feature flags for incomplete work."
    },
    content = {
//        h4 { +"How Trunk-Based Development Works" }
        p {
            +"Everyone commits to "; inlineCode("main"); +" (the trunk) multiple times per day. "
            +"If branches are used, they're extremely short-lived (hours, not days). "
            +"Incomplete features are hidden behind feature flags. Requires excellent automated testing and CI/CD. "
            +"Every commit to main is potentially deployable."
        }

        svgDiagram(
            width = 900,
            height = 160,
            svgContent = """
                <!-- Trunk/Main branch -->
                <line x1="20" y1="80" x2="860" y2="80" stroke="#22C55E" stroke-width="6"/>
                <text x="20" y="65" font-size="13" font-weight="bold" fill="#15803D">main / trunk</text>

                <!-- Many commits on trunk -->
                <circle cx="80" cy="80" r="5" fill="#15803D"/>
                <circle cx="160" cy="80" r="5" fill="#15803D"/>
                <circle cx="240" cy="80" r="5" fill="#15803D"/>
                <circle cx="320" cy="80" r="5" fill="#15803D"/>
                <circle cx="400" cy="80" r="5" fill="#15803D"/>
                <circle cx="480" cy="80" r="5" fill="#15803D"/>
                <circle cx="560" cy="80" r="5" fill="#15803D"/>
                <circle cx="640" cy="80" r="5" fill="#15803D"/>
                <circle cx="720" cy="80" r="5" fill="#15803D"/>
                <circle cx="800" cy="80" r="5" fill="#15803D"/>

                <!-- Very short-lived feature branch 1 -->
                <line x1="160" y1="80" x2="160" y2="40" stroke="#3B82F6" stroke-width="2"/>
                <line x1="160" y1="40" x2="240" y2="40" stroke="#3B82F6" stroke-width="2"/>
                <line x1="240" y1="40" x2="240" y2="80" stroke="#3B82F6" stroke-width="2" stroke-dasharray="3,3"/>
                <text x="200" y="30" text-anchor="middle" font-size="9" fill="#1E40AF">short feature</text>
                <circle cx="200" cy="40" r="4" fill="#1E40AF"/>

                <!-- Very short-lived feature branch 2 -->
                <line x1="480" y1="80" x2="480" y2="120" stroke="#3B82F6" stroke-width="2"/>
                <line x1="480" y1="120" x2="560" y2="120" stroke="#3B82F6" stroke-width="2"/>
                <line x1="560" y1="120" x2="560" y2="80" stroke="#3B82F6" stroke-width="2" stroke-dasharray="3,3"/>
                <text x="520" y="135" text-anchor="middle" font-size="9" fill="#1E40AF">1-2 days max</text>
                <circle cx="520" cy="120" r="4" fill="#1E40AF"/>

                <!-- Feature flags indicator -->
                <rect x="700" y="20" width="120" height="35" rx="5" fill="#FEF3C7" stroke="#F59E0B" stroke-width="2"/>
                <text x="760" y="37" text-anchor="middle" font-size="10" font-weight="bold" fill="#D97706">Feature Flags</text>
                <text x="760" y="50" text-anchor="middle" font-size="8" fill="#92400E">Control features</text>

                <!-- Continuous deploy arrow -->
                <path d="M 80 90 L 80 130 M 320 90 L 320 130 M 560 90 L 560 130 M 800 90 L 800 130"
                      stroke="#EF4444" stroke-width="2" stroke-dasharray="3,3"/>
                <text x="450" y="150" text-anchor="middle" font-size="10" fill="#DC2626" font-weight="bold">↓ Continuous Deployment ↓</text>
            """.trimIndent()
        )

        twoColumns(
            left = {
//                h4 { +"Advantages" }
                ul {
                    style = "list-style: none; padding-left: 20px;"
                    li { +"+ Fastest integration" }
                    li { +"+ Minimizes merge conflicts" }
                    li { +"+ True continuous integration" }
                    li { +"+ Simplest workflow" }
                }
            },
            right = {
//                h4 { +"Disadvantages" }
                ul {
                    style = "list-style: none; padding-left: 20px;"
                    li { +"- Requires strong test coverage" }
                    li { +"- Needs feature flags for incomplete work" }
                    li { +"- High discipline required" }
                    li { +"- Less time for thorough code review" }
                    li { +"- Can feel risky to teams" }
                    li { +"- Requires mature CI/CD" }
                }
            }
        )

        hintCard("Best For") {
            +"Trunk-Based Development is ideal for mature engineering teams with excellent automated testing, "
            +"strong CI/CD infrastructure, and a culture of continuous delivery. Used by Google, Facebook, and other tech giants."
        }
    }
)

object FinalSlide : Slide(
    header = "",
    content = {
        div {
            style = "display: flex; justify-content: center; align-items: center; height: 60vh; font-size: 2.5em; font-weight: 300; text-align: center;"
            +"Now go build something amazing."
        }
    }
)

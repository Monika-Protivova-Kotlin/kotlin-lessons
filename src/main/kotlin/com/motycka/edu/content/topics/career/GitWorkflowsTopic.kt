package com.motycka.edu.content.topics.career

import com.motycka.edu.model.*
import kotlinx.html.*

object GitWorkflowsTopic : Topic(
    title = "Git Workflows & Collaboration",
    slides = listOf(
        GitWorkflowsSlide
    )
)

object GitWorkflowsSlide : Slide(
    header = "Git Workflows & Branching Strategies",
    summary = {
        +"Different git workflows balance simplicity, control, and team collaboration needs. Choose based on your team size, release cadence, and deployment strategy."
    },
    content = {
        h4 { +"1. GitHub Flow (Simple & Modern)" }

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
                <text x="320" y="55" text-anchor="middle" font-size="10" fill="#7C3AED" font-weight="bold">Pull Request</text>
                <text x="320" y="68" text-anchor="middle" font-size="9" fill="#7C3AED">Code Review</text>
                <circle cx="320" cy="90" r="8" fill="#7C3AED" stroke="#fff" stroke-width="2"/>

                <!-- Feature branch 2 -->
                <line x1="440" y1="90" x2="440" y2="140" stroke="#F59E0B" stroke-width="3"/>
                <line x1="440" y1="140" x2="680" y2="140" stroke="#F59E0B" stroke-width="3"/>
                <line x1="680" y1="140" x2="680" y2="90" stroke="#F59E0B" stroke-width="3" stroke-dasharray="5,5"/>
                <text x="560" y="160" text-anchor="middle" font-size="11" font-weight="bold" fill="#D97706">feature/payment</text>
                <circle cx="520" cy="140" r="5" fill="#D97706"/>
                <circle cx="600" cy="140" r="5" fill="#D97706"/>

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
                p { strong { +"Pros:" } }
                ul {
                    li { +"Simple and easy to learn" }
                    li { +"Fast iteration and deployment" }
                    li { +"Works perfectly with CI/CD" }
                    li { +"Single source of truth (main)" }
                }
            },
            right = {
                p { strong { +"Cons:" } }
                ul {
                    li { +"Less control over releases" }
                    li { +"Not ideal for scheduled releases" }
                    li { +"Can be chaotic for large teams" }
                }
            }
        )

        h4 { +"2. Git Flow (Structured & Controlled)" }

        svgDiagram(
            width = 900,
            height = 240,
            svgContent = """
                <!-- Master/Main branch -->
                <line x1="20" y1="40" x2="860" y2="40" stroke="#22C55E" stroke-width="5"/>
                <text x="20" y="25" font-size="12" font-weight="bold" fill="#15803D">main</text>
                <circle cx="80" cy="40" r="6" fill="#15803D"/>
                <circle cx="460" cy="40" r="6" fill="#15803D"/>
                <circle cx="800" cy="40" r="6" fill="#15803D"/>

                <!-- Develop branch -->
                <line x1="20" y1="100" x2="860" y2="100" stroke="#3B82F6" stroke-width="5"/>
                <text x="20" y="85" font-size="12" font-weight="bold" fill="#1E40AF">develop</text>
                <circle cx="80" cy="100" r="5" fill="#1E40AF"/>
                <circle cx="220" cy="100" r="5" fill="#1E40AF"/>
                <circle cx="360" cy="100" r="5" fill="#1E40AF"/>
                <circle cx="580" cy="100" r="5" fill="#1E40AF"/>
                <circle cx="720" cy="100" r="5" fill="#1E40AF"/>

                <!-- Feature branch -->
                <line x1="220" y1="100" x2="220" y2="160" stroke="#FCD34D" stroke-width="3"/>
                <line x1="220" y1="160" x2="360" y2="160" stroke="#FCD34D" stroke-width="3"/>
                <line x1="360" y1="160" x2="360" y2="100" stroke="#FCD34D" stroke-width="3" stroke-dasharray="5,5"/>
                <text x="290" y="180" text-anchor="middle" font-size="10" font-weight="bold" fill="#D97706">feature/xyz</text>
                <circle cx="280" cy="160" r="4" fill="#D97706"/>

                <!-- Release branch -->
                <line x1="580" y1="100" x2="580" y2="70" stroke="#F97316" stroke-width="3"/>
                <line x1="580" y1="70" x2="720" y2="70" stroke="#F97316" stroke-width="3"/>
                <line x1="720" y1="70" x2="720" y2="40" stroke="#F97316" stroke-width="3" stroke-dasharray="5,5"/>
                <line x1="720" y1="70" x2="720" y2="100" stroke="#F97316" stroke-width="3" stroke-dasharray="5,5"/>
                <text x="650" y="65" text-anchor="middle" font-size="10" font-weight="bold" fill="#EA580C">release/1.0</text>
                <circle cx="640" cy="70" r="4" fill="#EA580C"/>

                <!-- Hotfix branch -->
                <line x1="460" y1="40" x2="460" y2="10" stroke="#EF4444" stroke-width="3"/>
                <line x1="460" y1="10" x2="540" y2="10" stroke="#EF4444" stroke-width="3"/>
                <line x1="540" y1="10" x2="540" y2="40" stroke="#EF4444" stroke-width="3" stroke-dasharray="5,5"/>
                <line x1="540" y1="10" x2="540" y2="100" stroke="#EF4444" stroke-width="3" stroke-dasharray="5,5"/>
                <text x="500" y="5" text-anchor="middle" font-size="10" font-weight="bold" fill="#DC2626">hotfix/bug</text>
                <circle cx="500" cy="10" r="4" fill="#DC2626"/>

                <!-- Legend -->
                <text x="20" y="220" font-size="11" fill="#666">Branch Types:</text>
                <rect x="120" y="210" width="15" height="8" fill="#22C55E"/>
                <text x="140" y="218" font-size="10" fill="#666">Production (main)</text>
                <rect x="250" y="210" width="15" height="8" fill="#3B82F6"/>
                <text x="270" y="218" font-size="10" fill="#666">Integration (develop)</text>
                <rect x="390" y="210" width="15" height="8" fill="#FCD34D"/>
                <text x="410" y="218" font-size="10" fill="#666">Features</text>
                <rect x="490" y="210" width="15" height="8" fill="#F97316"/>
                <text x="510" y="218" font-size="10" fill="#666">Releases</text>
                <rect x="590" y="210" width="15" height="8" fill="#EF4444"/>
                <text x="610" y="218" font-size="10" fill="#666">Hotfixes</text>
            """.trimIndent()
        )

        twoColumns(
            left = {
                p { strong { +"Pros:" } }
                ul {
                    li { +"Clear structure for large teams" }
                    li { +"Great for scheduled releases" }
                    li { +"Parallel development streams" }
                    li { +"Hotfix workflow built-in" }
                }
            },
            right = {
                p { strong { +"Cons:" } }
                ul {
                    li { +"More complex to manage" }
                    li { +"Slower feedback loops" }
                    li { +"Can accumulate merge conflicts" }
                    li { +"Overkill for small teams" }
                }
            }
        )

        h4 { +"3. Trunk-Based Development (Continuous Integration)" }

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
                p { strong { +"Pros:" } }
                ul {
                    li { +"Fastest integration" }
                    li { +"Minimizes merge conflicts" }
                    li { +"True continuous integration" }
                    li { +"Simplest workflow" }
                }
            },
            right = {
                p { strong { +"Cons:" } }
                ul {
                    li { +"Requires strong test coverage" }
                    li { +"Needs feature flags for incomplete work" }
                    li { +"High discipline required" }
                    li { +"Less time for thorough code review" }
                }
            }
        )

        importantCard("Choosing the right workflow") {
            +"GitHub Flow works for most modern teams with continuous deployment. "
            +"Git Flow suits teams with scheduled releases and multiple versions in production. "
            +"Trunk-Based Development is ideal for mature teams with excellent testing and CI/CD."
        }
    }
)

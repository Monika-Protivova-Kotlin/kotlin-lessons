package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.*
import kotlinx.html.*

object DeploymentStrategiesTopic : Topic(
    title = "Deployment Strategies",
    slides = listOf(
        BlueGreenDeploymentSlide,
        CanaryDeploymentSlide,
        RollingUpdateDeploymentSlide,
        FeatureFlagsDeploymentSlide
    )
)

object BlueGreenDeploymentSlide : Slide(
    header = "Blue-Green Deployment",
    summary = {
        +"Blue-Green deployment eliminates downtime by running two identical production environments and switching traffic instantly."
    },
    content = {
//        p {
//            +"You maintain two identical production environments (Blue and Green). At any time, only one environment serves live traffic. "
//            +"The deployment process: (1) Blue environment serves 100% of traffic with v1.0, (2) Deploy v2.0 to the idle Green environment, "
//            +"(3) Test Green thoroughly, (4) Switch the load balancer to route all traffic to Green instantly, (5) Monitor for issues, "
//            +"(6) Keep Blue as a rollback option. If problems arise, switch back to Blue immediately."
//        }

        twoColumns(
            left = {
                h4 { +"How It Works" }
                p {
                    +"Two production environments run in parallel. The load balancer controls which environment receives user traffic. "
                    +"Deploy to the inactive environment, verify it works correctly, then flip the switch. "
                    +"The old environment stays ready for instant rollback."
                }
            },
            right = {
                svgDiagram(
                    width = 450,
                    height = 200,
                    svgContent = """
                        <!-- Blue Environment (v1) -->
                        <rect x="20" y="20" width="80" height="60" rx="5" fill="#E3F2FD" stroke="#1976D2" stroke-width="2"/>
                        <text x="60" y="45" text-anchor="middle" font-size="12" font-weight="bold" fill="#0D47A1">Blue</text>
                        <text x="60" y="62" text-anchor="middle" font-size="10" fill="#424242">v1.0</text>

                        <!-- Green Environment (v2) -->
                        <rect x="20" y="100" width="80" height="60" rx="5" fill="#C8E6C9" stroke="#388E3C" stroke-width="2"/>
                        <text x="60" y="125" text-anchor="middle" font-size="12" font-weight="bold" fill="#1B5E20">Green</text>
                        <text x="60" y="142" text-anchor="middle" font-size="10" fill="#424242">v2.0 (new)</text>

                        <!-- Load Balancer -->
                        <rect x="150" y="60" width="80" height="50" rx="5" fill="#FFF9C4" stroke="#F57C00" stroke-width="2"/>
                        <text x="190" y="82" text-anchor="middle" font-size="11" font-weight="bold" fill="#E65100">Load</text>
                        <text x="190" y="97" text-anchor="middle" font-size="11" font-weight="bold" fill="#E65100">Balancer</text>

                        <!-- Traffic arrow to Blue -->
                        <line x1="100" y1="50" x2="150" y2="85" stroke="#1976D2" stroke-width="3" marker-end="url(#arrowhead)"/>

                        <!-- Switch arrow (dashed) -->
                        <line x1="100" y1="130" x2="150" y2="85" stroke="#388E3C" stroke-width="2" stroke-dasharray="5,5" marker-end="url(#arrowhead)"/>
                        <text x="110" y="115" font-size="9" fill="#2E7D32">switch →</text>

                        <!-- Users -->
                        <circle cx="320" cy="85" r="30" fill="#FFEBEE" stroke="#D32F2F" stroke-width="2"/>
                        <text x="320" y="90" text-anchor="middle" font-size="11" font-weight="bold" fill="#C62828">Users</text>

                        <line x1="230" y1="85" x2="290" y2="85" stroke="#666" stroke-width="2" marker-end="url(#arrowhead)"/>
                    """.trimIndent()
                )
            }
        )

        twoColumns(
            left = {
                h4 { +"Advantages" }
                ul {
                    li { +"Zero downtime deployment" }
                    li { +"Instant rollback (just switch back)" }
                    li { +"Test new version in production-like environment" }
                    li { +"Simple and reliable process" }
                    li { +"Reduces deployment risk significantly" }
                }
            },
            right = {
                h4 { +"Disadvantages" }
                ul {
                    li { +"Requires double infrastructure (costly)" }
                    li { +"Database migrations need careful planning" }
                    li { +"Shared resources (DB) must be compatible with both versions" }
                    li { +"Not suitable for stateful applications without planning" }
                }
            }
        )

        importantCard("When to Use") {
            +"Best for applications where you can afford double infrastructure and need absolute reliability. "
            +"Common in financial services, e-commerce, and critical enterprise applications. "
            +"Perfect when you need instant rollback capability and zero downtime is essential."
        }
    }
)

object CanaryDeploymentSlide : Slide(
    header = "Canary Deployment",
    summary = {
        +"Canary deployment gradually rolls out changes to a small percentage of users first, allowing you to detect issues before full deployment."
    },
    content = {
//        p {
//            +"Deploy the new version to a small subset of servers (the 'canary'). "
//            +"The deployment process: (1) Deploy v2.0 to 1-2 canary instances, (2) Route only 5% of traffic to canaries, "
//            +"(3) Monitor error rates, latency, and business metrics closely, (4) If healthy, gradually increase traffic to 25%, then 50%, then 100%, "
//            +"(5) If issues arise, route traffic back to v1.0 immediately. Complete rollout typically takes hours to days."
//        }

        twoColumns(
            left = {
                h4 { +"How It Works" }
                p {
                    +"The canary approach tests new code with real users in production at minimal risk. "
                    +"The router directs a small percentage of requests to the new version while most traffic stays on the stable version. "
                    +"You monitor the canary closely and gradually increase traffic as confidence grows."
                }
            },
            right = {
                svgDiagram(
                    width = 450,
                    height = 200,
                    svgContent = """
                        <!-- Old version instances -->
                        <rect x="20" y="20" width="60" height="40" rx="5" fill="#E3F2FD" stroke="#1976D2" stroke-width="2"/>
                        <text x="50" y="45" text-anchor="middle" font-size="10" fill="#0D47A1">v1.0</text>

                        <rect x="20" y="70" width="60" height="40" rx="5" fill="#E3F2FD" stroke="#1976D2" stroke-width="2"/>
                        <text x="50" y="95" text-anchor="middle" font-size="10" fill="#0D47A1">v1.0</text>

                        <rect x="20" y="120" width="60" height="40" rx="5" fill="#E3F2FD" stroke="#1976D2" stroke-width="2"/>
                        <text x="50" y="145" text-anchor="middle" font-size="10" fill="#0D47A1">v1.0</text>

                        <!-- Canary instance -->
                        <rect x="100" y="70" width="60" height="40" rx="5" fill="#FFF9C4" stroke="#F57C00" stroke-width="3"/>
                        <text x="130" y="95" text-anchor="middle" font-size="10" font-weight="bold" fill="#E65100">v2.0</text>

                        <!-- Load Balancer -->
                        <rect x="190" y="60" width="70" height="50" rx="5" fill="#F3E5F5" stroke="#7B1FA2" stroke-width="2"/>
                        <text x="225" y="82" text-anchor="middle" font-size="10" fill="#4A148C">Router</text>
                        <text x="225" y="97" text-anchor="middle" font-size="9" fill="#4A148C">95% / 5%</text>

                        <!-- Users -->
                        <circle cx="320" cy="85" r="30" fill="#FFEBEE" stroke="#D32F2F" stroke-width="2"/>
                        <text x="320" y="90" text-anchor="middle" font-size="11" fill="#C62828">Users</text>

                        <!-- Traffic arrows -->
                        <line x1="260" y1="85" x2="290" y2="85" stroke="#666" stroke-width="2" marker-end="url(#arrowhead)"/>
                    """.trimIndent()
                )
            }
        )

        twoColumns(
            left = {
                h4 { +"Advantages" }
                ul {
                    li { +"Early issue detection with real user traffic" }
                    li { +"Reduced blast radius (only 5% of users affected)" }
                    li { +"Real production feedback before full rollout" }
                    li { +"No duplicate infrastructure needed" }
                    li { +"Gradual confidence building" }
                }
            },
            right = {
                h4 { +"Disadvantages" }
                ul {
                    li { +"Requires sophisticated traffic routing system" }
                    li { +"Monitoring and metrics complexity" }
                    li { +"Slower rollout than blue-green" }
                    li { +"Need to handle mixed versions" }
                }
            }
        )

        importantCard("When to Use") {
            +"Ideal for high-traffic applications where you want to validate changes with real users before full rollout. "
            +"Common at Netflix, Facebook, Google for large-scale deployments. "
            +"Best when you have good monitoring and can afford slower deployments for safety."
        }
    }
)

object RollingUpdateDeploymentSlide : Slide(
    header = "Rolling Update Deployment",
    summary = {
        +"Rolling updates gradually replace instances one-by-one or in batches, maintaining service availability throughout the deployment."
    },
    content = {
//        p {
//            +"Update instances in your cluster incrementally, one or a few at a time. "
//            +"The deployment process: (1) Remove instance 1 from the load balancer, (2) Update instance 1 to v2.0, "
//            +"(3) Run health checks on instance 1, (4) Add instance 1 back to the pool, (5) Repeat for instances 2, 3, 4, etc., "
//            +"(6) Complete when all instances are updated. Kubernetes does this automatically with configurable batch sizes and wait times."
//        }

        twoColumns(
            left = {
                h4 { +"How It Works" }
                p {
                    +"Instances are updated in a controlled sequence while the service remains available. "
                    +"The load balancer continues routing traffic to healthy instances. "
                    +"Each instance is taken offline, updated, verified, and returned to service before moving to the next."
                }
            },
            right = {
                svgDiagram(
                    width = 450,
                    height = 200,
                    svgContent = """
                        <!-- Stage 1: Initial state -->
                        <text x="20" y="15" font-size="10" fill="#666">Stage 1:</text>
                        <rect x="20" y="20" width="45" height="30" rx="3" fill="#E3F2FD" stroke="#1976D2" stroke-width="2"/>
                        <text x="42" y="40" text-anchor="middle" font-size="9" fill="#0D47A1">v1</text>
                        <rect x="70" y="20" width="45" height="30" rx="3" fill="#E3F2FD" stroke="#1976D2" stroke-width="2"/>
                        <text x="92" y="40" text-anchor="middle" font-size="9" fill="#0D47A1">v1</text>
                        <rect x="120" y="20" width="45" height="30" rx="3" fill="#E3F2FD" stroke="#1976D2" stroke-width="2"/>
                        <text x="142" y="40" text-anchor="middle" font-size="9" fill="#0D47A1">v1</text>
                        <rect x="170" y="20" width="45" height="30" rx="3" fill="#E3F2FD" stroke="#1976D2" stroke-width="2"/>
                        <text x="192" y="40" text-anchor="middle" font-size="9" fill="#0D47A1">v1</text>

                        <!-- Stage 2: One updated -->
                        <text x="20" y="70" font-size="10" fill="#666">Stage 2:</text>
                        <rect x="20" y="75" width="45" height="30" rx="3" fill="#C8E6C9" stroke="#388E3C" stroke-width="2"/>
                        <text x="42" y="95" text-anchor="middle" font-size="9" fill="#1B5E20">v2</text>
                        <rect x="70" y="75" width="45" height="30" rx="3" fill="#E3F2FD" stroke="#1976D2" stroke-width="2"/>
                        <text x="92" y="95" text-anchor="middle" font-size="9" fill="#0D47A1">v1</text>
                        <rect x="120" y="75" width="45" height="30" rx="3" fill="#E3F2FD" stroke="#1976D2" stroke-width="2"/>
                        <text x="142" y="95" text-anchor="middle" font-size="9" fill="#0D47A1">v1</text>
                        <rect x="170" y="75" width="45" height="30" rx="3" fill="#E3F2FD" stroke="#1976D2" stroke-width="2"/>
                        <text x="192" y="95" text-anchor="middle" font-size="9" fill="#0D47A1">v1</text>

                        <!-- Stage 3: All updated -->
                        <text x="20" y="125" font-size="10" fill="#666">Stage 3:</text>
                        <rect x="20" y="130" width="45" height="30" rx="3" fill="#C8E6C9" stroke="#388E3C" stroke-width="2"/>
                        <text x="42" y="150" text-anchor="middle" font-size="9" fill="#1B5E20">v2</text>
                        <rect x="70" y="130" width="45" height="30" rx="3" fill="#C8E6C9" stroke="#388E3C" stroke-width="2"/>
                        <text x="92" y="150" text-anchor="middle" font-size="9" fill="#1B5E20">v2</text>
                        <rect x="120" y="130" width="45" height="30" rx="3" fill="#C8E6C9" stroke="#388E3C" stroke-width="2"/>
                        <text x="142" y="150" text-anchor="middle" font-size="9" fill="#1B5E20">v2</text>
                        <rect x="170" y="130" width="45" height="30" rx="3" fill="#C8E6C9" stroke="#388E3C" stroke-width="2"/>
                        <text x="192" y="150" text-anchor="middle" font-size="9" fill="#1B5E20">v2</text>
                    """.trimIndent()
                )
            }
        )

        twoColumns(
            left = {
                h4 { +"Advantages" }
                ul {
                    li { +"No duplicate infrastructure needed" }
                    li { +"Gradual rollout reduces risk" }
                    li { +"Can pause deployment if issues detected" }
                    li { +"Built into Kubernetes and most orchestrators" }
                    li { +"Resource efficient" }
                }
            },
            right = {
                h4 { +"Disadvantages" }
                ul {
                    li { +"Multiple versions running simultaneously" }
                    li { +"Slower than blue-green deployment" }
                    li { +"Rollback requires another rolling update" }
                    li { +"Requires backward-compatible versions" }
                }
            }
        )

        importantCard("When to Use") {
            +"Default deployment strategy for Kubernetes and container orchestration platforms. "
            +"Provides a good balance between safety and resource efficiency. "
            +"Best when versions are backward compatible and you can tolerate gradual rollout times."
        }
    }
)

object FeatureFlagsDeploymentSlide : Slide(
    header = "Feature Flags (Feature Toggles)",
    summary = {
        +"Feature flags decouple code deployment from feature release, allowing you to control feature visibility independently."
    },
    content = {
//        p {
//            +"Deploy code with new features wrapped in conditional checks (feature flags). Features are initially OFF in production. "
//            +"The deployment process: (1) Wrap new feature code in an 'if (featureFlag.enabled)' check, (2) Deploy code to production with feature OFF, "
//            +"(3) Monitor deployment health, (4) Toggle feature ON via a dashboard or configuration, (5) Monitor feature usage and metrics, "
//            +"(6) If issues arise, toggle OFF instantly without redeploying. Deployment is separated from feature release."
//        }

        twoColumns(
            left = {
                h4 { +"How It Works" }
                p {
                    +"Code deployment and feature activation are independent. Deploy the code first, then control when users see new features. "
                    +"Toggle flags ON/OFF instantly through a management interface. This enables safe experimentation and gradual rollouts."
                }

                kotlinPlayground(
                    code = """
                        if (featureFlags.isEnabled("newCheckout")) {
                            processNewCheckout(order)
                        } else {
                            processOldCheckout(order)
                        }
                    """.trimIndent(),
                    executable = false
                )
            },
            right = {
                svgDiagram(
                    width = 450,
                    height = 200,
                    svgContent = """
                        <!-- Deploy -->
                        <rect x="20" y="20" width="100" height="50" rx="5" fill="#E3F2FD" stroke="#1976D2" stroke-width="2"/>
                        <text x="70" y="42" text-anchor="middle" font-size="11" font-weight="bold" fill="#0D47A1">Deploy Code</text>
                        <text x="70" y="58" text-anchor="middle" font-size="9" fill="#424242">v2.0 (feature OFF)</text>

                        <line x1="120" y1="45" x2="160" y2="45" stroke="#666" stroke-width="2" marker-end="url(#arrowhead)"/>

                        <!-- Feature Flag -->
                        <rect x="160" y="20" width="100" height="50" rx="5" fill="#FFF9C4" stroke="#F57C00" stroke-width="2"/>
                        <text x="210" y="42" text-anchor="middle" font-size="11" font-weight="bold" fill="#E65100">Feature Flag</text>
                        <text x="210" y="58" text-anchor="middle" font-size="9" fill="#424242">Toggle ON/OFF</text>

                        <line x1="260" y1="45" x2="300" y2="45" stroke="#666" stroke-width="2" marker-end="url(#arrowhead)"/>

                        <!-- Release -->
                        <rect x="300" y="20" width="100" height="50" rx="5" fill="#C8E6C9" stroke="#388E3C" stroke-width="2"/>
                        <text x="350" y="42" text-anchor="middle" font-size="11" font-weight="bold" fill="#1B5E20">Feature Live</text>
                        <text x="350" y="58" text-anchor="middle" font-size="9" fill="#424242">Visible to users</text>

                        <!-- Timeline -->
                        <line x1="20" y1="100" x2="400" y2="100" stroke="#999" stroke-width="1"/>
                        <text x="70" y="115" text-anchor="middle" font-size="9" fill="#666">Day 1</text>
                        <text x="210" y="115" text-anchor="middle" font-size="9" fill="#666">Day 1 (later)</text>
                        <text x="350" y="115" text-anchor="middle" font-size="9" fill="#666">Day 2</text>
                    """.trimIndent()
                )
            }
        )

        twoColumns(
            left = {
                h4 { +"Advantages" }
                ul {
                    li { +"Decouple deployment from feature release" }
                    li { +"Instant rollback (just toggle OFF)" }
                    li { +"A/B testing and gradual rollouts" }
                    li { +"Test in production safely" }
                    li { +"Enable features for specific users or percentages" }
                }
            },
            right = {
                h4 { +"Disadvantages" }
                ul {
                    li { +"Code complexity with conditionals" }
                    li { +"Technical debt if flags not cleaned up" }
                    li { +"Requires feature flag infrastructure" }
                    li { +"Multiple code paths to test and maintain" }
                }
            }
        )

        importantCard("When to Use") {
            +"Extremely powerful for continuous delivery and experimentation. "
            +"Use for risky features, A/B tests, or gradual rollouts to specific user segments. "
            +"Clean up old flags after features are stable to avoid code bloat. Popular tools: LaunchDarkly, Split.io, Unleash."
        }
    }
)

package com.motycka.edu.content.topics.backend.deployment

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.hintCard
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.twoColumns
import kotlinx.html.*

object DockerBasicsTopic : Topic(
    title = "Docker Basics",
    slides = listOf(
        DockerIntroductionSlide,
        DockerConceptsSlide,
        DockerBenefitsSlide,
        DockerCommandsSlide
    )
)

object DockerIntroductionSlide : Slide(
    header = "What is Docker?",
    summary = {
        +"Docker is a platform for developing, shipping, and running applications in containers"
    },
    content = {
        p {
            +"Docker enables you to package your application with all its dependencies into a standardized unit called a container. "
            +"Containers are lightweight, standalone, and executable packages that include everything needed to run your software."
        }
        p {
            h4 { +"Key Concepts" }

            ul {
                li {
                    strong { +"Container" }
                    +" - A running instance of a Docker image, isolated from the host system"
                }
                li {
                    strong { +"Image" }
                    +" - A read-only template containing application code, runtime, libraries, and dependencies"
                }
                li {
                    strong { +"Dockerfile" }
                    +" - A text file with instructions for building a Docker image"
                }
                li {
                    strong { +"Registry" }
                    +" - A storage and distribution system for Docker images (e.g., Docker Hub)"
                }
            }
        }
        hintCard {
            +"Think of a Docker image as a recipe (Dockerfile), and a container as the meal you cook from that recipe."
        }
    }
)

object DockerConceptsSlide : Slide(
    header = "Docker Architecture",
    summary = {
        +"Understanding Docker's core components and how they work together"
    },
    content = {
        twoColumns(
            left = {
                p {
                    h4 { +"Docker Components" }
                    ul {
                        li {
                            strong { +"Docker Engine" }
                            +" - The runtime that builds and runs containers"
                        }
                        li {
                            strong { +"Docker Client" }
                            +" - The CLI tool ("; inlineCode("docker"); +" command) you use to interact with Docker"
                        }
                        li {
                            strong { +"Docker Daemon" }
                            +" - Background service that manages containers"
                        }
                        li {
                            strong { +"Docker Hub" }
                            +" - Public registry for sharing Docker images"
                        }
                    }
                }

                p {
                    h4 { +"How Docker Works" }
                    ol {
                        li { +"You write a Dockerfile describing your application" }
                        li { +"Docker builds an image from the Dockerfile" }
                        li { +"You run a container from the image" }
                        li { +"The container runs your application in isolation" }
                    }
                }
            },
            right = {
                p {
                    h4 { +"Containers vs Virtual Machines" }
                    ul {
                        li { +"Containers share the host OS kernel (lightweight)" }
                        li { +"VMs include a full OS (heavy)" }
                        li { +"Containers start in seconds, VMs take minutes" }
                        li { +"Containers use less disk space and memory" }
                    }
                }
            }
        )
    }
)

object DockerBenefitsSlide : Slide(
    header = "Why Use Docker?",
    summary = {
        +"Docker solves the 'it works on my machine' problem and more"
    },
    content = {
        p { h4 { +"Benefits"}
        ul {
            li {
                strong { +"Consistency" }
                +" - Same environment from development to production"
            }
            li {
                strong { +"Isolation" }
                +" - Applications run independently without conflicts"
            }
            li {
                strong { +"Portability" }
                +" - Run anywhere Docker is installed"
            }
            li {
                strong { +"Efficiency" }
                +" - Lightweight, fast startup, efficient resource usage"
            }
            li {
                strong { +"Scalability" }
                +" - Easy to replicate and scale applications"
            }
            li {
                strong { +"Version Control" }
                +" - Images can be versioned and rolled back"
            }
        }
        }

        p {
            h4 { +"Use Cases" }
            ul {
                li { +"Microservices architecture" }
                li { +"CI/CD pipelines" }
                li { +"Development environments" }
                li { +"Application deployment" }
                li { +"Testing in isolated environments" }
                li { +"Cloud-native applications" }
            }
        }
    }
)

object DockerCommandsSlide : Slide(
    header = "Essential Docker Commands",
    summary = {
        +"Common Docker CLI commands for working with images and containers"
    },
    content = {
        twoColumns(
            left = {
                p {
                    h4 { +"Image Commands" }
                    pre {
                        +"""
                    # Build an image from Dockerfile
                    docker build -t myapp:1.0 .
        
                    # List images
                    docker images
        
                    # Pull image from registry
                    docker pull postgres:15
        
                    # Remove an image
                    docker rmi myapp:1.0
                    """.trimIndent()
                    }
                }
            },
            right = {
                p {
                    h4 { +"Container Commands" }
                    pre {
                        +"""
                    # Run a container
                    docker run -d -p 8080:8080 --name myapp myapp:1.0
        
                    # List running containers
                    docker ps
        
                    # List all containers (including stopped)
                    docker ps -a
        
                    # Stop a container
                    docker stop myapp
        
                    # Start a stopped container
                    docker start myapp
        
                    # Remove a container
                    docker rm myapp
        
                    # View container logs
                    docker logs myapp
        
                    # Execute command in running container
                    docker exec -it myapp /bin/bash
                    """.trimIndent()
                    }
                }
            }
        )
    }
)

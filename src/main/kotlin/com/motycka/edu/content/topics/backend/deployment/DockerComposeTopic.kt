package com.motycka.edu.content.topics.backend.deployment

import com.motycka.edu.builders.codeOrderedList
import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.infoCard
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.twoColumns
import kotlinx.html.*

object DockerComposeTopic : Topic(
    title = "Docker Compose",
    slides = listOf(
        DockerComposeIntroSlide,
        DockerComposeFileSlide,
        SpringBootWithDatabaseSlide,
        DockerComposeCommandsSlide
    )
)

object DockerComposeIntroSlide : Slide(
    header = "What is Docker Compose?",
    summary = {
        +"Docker Compose is a tool for defining and running multi-container Docker applications"
    },
    content = {
        p {
            +"Docker Compose uses a YAML file to configure your application's services, networks, and volumes. "
            +"With a single command, you can create and start all services from your configuration."
        }
        twoColumns(
            left = {
                p {
                    h4 { +"Why Docker Compose?" }
                    ul {
                        li {
                            strong { +"Multi-container Management" }
                            +" - Run multiple containers as a single application"
                        }
                        li {
                            strong { +"Simple Configuration" }
                            +" - Define everything in one "; inlineCode("docker-compose.yml"); +" file"
                        }
                        li {
                            strong { +"Networking" }
                            +" - Automatic network creation for service communication"
                        }
                        li {
                            strong { +"Development Workflow" }
                            +" - Perfect for local development environments"
                        }
                    }
                }
            },
            right = {
                infoCard {
                    +"Common use case: Running your Spring Boot application with PostgreSQL, Redis, and other services locally."
                }
            }
        )
    }
)

object DockerComposeFileSlide : Slide(
    header = "docker-compose.yml Structure",
    summary = {
        +"Understanding the Docker Compose file format"
    },
    content = {
        twoColumns(
            left = {
                p {
                    h4 { +"Basic docker-compose.yml" }
                    pre {
                        +"""
                    version: '3.8'
        
                    services:
                      app:
                        build: .
                        ports:
                          - "8080:8080"
                        environment:
                          - SPRING_PROFILES_ACTIVE=dev
                        depends_on:
                          - db
        
                      db:
                        image: postgres:15
                        ports:
                          - "5432:5432"
                        environment:
                          - POSTGRES_DB=myapp
                          - POSTGRES_USER=user
                          - POSTGRES_PASSWORD=password
                        volumes:
                          - postgres_data:/var/lib/postgresql/data
        
                    volumes:
                      postgres_data:
                    """.trimIndent()
                    }
                }
            },
            right = {
                p {
                    h4 { +"Key Sections" }
                    ul {
                        li { inlineCode("services"); +" - Defines containers to run" }
                        li { inlineCode("volumes"); +" - Persistent storage" }
                        li { inlineCode("networks"); +" - Custom networks (optional)" }
                    }
                }
            }
        )

    }
)

object SpringBootWithDatabaseSlide : Slide(
    header = "Spring Boot with Database",
    summary = {
        +"Complete example of Spring Boot application with PostgreSQL"
    },
    content = {
        pre {
            +"""
            version: '3.8'

            services:
              app:
                build:
                  context: .
                  dockerfile: Dockerfile
                container_name: myapp
                ports:
                  - "8080:8080"
                environment:
                  - SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/myapp
                  - SPRING_DATASOURCE_USERNAME=user
                  - SPRING_DATASOURCE_PASSWORD=password
                  - SPRING_JPA_HIBERNATE_DDL_AUTO=update
                depends_on:
                  db:
                    condition: service_healthy
                networks:
                  - app-network

              db:
                image: postgres:15-alpine
                container_name: myapp-db
                ports:
                  - "5432:5432"
                environment:
                  - POSTGRES_DB=myapp
                  - POSTGRES_USER=user
                  - POSTGRES_PASSWORD=password
                volumes:
                  - postgres_data:/var/lib/postgresql/data
                networks:
                  - app-network
                healthcheck:
                  test: ["CMD-SHELL", "pg_isready -U user"]
                  interval: 10s
                  timeout: 5s
                  retries: 5

            volumes:
              postgres_data:

            networks:
              app-network:
                driver: bridge
            """.trimIndent()
        }
    },
    fontSize = "65%"
)

object DockerComposeCommandsSlide : Slide(
    header = "Docker Compose Commands",
    summary = {
        +"Essential commands for working with Docker Compose"
    },
    content = {
        codeOrderedList(
            "docker-compose up"  to {
                +"Builds, (re)creates, starts, and attaches to containers for a service"
            },
            "docker-compose up -d" to {
                +"Starts services in detached mode (in the background)"
            },
            "docker-compose build" to {
                +"Builds or rebuilds services"
            },
            "docker-compose stop" to {
                +"Stops running containers without removing them"
            },
            "docker-compose down" to {
                +"Stops and removes containers, networks, volumes, and images created by "; inlineCode("up"); +"."
            },
            "docker-compose down -v" to {
                +"Stops and removes containers, networks, and volumes"
            },
            "docker-compose restart" to {
                +"Restarts services"
            },
            "docker-compose ps" to {
                +"Lists containers"
            },
            "docker-compose logs" to {
                +"Views output from containers"
            },
            "docker-compose logs -f app" to {
                +"Follows logs for the 'app' service"
            },
            "docker-compose exec app bash" to {
                +"Executes a command in a running 'app' service container"
            },
            "docker-compose up -d --scale app=3" to {
                +"Scales the 'app' service to 3 instances"
            }
        )
    },
    fontSize = "70%"
)

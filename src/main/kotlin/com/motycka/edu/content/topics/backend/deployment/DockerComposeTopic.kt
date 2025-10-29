package com.motycka.edu.content.topics.backend.deployment

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
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

        p { strong { +"Why Docker Compose?" } }
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

        blockQuote {
            +"Common use case: Running your Spring Boot application with PostgreSQL, Redis, and other services locally."
        }
    }
)

object DockerComposeFileSlide : Slide(
    header = "docker-compose.yml Structure",
    summary = {
        +"Understanding the Docker Compose file format"
    },
    content = {
        p { strong { +"Basic docker-compose.yml:" } }
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

        p { highlight("Key Sections:") }
        ul {
            li { inlineCode("services"); +" - Defines containers to run" }
            li { inlineCode("volumes"); +" - Persistent storage" }
            li { inlineCode("networks"); +" - Custom networks (optional)" }
        }
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
        p { highlight("Basic Commands:") }
        pre {
            +"""
            # Start all services (build if needed)
            docker-compose up

            # Start in detached mode (background)
            docker-compose up -d

            # Build/rebuild services
            docker-compose build

            # Stop all services
            docker-compose stop

            # Stop and remove containers
            docker-compose down

            # Stop and remove containers, volumes, and networks
            docker-compose down -v

            # View logs
            docker-compose logs

            # Follow logs for a specific service
            docker-compose logs -f app

            # List running services
            docker-compose ps

            # Execute command in a running service
            docker-compose exec app bash

            # Scale a service
            docker-compose up -d --scale app=3
            """.trimIndent()
        }

        blockQuote {
            +"💡 Use "; inlineCode("docker-compose up -d"); +" for development and "
            inlineCode("docker-compose down -v"); +" to clean up everything including volumes."
        }
    },
    fontSize = "80%"
)

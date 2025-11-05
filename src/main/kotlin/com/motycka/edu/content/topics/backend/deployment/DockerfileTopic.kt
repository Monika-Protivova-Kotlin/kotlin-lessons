package com.motycka.edu.content.topics.backend.deployment

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.twoColumns
import kotlinx.html.*

object DockerfileTopic : Topic(
    title = "Dockerfile",
    slides = listOf(
        DockerfileIntroSlide,
        DockerfileInstructionsSlide,
        SpringBootDockerfileSlide,
        MultistageBuildsSlide
    )
)

object DockerfileIntroSlide : Slide(
    header = "What is a Dockerfile?",
    summary = {
        +"A Dockerfile is a text document containing instructions for building a Docker image"
    },
    content = {
        p {
            +"A Dockerfile defines the steps Docker takes to create an image. "
            +"Each instruction creates a layer in the image, and Docker caches these layers for efficiency."
        }
        twoColumns(
            left = {
                p {
                    h4 { +"Basic Dockerfile Structure" }
                    pre {
                        +"""
                        # Start from a base image
                        FROM openjdk:17-jdk-slim
            
                        # Set the working directory
                        WORKDIR /app
            
                        # Copy files into the image
                        COPY target/myapp.jar app.jar
            
                        # Expose a port
                        EXPOSE 8080
            
                        # Define the command to run
                        ENTRYPOINT ["java", "-jar", "app.jar"]
                        """.trimIndent()
                    }
                }
            },
            right = {
                p {
                    h4 { +"Key Principles" }
                    ul {
                        li { +"Instructions are executed in order" }
                        li { +"Each instruction creates a new layer" }
                        li { +"Layers are cached for faster rebuilds" }
                        li { +"Order matters for cache optimization" }
                    }
                }
            }
        )
    }
)

object DockerfileInstructionsSlide : Slide(
    header = "Common Dockerfile Instructions",
    content = {
        p { highlight("Essential Instructions:") }
        ul {
            li {
                strong { inlineCode("FROM") }
                +" - Sets the base image (must be first instruction)"
                pre { +"FROM openjdk:17-jdk-slim" }
            }
            li {
                strong { inlineCode("WORKDIR") }
                +" - Sets the working directory for subsequent instructions"
                pre { +"WORKDIR /app" }
            }
            li {
                strong { inlineCode("COPY") }
                +" - Copies files from host to image"
                pre { +"COPY target/*.jar app.jar" }
            }
            li {
                strong { inlineCode("RUN") }
                +" - Executes commands during image build"
                pre { +"RUN apt-get update && apt-get install -y curl" }
            }
            li {
                strong { inlineCode("EXPOSE") }
                +" - Documents which port the container listens on"
                pre { +"EXPOSE 8080" }
            }
            li {
                strong { inlineCode("ENV") }
                +" - Sets environment variables"
                pre { +"ENV SPRING_PROFILES_ACTIVE=prod" }
            }
            li {
                strong { inlineCode("ENTRYPOINT") }
                +" - Defines the executable to run"
                pre { +"ENTRYPOINT [\"java\", \"-jar\", \"app.jar\"]" }
            }
            li {
                strong { inlineCode("CMD") }
                +" - Provides default arguments (can be overridden)"
                pre { +"CMD [\"--spring.profiles.active=dev\"]" }
            }
        }
    },
    fontSize = "75%"
)

object SpringBootDockerfileSlide : Slide(
    header = "Dockerfile for Spring Boot",
    summary = {
        +"Creating an optimized Dockerfile for Spring Boot applications"
    },
    content = {
        p { strong { +"Simple Spring Boot Dockerfile:" } }
        pre {
            +"""
            FROM openjdk:17-jdk-slim

            WORKDIR /app

            # Copy the JAR file
            COPY build/libs/*.jar app.jar

            # Expose the application port
            EXPOSE 8080

            # Set JVM options
            ENV JAVA_OPTS="-Xmx512m -Xms256m"

            # Run the application
            ENTRYPOINT ["sh", "-c", "java ${'$'}JAVA_OPTS -jar app.jar"]
            """.trimIndent()
        }

        p { strong { +"Building and Running:" } }
        pre {
            +"""
            # Build the application first
            ./gradlew build

            # Build Docker image
            docker build -t myapp:1.0 .

            # Run the container
            docker run -p 8080:8080 myapp:1.0
            """.trimIndent()
        }

        blockQuote {
            +"💡 For Gradle, use "; inlineCode("build/libs/*.jar"); +". "
            +"For Maven, use "; inlineCode("target/*.jar"); +"."
        }
    }
)

object MultistageBuildsSlide : Slide(
    header = "Multi-stage Builds",
    summary = {
        +"Use multi-stage builds to create smaller, more secure images"
    },
    content = {
        p {
            +"Multi-stage builds allow you to build your application in one stage and copy only the necessary artifacts "
            +"to the final image, resulting in smaller images."
        }

        pre {
            +"""
            # Build stage
            FROM gradle:8-jdk17 AS build
            WORKDIR /app
            COPY . .
            RUN gradle build --no-daemon

            # Runtime stage
            FROM openjdk:17-jdk-slim
            WORKDIR /app

            # Copy only the built JAR from build stage
            COPY --from=build /app/build/libs/*.jar app.jar

            EXPOSE 8080
            ENTRYPOINT ["java", "-jar", "app.jar"]
            """.trimIndent()
        }

        p { highlight("Benefits:") }
        ul {
            li { +"✓ Smaller final image (no build tools)" }
            li { +"✓ More secure (fewer attack surfaces)" }
            li { +"✓ Faster deployments" }
            li { +"✓ No need to build locally" }
        }

        blockQuote {
            +"Multi-stage builds are the recommended approach for production applications."
        }
    }
)

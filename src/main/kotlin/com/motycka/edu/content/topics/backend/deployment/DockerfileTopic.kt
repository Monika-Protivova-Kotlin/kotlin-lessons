package com.motycka.edu.content.topics.backend.deployment

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.hintCard
import com.motycka.edu.model.infoCard
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
//        p { h4 { +"Essential Instructions:" } }
        twoColumns(
            left = {
                inlineCode("FROM")
                +" - Sets the base image (must be first instruction)"
            },
            right = {
                pre { +"FROM openjdk:17-jdk-slim" }
            }
        )
        twoColumns(
            left = {
                strong { inlineCode("WORKDIR") }
                +" - Sets the working directory for subsequent instructions"
            },
            right = {
                pre { +"WORKDIR /app" }
            }
        )
        twoColumns(
            left = {
                strong { inlineCode("COPY") }
                +" - Copies files from host to image"
            },
            right = {
                pre { +"COPY target/*.jar app.jar" }
            }
        )
        twoColumns(
            left = {
                strong { inlineCode("RUN") }
                +" - Executes commands during image build"
            },
            right = {
                pre { +"RUN apt-get update && apt-get install -y curl" }
            }
        )
        twoColumns(
            left = {
                strong { inlineCode("EXPOSE") }
                +" - Documents which port the container listens on"
            },
            right = {
                pre { +"EXPOSE 8080" }
            }
        )
        twoColumns(
            left = {
                strong { inlineCode("ENV") }
                +" - Sets environment variables"
            },
            right = {
                pre { +"ENV SPRING_PROFILES_ACTIVE=prod" }
            }
        )
        twoColumns(
            left = {
                strong { inlineCode("ENTRYPOINT") }
                +" vs "
                strong { inlineCode("CMD") }
            },
            right = {
                pre { +"ENTRYPOINT [\"java\", \"-jar\", \"app.jar\"]\nCMD [\"--spring.profiles.active=dev\"]" }
            }
        )
        twoColumns(
            left = {
                strong { inlineCode("ENTRYPOINT") }
                +" - Defines the executable to run"
            },
            right = {
                pre { +"CMD [\"--spring.profiles.active=dev\"]" }
            }
        )
        twoColumns(
            left = {
                strong { inlineCode("ADD") }
                +" - Similar to COPY but with extra features (e.g., extracting tar files, fetching from URLs)"
            },
            right = {
                pre { +"ADD https://example.com/file.tar.gz /app/" }
            }
        )
        twoColumns(
            left = {
                strong { inlineCode("VOLUME") }
                +" - Creates a mount point for external storage"
            },
            right = {
                pre { +"VOLUME /data" }
            }
        )
    },
    fontSize = "75%"
)

object SpringBootDockerfileSlide : Slide(
    header = "Dockerfile for Spring Boot",
    summary = {
        +"Creating an optimized Dockerfile for Spring Boot applications"
    },
    content = {
        twoColumns(
            left = {
                p {
                    h4 { +"Simple Spring Boot Dockerfile" }
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
                }
            },
            right = {
                p {
                    h4 { +"Building and Running" }
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
                }
            }
        )
    }
)

object MultistageBuildsSlide : Slide(
    header = "Multi-stage Builds",
    summary = {
        +"Use multi-stage builds to create smaller, more secure images"
    },
    content = {
        twoColumns(
            left = {
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
            },
            right = {
                p {
                    h4 { +"Benefits" }
                    ul {
                        li { +"✓ Smaller final image (no build tools)" }
                        li { +"✓ More secure (fewer attack surfaces)" }
                        li { +"✓ Faster deployments" }
                        li { +"✓ No need to build locally" }
                    }
                }

                infoCard {
                    +"Multi-stage builds are the recommended approach for production applications."
                }
            }
        )
    }
)

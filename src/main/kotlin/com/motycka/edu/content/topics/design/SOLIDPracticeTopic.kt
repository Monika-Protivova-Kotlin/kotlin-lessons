package com.motycka.edu.content.topics.design

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import kotlinx.html.*

object SOLIDPracticeTopic : Topic(
    title = "Practice",
    slides = listOf(
        SOLIDPracticeSlide
    )
)

object SOLIDPracticeSlide : Slide(
    header = "Assignment 08: SOLID Principles & Dependency Injection",
    summary = {
        +"Refactor the Fantasy Space Game using SOLID principles, service layer architecture, and dependency injection"
    },
    content = {
        h4 { +"The Challenge" }
        p {
            +"Transform your game from procedural design into a well-architected application."
        }

        h4 { +"Architecture Transformation" }
        p { +"You will apply:" }
        ul {
            li { strong { +"Service Layer Pattern" }; +" - Separate business logic into focused services" }
            li { strong { +"Dependency Injection" }; +" - Inject dependencies via constructors" }
            li { strong { +"SOLID Principles" }; +" - Single Responsibility, Dependency Inversion, Interface Segregation" }
        }

//        h4 { +"Your Tasks" }
//        ol {
//            li {
//                strong { +"Create Service Interfaces" }
//                ul {
//                    li { +"CharacterService - Load and return characters" }
//                    li { +"MatchService - Run matches between characters" }
//                    li { +"LeaderboardService - Calculate and save leaderboard" }
//                }
//            }
//            li {
//                strong { +"Implement Service Classes" }
//                ul {
//                    li { +"CharacterServiceImpl - Load from CSV" }
//                    li { +"MatchServiceImpl - Wrapper around Match logic" }
//                    li { +"LeaderboardServiceImpl - Calculate positions and save to CSV" }
//                }
//            }
//            li {
//                strong { +"Create FantasySpaceGame Application Class" }
//                ul {
//                    li { +"Accept services via constructor injection" }
//                    li { +"Coordinate tournament using injected services" }
//                    li { +"Depend on interfaces, not implementations" }
//                }
//            }
//            li {
//                strong { +"Wire Everything Together" }
//                ul {
//                    li { +"Create service implementations in main()" }
//                    li { +"Inject into FantasySpaceGame" }
//                    li { +"Run tournament and save results" }
//                }
//            }
//        }

        h4 { +"Key Benefits" }
        ul {
            li { +"✅ Testability - Easy to test with mocks" }
            li { +"✅ Maintainability - Changes are localized" }
            li { +"✅ Flexibility - Easy to swap implementations" }
            li { +"✅ Readability - Clear separation of concerns" }
        }
    }
)

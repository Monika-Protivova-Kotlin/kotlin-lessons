package com.motycka.edu.content.topics.basics.io

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import kotlinx.html.*

object IOAndExceptionsAssignmentTopic : Topic(
    title = "Assignment: Fantasy.Space File I/O",
    slides = listOf(
        IOAndExceptionsAssignmentSlide,
    )
)

object IOAndExceptionsAssignmentSlide : Slide(
    header = "Assignment: Fantasy.Space File I/O",
    summary = {
        +"Load characters from CSV file and save tournament leaderboard to CSV."
    },
    content = {
        p { +"In this assignment, you'll add file I/O capabilities to the Fantasy.Space game:" }
        ul {
            li { +"Create "; inlineCode("FileUtils"); +" object with generic CSV read/write functions" }
            li { +"Implement "; inlineCode("readCsvFile(fileName: String): List<List<String>>"); +" - generic CSV reader" }
            li { +"Implement "; inlineCode("saveCsvFile(data: List<List<String>>, fileName: String): File"); +" - generic CSV writer" }
            li { +"Create "; inlineCode("loadCharacters(fileName: String): List<Character>"); +" - domain-specific function" }
            li { +"Create "; inlineCode("saveLeaderboard(leaderboard: List<LeaderboardEntry>, fileName: String): File") }
            li { +"Load characters from "; inlineCode("characters.csv"); +" in "; inlineCode("src/main/resources/") }
            li { +"Run tournament and save results to "; inlineCode("results.csv") }
        }
        br()
        p { strong { +"Key Concepts:" } }
        ul {
            li { strong { +"Resource Loading" }; +" - Accessing files from classpath" }
            li { strong { +"CSV Parsing" }; +" - String manipulation with split() and column indexing" }
            li { strong { +"File Operations" }; +" - File.readLines(), File.writeText()" }
            li { strong { +"Type Conversion" }; +" - toInt(), CharacterClass.valueOf()" }
        }
    }
)

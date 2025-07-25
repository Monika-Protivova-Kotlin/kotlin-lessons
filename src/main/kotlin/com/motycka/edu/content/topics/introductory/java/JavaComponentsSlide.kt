package com.motycka.edu.content.topics.introductory.java

import com.motycka.edu.model.Slide
import kotlinx.html.blockQuote
import kotlinx.html.em
import kotlinx.html.h4
import kotlinx.html.hr
import kotlinx.html.p
import kotlinx.html.strong

object JavaComponentsSlide : Slide(
    header = "Java has two main components ...",
    content = {
        h4 { +"Java Runtime Environment (JRE)" }
        p {
            +"The JRE provides the libraries, the Java Virtual Machine (JVM), and other components needed to run applications written in Java. It does not include developer tools such as compilers and debuggers."
        }
        p {
            em { +"As a user, you would use the JRE to run Java programs on your system." }
        }
        hr()
        h4 { +"Java Development Kit (JDK)" }
        p {
            +"The JDK includes the JRE as well as a set of development tools for writing and running Java programs. These tools include the Java compiler (javac), an archiver (jar), a documentation generator (javadoc), and other tools needed in Java development."
        }
        p {
            +"Since open-sourcing, multiple implementations of JDK have existed, including "
            strong { +"Oracle JDK" }
            +", "
            strong { +"OpenJDK" }
            +", "
            strong { +"Amazon Corretto" }
            +", and others."
        }
        blockQuote {
            +"As a programmer, you would use the JDK to develop Java applications and JRE to run them."
        }
    },
    textAlign = "center",
    fontSize = "100%"
)

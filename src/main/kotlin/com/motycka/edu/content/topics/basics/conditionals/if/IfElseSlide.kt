package com.motycka.edu.content.topics.basics.conditionals.`if`

import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.p
import kotlinx.html.strong

object IfElseSlide : Slide(
    header = "if ... else if ... else",
    fontSize = "70%",
    summary = {
        +"Because Kotlin, like Java is based on C++ syntax, you can expect similar control flow statements."
    },
    content = {
        twoColumns(
            left = {
                p {
                    +"You can write just simple "
                    strong { +"if" }
                    +" statement."
                }
                kotlinPlayground(
                    code = """
                        if (a <= b) {
                            // execute if condition is met
                        }
                    """,
                    executable = false
                )
            },
            right = {
                p {
                    +"The "
                    strong { +"else" }
                    +" branch is not required, but it is highly recommendable."
                }
                kotlinPlayground(
                    code = """
                        if (a <= b) {
                            // execute if condition is met
                        } else {
                            // execute if condition is NOT met
                        }
                    """,
                    executable = false
                )
            }
        )
        p {
            +"You can also evaluate multiple conditions with else if."
        }
        kotlinPlayground(
            code = """
                if (a < b) {
                	// execute if first condition is met
                } else if (a == b) {
                	// execute if second condition is met
                } else if (a == null) {
                	// execute if third condition is met
                } else {
                	// execute if no condition is NOT met
                }
            """,
            executable = false
        )
    }
)

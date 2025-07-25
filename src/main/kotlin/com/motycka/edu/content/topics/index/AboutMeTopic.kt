package com.motycka.edu.content.topics.index

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import kotlinx.html.*

class AboutMeTopic : Topic(
    title = "About Me",
    slides = listOf(
        Slide(
            header = "About Me",
            summary = {
                span { +"Monika Protivová, Prague, Czech Republic" }
                br()
                span { +"\"Moni\"" }
            },
            content = {
                div {
                    style = "display: flex; align-items: center; justify-content: space-between; gap: 40px;"
                    
                    div {
                        style = "flex: 1;"
                        ul {
                            li {
                                strong { +"Software Developer & Educator" }
                                +" passionate about backend development and modern programming languages"
                            }
                            li {
                                +"Experienced in "
                                highlight("Kotlin")
                                +", "
                                highlight("Java")
                                +", and "
                                highlight("Spring Framework")
                            }
                            li {
                                +"Focus on "
                                highlight("clean code")
                                +", "
                                highlight("testing")
                                +", and "
                                highlight("modern architecture patterns")
                            }
                            li {
                                +"Teaching programming at "
                                highlight("Harbour.Space University")
                            }
                            li {
                                +"Coffee enthusiast ☕ and continuous learner"
                            }
                        }
                    }
                    
                    div {
                        style = "flex: 0 0 300px; text-align: center;"
                        div {
                            style = "font-size: 150px; margin-bottom: 20px;"
                            +"👩‍💻"
                        }
                        p {
                            style = "margin: 0; font-style: italic; color: #666;"
                            +"\"Code is poetry written for machines\""
                        }
                    }
                }
            }
        )
    ),
)

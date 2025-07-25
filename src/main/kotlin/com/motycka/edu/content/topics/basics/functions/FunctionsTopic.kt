package com.motycka.edu.content.topics.basics.functions

import com.motycka.edu.model.Topic

object FunctionsTopic : Topic(
    title = "Functions",
    slides = listOf(
        FunctionsIntroSlide,
        LocalFunctionsSlide,
        DefaultArgumentsSlide,
        NamedArgumentsSlide,
        NamedArgumentsExampleSlide,
        VariableArgumentsSlide
    )
)


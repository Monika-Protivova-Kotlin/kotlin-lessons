package com.motycka.edu.content.lessons.backend

import com.motycka.edu.content.topics.oop.ObjectOrientedProgrammingTopic
import com.motycka.edu.content.topics.basics.arrays.ArraysTopic
import com.motycka.edu.content.topics.basics.collections.CollectionsTopic
import com.motycka.edu.content.topics.basics.conditionals.ConditionalsTopic
import com.motycka.edu.content.topics.basics.essentials.KotlinDataTypesTopic
import com.motycka.edu.content.topics.basics.essentials.KotlinLanguageSyntaxTopic
import com.motycka.edu.content.topics.basics.essentials.OperatorsTopic
import com.motycka.edu.content.topics.basics.essentials.PackagesImportsModifiersTopic
import com.motycka.edu.content.topics.basics.essentials.WorkingWithStringsTopic
import com.motycka.edu.content.topics.basics.functions.FunctionsTopic
import com.motycka.edu.model.Lesson

val KotlinEssentialsBackendLesson = Lesson(
    title = "Kotlin Essentials",
    preTitle = "Week 1 | Lesson 2",
    subTitle = "Kotlin fundamentals for backend development",
    topics = listOf(
        ObjectOrientedProgrammingTopic,
        KotlinLanguageSyntaxTopic,
        KotlinDataTypesTopic,
        OperatorsTopic,
        ConditionalsTopic,
        WorkingWithStringsTopic,
        FunctionsTopic,
        ArraysTopic,
        CollectionsTopic,
        PackagesImportsModifiersTopic
    )
)

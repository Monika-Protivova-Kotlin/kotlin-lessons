package com.motycka.edu.content.lessons

import com.motycka.edu.content.topics.introductory.WhyKotlinTopic
import com.motycka.edu.content.topics.introductory.DevelopmentEnvironmentTopic
import com.motycka.edu.content.topics.introductory.FirstKotlinProgramTopic
import com.motycka.edu.content.topics.basics.KotlinLanguageFeaturesOriginsTopic
import com.motycka.edu.content.topics.basics.essentials.KotlinBasicSyntaxTopic
import com.motycka.edu.content.topics.basics.essentials.KotlinDataTypesTopicV2
import com.motycka.edu.content.topics.basics.essentials.KotlinVariablesAndConstantsTopic
import com.motycka.edu.content.topics.basics.essentials.KotlinOperatorsTopic
import com.motycka.edu.content.topics.basics.essentials.KotlinComparisonsConditionalsTopic
import com.motycka.edu.model.Lesson

object KotlinEssentialsV2Lesson : Lesson(
    preTitle = "Week 1 | Lesson 1",
    title = "Kotlin Essentials",
    contentInfo = listOf(
        "History, Syntax, Types, Operators, Conditionals"
    ),
    topics = listOf(
        WhyKotlinTopic,
        DevelopmentEnvironmentTopic,
        FirstKotlinProgramTopic,
        KotlinLanguageFeaturesOriginsTopic,
        KotlinBasicSyntaxTopic,
        KotlinDataTypesTopicV2,
        KotlinVariablesAndConstantsTopic,
        KotlinOperatorsTopic,
        KotlinComparisonsConditionalsTopic
    )
)

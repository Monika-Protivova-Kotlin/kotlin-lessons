package com.motycka.edu.content.lessons.backend

import com.motycka.edu.content.topics.introductory.java.JavaVirtualMachineTopic
import com.motycka.edu.content.topics.introductory.java.JREJDKCompilerTopic
import com.motycka.edu.content.topics.basics.JavaMemoryManagementTopic
import com.motycka.edu.content.topics.basics.concurrency.MultithreadingTopic
import com.motycka.edu.content.topics.basics.concurrency.CoroutinesTopic
import com.motycka.edu.content.topics.basics.concurrency.CoffeeShopExerciseTopic
import com.motycka.edu.model.Lesson

val MemoryConcurrencyBackendLesson = Lesson(
    title = "Memory & Threads",
    preTitle = "Week 3 | Lesson 10",
    subTitle = "JVM, Memory management, Multithreading, Coroutines",
    topics = listOf(
        JavaVirtualMachineTopic,
        JREJDKCompilerTopic,
        JavaMemoryManagementTopic,
        MultithreadingTopic,
        CoroutinesTopic,
        CoffeeShopExerciseTopic
    )
)
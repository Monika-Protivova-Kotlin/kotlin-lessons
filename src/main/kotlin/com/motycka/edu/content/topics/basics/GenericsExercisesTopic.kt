package com.motycka.edu.content.topics.basics

import kotlinx.html.*
import com.motycka.edu.model.ExerciseTopic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.inlineCode

object GenericsExercisesTopic : ExerciseTopic(
    title = "Generics Exercises",
    subtitle = "Hands-on Practice",
    slides = listOf(
        GenericsExercise1Slide,
        GenericsExercise2Slide,
        GenericsExercise3Slide
    )
)

object GenericsExercise1Slide : Slide(
    header = "Exercise: Generic #1",
    content = {
        div("content exercise-formatting") {
            p {
                +"Create a generic class Box that can hold any type of item. The class should have two methods:"
            }
            ul {
                li { inlineCode("putItem(item: T)"); +" that puts an item into the box" }
                li { inlineCode("getItem(): T?"); +" that returns the item from the box" }
                li { +"Store the "; strong { +"item" }; +" as a private property in the class" }
            }
            p {
                +"Create an instance of the Box class with different types and test the methods."
            }
        }
    }
)

object GenericsExercise2Slide : Slide(
    header = "Exercise: Generics #2",
    content = {
        div("content exercise-formatting") {
            p {
                +"Create a generic function insertIntoBox that takes three parameters:"
            }
            ul {
                li { +"item1 of type "; inlineCode("T1") }
                li { +"item2 of type "; inlineCode("T2") }
                li { +"func of type "; inlineCode("(T1, T2) -> R") }
            }
            p {
                +"The function should return the result of calling the func with item1 and item2 as arguments."
            }
            p {
                +"Call the function with two different types of items and a lambda that combines the items into a string."
            }
        }
    }
)

object GenericsExercise3Slide : Slide(
    header = "Exercise: Generics #3",
    content = {
        div("content exercise-formatting") {
            p {
                +"Create a generic interface "
                strong { +"MyComparator" }
                +" that has three type parameters:"
            }
            ul {
                li { inlineCode("T1") }
                li { inlineCode("T2") }
                li { inlineCode("R") }
            }
            p {
                +"The interface should have a single method "
                inlineCode("returnGreater")
                +" that takes two parameters of type "
                inlineCode("T1")
                +" and "
                inlineCode("T2")
                +" and returns the greater of the two as type "
                inlineCode("R")
                +"."
            }
            p {
                +"Create a class "
                strong { +"SmallDrawer" }
                +" that implements the MyComparator interface with different types."
            }
            p {
                +"Create an instance of the "
                strong { +"SmallDrawer" }
                +" class and test the returnGreater method."
            }
        }
    }
)
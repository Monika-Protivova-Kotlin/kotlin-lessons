package com.motycka.edu

import com.motycka.edu.builders.CourseBuilder
import com.motycka.edu.content.courses.BackendDevelopmentInKotlinCourse
import com.motycka.edu.content.courses.ProgrammingInKotlinCourse

fun main() {
    val courses = listOf(
        BackendDevelopmentInKotlinCourse,
        ProgrammingInKotlinCourse
    )

    courses.forEach { course ->
        CourseBuilder.createCourse(course)
    }

    CourseBuilder.createMainIndex(courses)
}

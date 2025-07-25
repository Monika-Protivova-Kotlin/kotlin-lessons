package com.motycka.edu

import com.motycka.edu.builders.CourseBuilder
import com.motycka.edu.content.courses.BackendDevelopmentInKotlinCourse
import com.motycka.edu.content.courses.ProgrammingInKotlinCourse

fun main() {
    CourseBuilder.createCourse(BackendDevelopmentInKotlinCourse)
    CourseBuilder.createCourse(ProgrammingInKotlinCourse)
}

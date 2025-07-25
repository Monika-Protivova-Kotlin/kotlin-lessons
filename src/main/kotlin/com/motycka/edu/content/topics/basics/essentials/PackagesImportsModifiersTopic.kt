package com.motycka.edu.content.topics.basics.essentials

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode

object PackagesImportsModifiersTopic : Topic(
    title = "Packages, Imports and Modifiers",
    slides = listOf(
        PackagesSlide,
        ModifiersIntroSlide,
        AccessModifiersSlide,
        NonAccessModifiersSlide
    )
)

object PackagesSlide : Slide(
    header = "Packages",
    summary = {
        +"Packages are used to organize code into namespaces, making it easier to manage and avoid naming conflicts."
    },
    content = {
        ul {
            li { +"The package declaration is usually the first line in a Kotlin file. It specifies the package to which the file belongs." }
            li { +"Package names are typically written in all lowercase and follow the reverse domain name convention." }
            li { +"To use classes and functions from other packages, you need to "; inlineCode("import"); +" them using the import keyword." }
            li { +"If no package is specified, the file belongs to the default package." }
        }
        br
        kotlinPlayground(
            code = """
                package com.motycka.edu.model // <- package name

                import java.time.LocalDate // <- import of class LocalDate from java.time package

                class User(
                    val name: String,
                    val birthDate: LocalDate
                )
            """,
            executable = false
        )
    }
)

object ModifiersIntroSlide : Slide(
    header = "Modifiers",
    summary = {
        +"Modifiers in programming languages are keywords that you can use to change the properties or behavior of classes, methods, and variables."
    },
    content = {
        p {
            +"They can be broadly categorized into two types:"
        }
        br
        h4 { +"Access Modifiers" }
        p {
            +"These define the visibility or accessibility of functions, classes, methods, and variables."
        }
        br
        h4 { +"Non-Access Modifiers" }
        p {
            +"These define other characteristics such as behavior, state, or implementation details."
        }
    }
)

object AccessModifiersSlide : Slide(
    header = "Access Modifiers",
    summary = {
        +"Access, modifiers define the visibility or accessibility of functions, classes, methods, and variables."
    },
    content = {
        table {
            thead {
                tr {
                    td { strong { +"modifier" } }
                    td { strong { +"on class" } }
                    td { strong { +"on method" } }
                    td { strong { +"on field" } }
                }
            }
            tbody {
                tr {
                    td { strong { inlineCode("public") } }
                    td { +"accessible from anywhere" }
                    td { +"accessible from anywhere" }
                    td { +"accessible from anywhere" }
                }
                tr {
                    td { strong { inlineCode("private") } }
                    td { +"only accessible within the same package" }
                    td { +"only accessible within same class" }
                    td { +"only accessible within same class" }
                }
                tr {
                    td { strong { inlineCode("protected") } }
                    td { +"accessible within the same package" }
                    td { +"only accessible within same class or it's subclass" }
                    td { +"only accessible within same class or it's subclass" }
                }
                tr {
                    td { strong { inlineCode("internal") } }
                    td { +"accessible within the same module" }
                    td { +"accessible within the same module" }
                    td { +"N/A" }
                }
                tr {
                    td { span { style = "font-style: italic"; +"default" } }
                    td { +"same as public" }
                    td { +"same as public" }
                    td { +"same as public" }
                }
            }
        }
    }
)

object NonAccessModifiersSlide : Slide(
    header = "Non-Access Modifiers",
    summary = {
        +"Non-access modifiers define other characteristics such as behavior, state, or implementation details."
    },
    content = {
        div {
            style = "font-size: 70%"
            table {
            thead {
                tr {
                    td { strong { +"modifier" } }
                    td { strong { +"on class" } }
                    td { strong { +"on method / block" } }
                    td { strong { +"on field" } }
                }
            }
            tbody {
                tr {
                    td { inlineCode("abstract") }
                    td { +"Class marked as abstract cannot be directly instantiated." }
                    td { +"Method marked as abstract does not provide implementation, but expects a "; em { +"subclass" }; +" to implement it." }
                    td { +"N/A" }
                }
                tr {
                    td { inlineCode("final") }
                    td { +"prevents inheritance" }
                    td { +"prevents method overloading" }
                    td { +"Makes variable a constant = value cannot be changed after initialization." }
                }
                tr {
                    td { inlineCode("open") }
                    td { +"Allows class to be inherited" }
                    td { +"Allows method to be overridden" }
                    td { +"Allows property to be changed" }
                }
                tr {
                    td { inlineCode("override") }
                    td { +"N/A" }
                    td { +"Indicates that a method is overriding a method in a superclass." }
                    td { +"N/A" }
                }
                tr {
                    td { inlineCode("lateinit") }
                    td { +"N/A" }
                    td { +"Indicates that a property will be initialized later." }
                    td { +"N/A" }
                }
                tr {
                    td { inlineCode("const") }
                    td { +"N/A" }
                    td { +"N/A" }
                    td { +"Indicates that a property is a compile-time constant." }
                }
                tr {
                    td { inlineCode("companion") }
                    td { +"N/A" }
                    td { +"N/A" }
                    td { +"Defines a companion object, which is an object that is tied to a class and can access its private members." }
                }
            }
        }
        }
    }
)

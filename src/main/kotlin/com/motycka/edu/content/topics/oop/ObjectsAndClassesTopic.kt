package com.motycka.edu.content.topics.oop

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import com.motycka.edu.model.inlineCode

object ObjectsAndClassesTopic : Topic(
    title = "Objects and Classes",
    slides = listOf(
        ObjectsAndClassesIntroSlide,
        ClassDefinitionOverviewSlide,
        ClassDefinitionExampleSlide,
        ClassInstantiationSlide,
        ClassHeaderSlide,
        ClassConstructorsSlide,
        AlternateConstructorsSlide,
        DefaultValuesNamedArgsSlide,
        MethodsSlide,
        InitializerBlocksSlide,
        ScopeAndThisSlide,
        ClassDestructionSlide,
        InnerClassesSlide,
        InnerClassUseCaseSlide,
        AnonymousClassesSlide
    )
)

object ObjectsAndClassesIntroSlide : Slide(
    header = "Objects and Classes",
    summary = {
        +"We have already seen and worked with Kotlin objects and classes."
        br
        +"Let's explain what they are and how they work in more detail."
    },
    content = {
        div("content-center") {
            br
            div {
                div("highlight") {
                    style = "font-size: larger; font-weight: bolder"
                    +"What is an object?"
                }
                br
                div {
                    style = "font-size: larger; font-style: italic"
                    classes = setOf("fragment", "fade-in")
                    +"Object is a data structure in memory"
                    br
                }
            }
            br
            br
            div {
                div("highlight") {
                    style = "font-size: larger; font-weight: bolder"
                    +"What is a class?"
                }
                br
                div {
                    style = "font-size: larger; font-style: italic"
                    classes = setOf("fragment", "fade-in")
                    +"Class is a template for how to create an object"
                }
            }
            br
            blockQuote {
                classes = setOf("fragment", "fade-in")
                +"Think of a class as a blueprint for creating objects."
            }
        }
    }
)

object ClassDefinitionOverviewSlide : Slide(
    header = "Class Definition",
    summary = {
        +"Class is defined using the "
        code("hljs inline") { +"class" }
        +" keyword, followed by the class name, class header and the class body."
    },
    content = {
        ul {
            li {
                span("highlight") { +"Class name" }
                +" - should begin with an initial letter (capitalized by convention)."
            }
            li {
                span("highlight") { +"Class header" }
                +" - class type parameters, the primary constructor, and other things, such as its superclass or interfaces it implements."
            }
            li {
                span("highlight") { +"Class properties" }
                +" - are defined in the class header and are used to hold the state of the class and its objects."
            }
            li {
                span("highlight") { +"Class body" }
                +" - containing fields, methods, constructors, initializer blocks, inner classes, and interfaces enclosed in curly braces."
                ul {
                    li {
                        span("highlight") { +"Fields" }
                        +" - additional properties that hold the state of the class and its objects."
                    }
                    li {
                        span("highlight") { +"Methods" }
                        +" - functions that are part of the class and contain the executable code."
                    }
                    li {
                        span("highlight") { +"Constructors" }
                        +" - special methods used to initialize the state of an object."
                    }
                    li {
                        span("highlight") { +"Initializer blocks" }
                        +" - unnamed code blocks used for initializing shared variables and executing code that needs to run every time an instance of the class is created."
                    }
                    li {
                        span("highlight") { +"Inner classes and interfaces" }
                        +" - class or interface definitions nested within the outer class body."
                    }
                    li {
                        span("highlight") { +"Companion objects" }
                        +" - special objects that are tied to a class, rather than to an instance of a class."
                    }
                }
            }
        }
    }
)

object ClassDefinitionExampleSlide : Slide(
    header = "Class Definition",
//    summary = {
//        +"Class is defined using the "
//        code("hljs inline") { +"class" }
//        +" keyword, followed by the class name, class header (optional), and the class body (optional) enclosed in curly braces."
//    },
    content = {
        div {
            +"Simple class definition without body."
            kotlinPlayground(
                code = """
                    class UniversityCourse
                """,
                executable = false
            )
        }
        div {
            +"More realistic class definition with parameters and body."
            kotlinPlayground(
                code = """
                    class UniversityCourse(
                        val subject: String,
                        val startDate: LocalDate,
                        val endDate: LocalDate,
                        val students: MutableList<String> = mutableListOf() // initial value of empty list
                    ) {

                        // class field which is not part of the constructor
                        private var isOpen: Boolean = false // initial value of false

                        fun addStudent(studentName: String) {
                            if (isOpen) {
                                students.add(studentName)
                            } else {
                                error("Cannot add students to closed course.")
                            }
                        }

                        fun open() {
                            isOpen = true
                        }

                        fun close() {
                            isOpen = false
                        }
                    }
                """,
                executable = false
            )
        }
    }
)

object ClassInstantiationSlide : Slide(
    header = "Class Instantiation",
    summary = {
        +"You do not directly work with classes in Kotlin, you work with objects that are created from classes, called class instances."
        blockQuote {
            +"Object == Instance of a class"
        }
    },
    content = {
        ul {
            li { +"The process that creates an object from class is called "; strong { +"instantiation" } }
            li { +"Instantiation is done by calling a special "; strong { +"constructor" }; +" method" }
            li { +"Class may have "; strong { +"one or more" }; +" constructors" }
            li { +"If constructor is not explicitly defined, then the class will "; strong { +"default constructor with no arguments" } }
            li { +"Instantiation can be used to set initial values for the object" }
        }
    }
)

object ClassHeaderSlide : Slide(
    header = "Class Header",
    summary = {
        +"Class header defines its type parameters, the primary constructor, and other things, "
        +"such it's superclass or interfaces it implements."
    },
    content = {
        div {
            ul {
                li { +"The class header specifies class properties (fields)." }
                li { +"Just like functions argument, class fields can have default values." }
                li { +"The declared fields also define the class primary constructor." }
                li {
                    +"In this example, the class header specifies that the class has four fields, one with a default value, "
                    +"which means that it can be omitted when creating an instance of the class."
                    kotlinPlayground(
                        code = """
                            class UniversityCourse(
                                val subject: String,
                                val startDate: LocalDate,
                                val endDate: LocalDate,
                                val students: MutableList<String> = mutableListOf() // initial value of empty list
                            ) {
                                // class body
                            }
                        """,
                        executable = false
                    )
                }
            }
        }
    }
)

object ClassConstructorsSlide : Slide(
    header = "Class Constructors",
    summary = {
        +"Class constructor is a special method with the only purpose of class instantiation."
    },
    content = {
        twoColumns(
            left = {
                +"Class with zero arguments constructor"
                kotlinPlayground(
                    code = """
                        class KotlinCourse {
                            val subject: String = "Kotlin"
                        }


                        val kotlinCourse = KotlinCourse()
                    """,
                    executable = false
                )
                +"Class with one arguments constructor"
                kotlinPlayground(
                    code = """
                        class Course(val subject: String)


                        val kotlinCourse = Course("Kotlin")
                    """,
                    executable = false
                )
            },
            right = {
                +"Class with multiple arguments constructor"
                kotlinPlayground(
                    code = """
                        class Course(
                            val subject: String,
                            val startDate: LocalDate,
                            val endDate: LocalDate
                        )


                        val kotlinCourse = Course(
                            "Kotlin",
                            LocalDate.parse("2024-02-03"),
                            LocalDate.parse("2024-02-21")
                        )
                    """,
                    executable = false
                )
            }
        )
        ul {
            li { +"Every class has a constructor, even if it is not explicitly defined." }
            li { +"Class may have multiple constructors (primary and secondary constructors)." }
            li { +"Constructor may have zero, one, or many arguments" }
            li { +"Class constructors support named arguments just like functions." }
        }
    }
)

object AlternateConstructorsSlide : Slide(
    header = "Alternate class constructors",
    summary = {
        +"Kotlin allows you to define multiple constructors for a class, also called secondary constructors. "
        +"They provide alternative ways to instantiate the class."
    },
    content = {
        +"Example of class definition, class alternate constructor, and constructor calls."
        kotlinPlayground(
            code = """
                // primary constructor defined by class header
                class UniversityCourse(
                    val subject: String,
                    val startDate: LocalDate,
                    val endDate: LocalDate
                ) {

                    // alternate constructor
                    constructor(
                        subject: String,
                        startDate: String,
                        endDate: String,
                    ) : this(subject, LocalDate.parse(startDate), LocalDate.parse(endDate))
                }
            """,
            executable = false
        )
        +"Primary constructor call with default values"
        kotlinPlayground(
            code = """
                val kotlinCourse1 = UniversityCourse("Kotlin", LocalDate.parse("2024-02-03"), LocalDate.parse("2024-02-21"))
            """,
            executable = false
        )
        +"Alternate constructor call"
        kotlinPlayground(
            code = """
                val kotlinCourse3 = UniversityCourse("Kotlin", "2024-02-03", "2024-02-21")
            """,
            executable = false
        )
    }
)

object DefaultValuesNamedArgsSlide : Slide(
    header = "Default values & Named arguments",
    summary = {
        +"Just like functions, class constructors can have default values and support named arguments."
    },
    content = {
        +"Class constructor really is just a special kind of function, so just like functions, "
        +"class constructors can have default values and support named arguments."
        kotlinPlayground(
            code = """
                class UniversityCourse(
                    val subject: String,
                    val startDate: LocalDate,
                    val endDate: LocalDate = startDate.plusDays(30),    // default date
                    val students: MutableList<String> = mutableListOf() // default value of empty list
                )
            """,
            executable = false
        )
        twoColumns(
            left = {
                kotlinPlayground(
                    code = """
                        UniversityCourse("Kotlin", LocalDate.parse("2024-02-03"))
                    """,
                    executable = false
                )
            },
            right = { br; +"Calling the constructor with default values." }
        )
        twoColumns(
            left = {
                kotlinPlayground(
                    code = """
                        UniversityCourse(
                            "Kotlin",
                            LocalDate.parse("2024-02-03"),
                            students = mutableListOf("Alice", "Bob")
                        )
                    """,
                    executable = false
                )
            },
            right = {
                br
                +"Calling the constructor with one named argument for "; strong { +"students" }; "."
                br
                +"The "; strong { +"endDate" }; +" will be set to the default value."
            }
        )
        twoColumns(
            left = {
                kotlinPlayground(
                    code = """
                        UniversityCourse(
                            subject = "Kotlin",
                            startDate = LocalDate.parse("2024-02-03"),
                            endDate = LocalDate.parse("2024-02-21"),
                            students = mutableListOf("Alice", "Bob")
                        )
                    """,
                    executable = false
                )
            },
            right = {
                br
                +"Naming all arguments when calling the constructor."
            }
        )
    }
)

object MethodsSlide : Slide(
    header = "Methods",
    summary = {
        +"Method is a function associated with an object."
        br
        +"In Kotlin, you can also call methods member functions."
    },
    content = {
        p {
            +"Same rules and possibilities apply to methods as to functions not associated with a class."
        }
        kotlinPlayground(
            code = """
                class UniversityCourse(
                    val subject: String,
                    val startDate: LocalDate,
                    val endDate: LocalDate = startDate.plusDays(30),
                    val students: MutableList<String> = mutableListOf()
                ) {

                    fun addStudent(studentName: String) {
                        students.add(studentName)
                    }
                }
            """,
            executable = false
        )
        p {
            +"The only difference is that methods are called on an instance of object."
        }
        kotlinPlayground(
            code = """
                val kotlinCourse = UniversityCourse("Kotlin", LocalDate.parse("2024-02-03"))
                kotlinCourse.addStudent("Alice")
                kotlinCourse.addStudent("Bob")
            """,
            executable = false
        )
    }
)

object InitializerBlocksSlide : Slide(
    header = "Initializer blocks",
    summary = {
        +"Initializer blocks are code that is run when an instance of a class is created."
    },
    content = {
        p {
            +"For example, this can be used for additional argument validation or to set up some initial state."
        }
        p {
            +"Class may have one or more initializer blocks, which are executed in the order they are defined."
        }
        kotlinPlayground(
            code = """
                class UniversityCourse(
                    val subject: String,
                    val startDate: LocalDate,
                    val endDate: LocalDate = startDate.plusDays(30),
                    val students: MutableList<String> = mutableListOf()
                ) {

                    init {
                        require(startDate < endDate) { "End date must be after start date" }
                    }

                    init {
                        println("Course ${'$'}subject created")
                    }

                }
            """,
            executable = false
        )
    }
)

object ScopeAndThisSlide : Slide(
    header = "Scope and \"this\" reference",
    summary = {
        +"In Kotlin, the "
        inlineCode("this")
        +" keyword is used to refer to the current instance of a class."
    },
    content = {
        +"It is often used to distinguish between class properties and parameters or local variables with the same name."
        kotlinPlayground(
            code = """
                class Person(val name: String) {

                    fun introduce(name: String) {
                        println("${'$'}{this.name} also goes by ${'$'}name")
                    }

                }
                
                fun main() {
                    val person = Person("Monika")
                    person.introduce("Moni") // prints "Monika also goes by Moni"
                }
            """,
            executable = true
        )
        +"Additionally, Kotlin provides other scope references using "
        inlineCode("@label")
        +" to refer to specific instances in nested scopes."
        kotlinPlayground(
            code = """
                class A {
                    inner class B {
                        fun Int.foo() {
                            val a = this@A // refers to the instance of A
                            val b = this@B // refers to the instance of B
                            val c = this // refers to the receiver Int
                            println("a: ${'$'}a, b: ${'$'}b, c: ${'$'}c")
                        }
                    }
                }
            """,
            executable = false
        )
        kotlinPlayground(
            code = """
                fun main() {
                    val a = A()
                    val b = a.B()
                    b.run { 42.foo() }
                }
            """,
            executable = true
        )
    }
)

object ClassDestructionSlide : Slide(
    header = "Class destruction",
    summary = {
        +"Java/Kotlin has no class destructor, because freeing up memory is entirely delegated to JVM through "
        +"a process called "; strong { +"garbage collection" }; ", which we will talk about in later in the course."
    },
    content = {
        +"Some Java/Kotlin classes may have a special methods that should be called after we are done using "
        +"the class in order for it to be eligible for garbage collection. I will also mention these "
        +"later in the course."
    }
)

object InnerClassesSlide : Slide(
    header = "Inner classes",
    summary = {
        +"Inner class (also called nested class), is a class defined within a body of another class."
    },
    content = {
        kotlinPlayground(
            code = """
                class OuterClass {

                    private val outerField: String = "Outer Field"

                    // Inner class
                    inner class InnerClass {
                        fun accessOuterField(): String {
                            return outerField
                        }
                    }
                }
                
                fun main() {
                    val outer = OuterClass()
                    val inner = outer.InnerClass()
                    println(inner.accessOuterField()) // prints "Outer Field"
                }
            """,
            executable = true
        )
    }
)

object InnerClassUseCaseSlide : Slide(
    header = "Use case for nested classes",
    content = {
        br
        h4 { +"Logical Grouping" }
        div {
            +"Nested classes can help us keep our code organized by having related code together. "
            +"For example, you may want to create an internal data class."
        }
        br; br
        h4 { +"Encapsulation & Access Control" }
        div {
            +"Inner classes can access members of the outer class, including those marked as private. "
            +"This can benefit us in multiple scenarios, such us when creating helper classes without exposing "
            +"private methods or fields of the outer class."
        }
        br; br
        h4 { +"Increased Readability and Maintainability" }
        div {
            +"Inner classes are used for code that is relevant to a small part of the outer class. "
            +"Grouping them together improves code readability and maintainability."
        }
    }
)

object AnonymousClassesSlide : Slide(
    header = "Anonymous Classes",
    summary = {
        +"In Kotlin, anonymous classes are a way to create an instance of a class with certain modifications "
        +"without having to actually declare a new subclass. They are often used to create instances "
        +"of classes that have no name and are used only once."
    },
    content = {
        p {
            +"Since it has no name, we have no way to instantiate such class."
            br
            +"Thus, an anonymous class must be declared and instantiated with a single expression."
        }
        p {
            +"An anonymous class must either implement an interface or extend an existing class (abstract or concrete)."
        }
        p {
            +"Anonymous classes are useful for creating quick, one-off implementations of interfaces or abstract classes."
        }
        kotlinPlayground(
            code = """
                fun main() {

                    val runnable = object : Runnable {
                        override fun run() {
                            println("Running in an anonymous class!")
                        }
                    }

                    val thread = Thread(runnable)

                    thread.start()
                }
            """,
            executable = true
        )
    }
)

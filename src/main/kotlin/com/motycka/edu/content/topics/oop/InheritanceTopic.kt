package com.motycka.edu.content.topics.oop

import com.motycka.edu.builders.codeOrderedList
import com.motycka.edu.builders.highlightOrderedList
import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import com.motycka.edu.model.inlineCode

object InheritanceTopic : Topic(
    title = "Inheritance",
    subtitle = "OOP Principle #2",
    slides = listOf(
        InheritanceOverviewSlide,
        InheritanceExampleSlide,
        InheritanceFullExampleSlide,
        InheritancePreventionSlide,
        InheritanceProsAndConsSlide,
//        InheritanceProsSlide,
//        InheritanceConsSlide,
        CompositionSlide,
        CompositionExampleSlide,
        CompositionAndInheritanceSlide
    )
)

const val ANIMAL_CLASS = """
open class Animal(private val sound: String) {
    fun makeSound() {
        println(sound)
    }

    protected fun makeRawSound() {
        println(sound)
    }
}
"""
const val BARKIN_ANIMAL_CLASS = """
open class BarkingAnimal : Animal("woof") {
    fun bark() {
        makeRawSound()
    }
}
"""
const val CAT_CLASS = """
class Cat(sound: String) : Animal(sound)
"""
const val DOG_CLASS = """
class Dog : BarkingAnimal()    
"""
const val BIRD_CLASS = """
class Bird : Animal {
    constructor(song: String) : super(song)
}
"""
const val SOUND_CLASS = """
class Sound(private val sound: String) {
    fun makeSound() {
        println(sound)
    }
}
"""
const val INHERITANCE_EXAMPLE_CODE = """
$ANIMAL_CLASS
$BARKIN_ANIMAL_CLASS
$CAT_CLASS
$DOG_CLASS
$BIRD_CLASS

fun main() {
    val cat = Cat("meow")
    cat.makeSound()

    val dog = Dog()
    dog.makeSound()

    // this would not compile, because makeRawSound is protected
    // dog.makeRawSound();

    val bird = Bird("tweet")
    bird.makeSound()
}
"""

const val COMPOSITION_CAT_CLASS = """
class Cat(val name: String) {

    private val sound = Sound("meow")

    fun meow() {
        sound.makeSound()
    }
}
"""

const val COMPOSITION_EXAMPLE_CODE = """
$SOUND_CLASS
$COMPOSITION_CAT_CLASS

fun main() {
    val cat = Cat("Garfield")
    cat.meow()
}
"""

const val COMPOSITION_INHERITANCE_CAT_CLASS = """
class Cat(val name: String, sound: Sound) : Animal(sound)
"""

const val COMPOSITION_INHERITANCE_EXAMPLE_CODE = """
$ANIMAL_CLASS
$SOUND_CLASS
$COMPOSITION_INHERITANCE_CAT_CLASS

fun main() {
    val cat = Cat("Garfield", Sound("meow"))
    cat.makeSound()
}
"""

object InheritanceOverviewSlide : Slide(
    header = "What is Inheritance",
    summary = {
        +"Inheritance establishes an "
        em { +"\"is-a\"" }
        +" relationship between two classes, where one class inherits properties and methods of the other class."
    },
    content = {
        blockQuote {
            +"The class that inherits is called "
            strong { +"subclass" }
            +" and the class inherited from is called "
            strong { +"superclass" }
            +"."
        }
        ul {
            li { +"The class to be inherited from must be marked as "; inlineCode("open"); +"." }
            li {
                +"To define inheritance, the "
                inlineCode(":")
                +" symbol is used followed by the superclass name, for example "
                inlineCode("class Dog : Animal()")
                +"."
            }
            li { +"If a superclass has a "; em { +"non-default" }; +" constructor, you must call "; inlineCode("super()"); +" method in the subclass constructor." }
            li {
                +"You can mark methods and attributes of a superclass as "
                inlineCode("protected")
                +". This will make them only accessible within the same package or within subclass."
            }
            li { +"You can reference fields and methods in the superclass class using the "; inlineCode("super"); +" keyword." }
            li { +"To prevent inheritance, you can mark the class with "; inlineCode("final"); +" modifier." }
        }
        p { em { +"Let's have a look at this in detail ..." } }
    }
)

object InheritanceExampleSlide : Slide(
    header = "Inheritance: Example",
    content = {
        div("content") {
            twoColumns(
                left = {
                    strong { +"Animal" }
                    +" is base class for all animals."
                    kotlinPlayground(
                        code = ANIMAL_CLASS,
                        executable = false,
                        showLines = false,
                    )
                    strong { +"BarkingAnimal" }
                    +" extends "
                    strong { +"Animal" }
                    +" and adds a bark method."
                    kotlinPlayground(
                        code = BARKIN_ANIMAL_CLASS,
                        executable = false,
                        showLines = false,
                    )
                },
                right = {
                    +"Cat extends "
                    strong { +"Animal" }
                    +" and overrides the sound."
                    kotlinPlayground(
                        code = CAT_CLASS,
                        executable = false,
                        showLines = false,
                    )
                    strong { +"Dog" }
                    +" extends "
                    strong { +"BarkingAnimal" }
                    +" and does not override the sound."
                    kotlinPlayground(
                        code = DOG_CLASS,
                        executable = false,
                        showLines = false,
                    )
                    strong { +"Bird" }
                    +" extends "
                    strong { +"Animal" }
                    +" and adds an alternative constructor. Notice the use of "
                    inlineCode("super")
                    +"."
                    kotlinPlayground(
                        code = BIRD_CLASS,
                        executable = false,
                        showLines = false,
                    )
                }
            )
        }
    }
)

object InheritanceFullExampleSlide : Slide(
    header = "Inheritance: Full Example",
    content = {
        div("content") {
            kotlinPlayground(
                code = INHERITANCE_EXAMPLE_CODE,
                selectLines = 18..44,
                executable = true
            )
        }
    }
)

object InheritancePreventionSlide : Slide(
    header = "Inheritance",
    summary = {
        +"You can use the "
        inlineCode("final")
        +" modifier to prevent method overriding or class inheritance."
    },
    content = {
        kotlinPlayground(
            code = """
                open class Cat {

                    final fun meow() {
                        println("meow");
                    }

                }
            """,
            executable = false
        )
        p {
            +"We are trying to override the "
            inlineCode("meow")
            +" method, the compiler will throw an error."
        }
        p {
            kotlinPlayground(
                code = """
                    class MyCat: Cat() {

                        // This will not compile
                        override fun meow() { }

                    }
                """,
                executable = false,
                erroneous = true,
            )
        }
    }
)

object InheritanceProsAndConsSlide : Slide(
    header = "Inheritance Pros and Cons",
    content = {
        twoColumns(
            left = {
                h4 { +"Pros" }
                highlightOrderedList(
                    "Promotes code reuse" to {
                        +"Inheritance allows subclasses to inherit methods and fields from superclasses which leads to a reduction in code duplication."
                    },
                    "Promotes polymorphism" to {
                        +"Subclasses can redefine certain methods based on their requirement."
                    },
                    "Hierarchy and organization" to {
                        +"Helps to design the software in a hierarchical manner where classes with general characteristics are at a higher level and classes with specific characteristics are at lower level."
                    }
                )
            },
            right = {
                h4 { +"Cons" }
                highlightOrderedList(
                    "Tight coupling" to {
                        +"A subclass is tightly coupled with its superclass. If the superclass is modified, subclasses could be affected, as they inherit methods and fields from the superclass."
                    },
                    "Inheritance chain" to {
                        +"Inheritance often leads to long chains which could make tracking down errors in the code difficult."
                    },
                    "Issues with multiple inheritance" to {
                        +"Kotlin does not support multiple inheritance (a class can't extend more than one class)."
                        br()
                        +"However, it supports multiple interface implementation, which is a partial workaround for this issue."
                    },
                    "Memory overhead" to {
                        +"When a subclass object is created, a separate memory space is reserved for it in addition "
                        +"to the separate memory space reserved for the superclass object. This might result in "
                        +"memory wastage if the subclass makes limited use of the superclass's features."
                    }
                )
            }
        )
    }
)

object CompositionSlide : Slide(
    header = "Composition",
    summary = {
        +"Composition provides a "
        em { +"\"has-a\"" }
        +" relationship. It allows you to use object instances as fields within the other classes."
    },
    content = {
        h4 { +"Pros" }
        ul {
            li { +"Results in loose coupling and improves encapsulation, because the contained objects can be easily swapped without changing the code that uses them." }
            li { +"Can be used to overcome lack of multiple inheritance in Kotlin." }
            li { +"Usually allows for better testability as well." }
        }
        h4 { +"Cons" }
        ul {
            li { +"It can result in bloated classes if overused, and requires more code setup than inheritance." }
            li { +"it can be more difficult to use when requests must be delegated to the appropriate class." }
        }
    }
)

object CompositionExampleSlide : Slide(
    header = "Composition",
    content = {
        kotlinPlayground(
            code = COMPOSITION_EXAMPLE_CODE,
            executable = true,
            selectLines = 18..21,
        )
        twoColumns(
            left = {
                h4 { +"Composition class" }
                +"The "
                strong { +"Sound" }
                +" class has no dependencies and can be reused in other classes."
                kotlinPlayground(
                    code = SOUND_CLASS,
                    executable = false,
                    showLines = false,
                )
            },
            right = {
                h4 { +"Composed class" }
                +"The "
                strong { +"Cat" }
                +" class uses composition to include a "
                strong { +"Sound" }
                +" instance."
                kotlinPlayground(
                    code = COMPOSITION_CAT_CLASS,
                    executable = false,
                    showLines = false,
                )
            }
        )
    }
)

object CompositionAndInheritanceSlide : Slide(
    header = "Composition and Inheritance",
    summary = {
        +"The two techniques can be, and often are, combined."
    },
    content = {
        p {
            +"Both inheritance and composition have their strengths and weaknesses. Deciding when to use each can be instrumental for designing cleaner and more effective code."
        }
        kotlinPlayground(
            code = COMPOSITION_INHERITANCE_EXAMPLE_CODE,
            executable = true,
            selectLines = 22..25
        )
        twoColumns(
            left = {
                +"Superclass - adding Sound through composition"
                kotlinPlayground(
                    code = ANIMAL_CLASS,
                    executable = false,
                    showLines = false
                )
                +"Subclass extending Animal"
                kotlinPlayground(
                    code = COMPOSITION_INHERITANCE_CAT_CLASS,
                    executable = false,
                    showLines = false
                )
            },
            right = {
                +"Composed class - has no dependencies"
                kotlinPlayground(
                    code = SOUND_CLASS,
                    executable = false,
                    showLines = false
                )
            }
        )
    }
)

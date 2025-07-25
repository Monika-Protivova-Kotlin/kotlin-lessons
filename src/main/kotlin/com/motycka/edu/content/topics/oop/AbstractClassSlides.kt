package com.motycka.edu.content.topics.oop

import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.br
import kotlinx.html.code
import kotlinx.html.li
import kotlinx.html.ol
import kotlinx.html.p
import com.motycka.edu.model.inlineCode

const val ABSTRACT_CLASS = """
/**
 * Abstract class definition.
 */
abstract class Animal(
    protected val sound: String // notice the protected modifier
) {

    /*
    Abstract method definition, which a subclass must implement.
    */
    abstract fun makeSound()
}
"""

const val CONCRETE_CLASS = """
/**
 * Subclass of Animal.
 * Compiler will force us to call superclass constructor!
 */
class Cat(sound: String): Animal(sound) {

    /*
    Compiler will force us to use override keyword!
    */
    override fun makeSound() {
        println(this.sound) // referencing sound in 'this' instance
    }
}
"""

const val ABSTRACT_CLASS_MAIN = """
$ABSTRACT_CLASS
$CONCRETE_CLASS

fun main() {
    val cat: Animal = Cat("meow") // Animal reference but Cat object
    cat.makeSound()
}
"""

object AbstractClassOverviewSlide : Slide(
    header = "Abstract class",
    summary = {
        +"Abstract is defined using the "
        inlineCode("abstract")
        +" keyword "
        br()
        +"and are used to define common behavior that can be inherited by subclasses."
    },
    content = {
        p {
            +"Abstract class cannot be instantiated directly. The main purpose of an abstract class is encapsulating common behavior that can be shared among multiple subclasses, "
            +"while allowing each subclass to implement its own behavior either by overriding abstract methods, adding new methods or fields, or overriding non-abstract methods."
        }
        ol {
            li { +"Abstract classes can have constructors, but they cannot be directly instantiated." }
            li { +"They can contain both abstract and non-abstract methods." }
            li { +"Abstract methods must be implemented by subclasses." }
            li { +"Non-abstract methods can be optionally overridden by subclasses." }
        }
    }
)

object AbstractClassExampleSlide : Slide(
    header = "Abstract class",
    summary = {
        +"Example"
    },
    content = {
        kotlinPlayground(
            code = ABSTRACT_CLASS_MAIN,
            selectLines = 30..33,
            executable = true
        )
        twoColumns(
            left = {
                +"Abstract class definition"
                kotlinPlayground(
                    code = ABSTRACT_CLASS,
                    executable = false
                )
            },
            right = {
                +"Abstract class implementation"
                kotlinPlayground(
                    code = CONCRETE_CLASS,
                    executable = false
                )
            }
        )
    }
)

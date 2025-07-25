package com.motycka.edu.content.topics.oop

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Slide.Companion.DOLLAR
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.code
import kotlinx.html.li
import kotlinx.html.ol
import kotlinx.html.p
import com.motycka.edu.model.inlineCode

object InterfacesOverviewSlide : Slide(
    header = "Interfaces",
    summary = {
        +"Interface is a reference type (like class) defined with the "
        inlineCode("interface")
        +" modifier."
    },
    content = {
        p {
            +"In Kotlin, an interface is a reference type similar to a class. It can contain abstract methods and properties, as well as default method implementations. "
            +"Interfaces cannot store state and cannot have constructors. They are used to define a contract that classes can implement."
        }
        p {
            +"Therefore, interface cannot be directly instantiated, just like abstract class. You could say interface is a 100% abstract class."
        }
        ol {
            li { +"Interfaces are declared using the "; inlineCode("interface"); +" keyword." }
            li { +"All methods in an interface are abstract by default, but they can also have default implementations." }
            li { +"Interfaces can contain properties, but these properties must be abstract or have default implementations." }
            li { +"A class implements an interface using the "; inlineCode(":"); +" symbol followed by the interface name." }
            li { +"A class can implement multiple interfaces, allowing for a form of multiple inheritance." }
        }
    }
)

const val ANIMAL_INTERFACE = """
interface Animal {

    fun makeSound()

    fun move(distance: Double): Double

}
"""

const val MOVING_INTERFACE = """
interface Moving {

    fun move(distance: Double): Double

}
"""

const val VOCALIZING_INTERFACE = """
interface Vocalizing {

    fun makeSound()

}
"""

const val ANIMAL_MOVING_VOCALIZING_INTERFACE = """
interface Animal : Moving, Vocalizing {

    fun eat(food: String)

}
"""

const val CAT_ANIMAL_IMPLEMENTATION = """
class Cat : Animal {

    override fun makeSound() {
        println("meow")
    }

    override fun move(distance: Double): Double {
        val speed = 2.0
        return distance / speed
    }
}
"""

const val CAT_MOVING_VOCALIZING_IMPLEMENTATION = """
class Cat : Moving, Vocalizing {

    override fun makeSound() {
        println("meow")
    }

    override fun move(distance: Double): Double {
        val speed = 2.0
        return distance / speed
    }
}
"""

const val CAT_ANIMAL_MOVING_VOCALIZING_IMPLEMENTATION = """
class Cat : Animal {

    override fun makeSound() {
        println("meow")
    }

    override fun move(distance: Double): Double {
        val speed = 2.0
        return distance / speed
    }

    override fun eat(food: String) {
        println("eats " + food)
    }
}
"""

const val INTERFACES_MAIN = """
fun main() {
    val cat: Animal = Cat() // Animal reference but Cat object

    cat.makeSound()

    val distance = 3.2
    val movementTime = cat.move(distance)

    println("Cat move ${DOLLAR}distance m in ${DOLLAR}movementTime s")
}
"""

object InterfacesExampleSlide : Slide(
    header = "Interfaces",
    summary = {
        +"Example"
    },
    content = {
        kotlinPlayground(
            code = """
                $ANIMAL_INTERFACE
                $CAT_ANIMAL_IMPLEMENTATION
                $INTERFACES_MAIN
            """,
            selectLines = 23 .. 32,
            executable = true
        )
        twoColumns(
            left = {
                +"Interface"
                kotlinPlayground(
                    code = ANIMAL_INTERFACE,
                    executable = false
                )
            },
            right = {
                +"Implementation"
                kotlinPlayground(
                    code = CAT_ANIMAL_IMPLEMENTATION,
                    executable = false
                )
            }
        )
    }
)

object MultipleInterfacesSlide : Slide(
    header = "Interfaces",
    summary = {
        +"You can also implement multiple interfaces at once."
    },
    content = {
        kotlinPlayground(
            code = """
                $MOVING_INTERFACE
                $VOCALIZING_INTERFACE
                $CAT_MOVING_VOCALIZING_IMPLEMENTATION
                $INTERFACES_MAIN
            """,
            selectLines = 28 .. 37,
            executable = true
        )
        twoColumns(
            left = {
                +"Moving interface"
                kotlinPlayground(
                    code = MOVING_INTERFACE,
                    executable = false
                )
                +"Vocalizing interface"
                kotlinPlayground(
                    code = VOCALIZING_INTERFACE,
                    executable = false
                )
            },
            right = {
                +"Implementation of both Moving and Vocalizing"
                kotlinPlayground(
                    code = CAT_MOVING_VOCALIZING_IMPLEMENTATION,
                    executable = false
                )
            }
        )
    }
)

object InterfaceInheritanceSlide : Slide(
    header = "Interfaces",
    summary = {
        +"You can also extend interface with other interfaces. "
        +"The concrete class that implements such interface will be required to implement all abstract methods."
    },
    content = {
        twoColumns(
            left = {
                +"Animal interface"
                kotlinPlayground(
                    code = ANIMAL_MOVING_VOCALIZING_INTERFACE,
                    executable = false
                )
                +"Moving interface"
                kotlinPlayground(
                    code = MOVING_INTERFACE,
                    executable = false
                )
                +"Vocalizing interface"
                kotlinPlayground(
                    code = VOCALIZING_INTERFACE,
                    executable = false
                )
            },
            right = {
                +"Implementation of Animal"
                kotlinPlayground(
                    code = CAT_ANIMAL_MOVING_VOCALIZING_IMPLEMENTATION,
                    executable = false
                )
            }
        )
    }
)

package com.motycka.edu.content.topics.introductory.java

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.highlight
import kotlinx.html.*

object JREJDKCompilerTopic : Topic(
    title = "JRE, JDK, compiler",
    slides = listOf(
        JREJDKCompilerSlide,
        JavaBytecodeSlide,
        JavaBytecodeExampleSlide
    )
)

object JREJDKCompilerSlide : Slide(
    header = "JRE, JDK, compiler",
    content = {
        p {
            highlight("JRE - Java Runtime Environment")
            br()
            +"JRE is the part of Java required to run Java applications. It includes JVM, core libraries, and other components. "
            +"If you only want to run Java applications, you only need JRE."
        }
        p {
            highlight("JDK - Java Development Kit")
            br()
            +"You need JDK if you want to develop Java applications. It includes JRE, compiler, and other development tools."
        }
        p {
            highlight("Java Compiler")
            br()
            +"Java source code is compiled into "
            highlight("bytecode")
            +", which is then executed by JVM. To do so, you need a Java compiler."
        }
        p {
            highlight("Java Bytecode")
            br()
            +"Java bytecode is the instruction set for the Java Virtual Machine."
        }
    }
)

object JavaBytecodeSlide : Slide(
    header = "Java Bytecode",
    summary = { +"Write Once, Run Anywhere" },
    content = {
        p {
            +"Java bytecode is the intermediate representation of Java code which is output by the Java compiler (javac). "
            +"It is not the machine code for any particular computer - it is not executed by the CPU of any computer."
        }
        p {
            +"Instead, the Java bytecode is executed by the Java Virtual Machine (JVM). "
            +"You can say it is an instruction set for the JVM."
        }
    },
    textAlign = "center",
    fontSize = "100%"
)

object JavaBytecodeExampleSlide : Slide(
    header = "Java Bytecode",
    summary = { +"Write Once, Run Anywhere" },
    content = {
        p {
            +"When a Java program is compiled, each individual class file is compiled into a separate bytecode file (with a .class extension). "
            +"This bytecode is platform independent, which means the same bytecode can run on any device that has a JVM."
        }
        kotlinPlayground(
            code = """
                fun main(args: Array<String>) {
                    println("Hello, World!")
                }
            """.trimIndent()
        )
        p {
            +"Compiles to following bytecode:"
        }
        pre {
            code {
                +"""
                    Compiled from "HelloWorld.java"
                    public class lesson08.HelloWorld {
                      public lesson08.HelloWorld();
                        Code:
                           0: aload_0
                           1: invokespecial #1                  // Method java/lang/Object."<init>":()V
                           4: return

                      public static void main(java.lang.String[]);
                        Code:
                           0: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
                           3: ldc           #13                 // String Hello, World!
                           5: invokevirtual #15                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
                           8: return
                    }
                """.trimIndent()
            }
        }
    }
)

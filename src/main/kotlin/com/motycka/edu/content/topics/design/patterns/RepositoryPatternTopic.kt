package com.motycka.edu.content.topics.design.patterns

import com.motycka.edu.builders.CourseBuilder.ARROW_DOWN
import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object RepositoryPatternTopic : Topic(
    title = "Repository Pattern",
    subtitle = "with exercise",
    slides = listOf(
        RepositoryPatternExplanationSlide,
        RepositoryPatternExampleSlide,
        RepositoryExercise1Slide,
        RepositoryExercise2Slide
    )
)

object RepositoryPatternExplanationSlide : Slide(
    header = "Repository Pattern (Architectural)",
    summary = {
        +"Mediates between the domain and data mapping layers, providing a collection-like interface for accessing domain objects."
    },
    content = {
        p {
            +"The Repository pattern abstracts data access logic and provides a centralized way to manage domain objects. "
            +"It acts as an in-memory collection of objects, hiding the details of data persistence."
        }

        p { highlight("When to use:") }
        ul {
            li { +"Decoupling business logic from data access" }
            li { +"Centralizing data access logic" }
            li { +"Making it easy to switch data sources (database, API, cache, etc.)" }
            li { +"Improving testability by mocking repositories" }
        }

        p { highlight("Pros & Cons:") }
        ul {
            li { +"✓ Centralized data access logic" }
            li { +"✓ Easy to unit test with mock repositories" }
            li { +"✓ Hides implementation details from business logic" }
            li { +"✗ Can become bloated with many methods" }
            li { +"✗ May introduce extra abstraction layer" }
        }
    }
)

object RepositoryPatternExampleSlide : Slide(
    header = "Repository Pattern - Kotlin Implementation",
//    summary = {
//        +"Example: In-memory user repository with CRUD operations"
//    },
    content = {
        kotlinPlayground(
            code = """
                data class User(val id: Int, val name: String, val email: String)

                interface UserRepository {
                    fun findById(id: Int): User?
                    fun findAll(): List<User>
                    fun save(user: User)
                    fun delete(id: Int)
                }

                class InMemoryUserRepository : UserRepository {
                    private val users = mutableMapOf<Int, User>()
                    private var nextId = 1

                    override fun findById(id: Int): User? = users[id]

                    override fun findAll(): List<User> = users.values.toList()

                    override fun save(user: User) {
                        val id = if (user.id == 0) nextId++ else user.id
                        users[id] = user.copy(id = id)
                    }

                    override fun delete(id: Int) {
                        users.remove(id)
                    }
                }

                fun main() {
                    val repo: UserRepository = InMemoryUserRepository()

                    repo.save(User(0, "Alice", "alice@example.com"))
                    repo.save(User(0, "Bob", "bob@example.com"))

                    println("All users:")
                    repo.findAll().forEach { println(it) }

                    println("\nFind user 1:")
                    println(repo.findById(1))
                }
            """,
            executable = true,
            selectLines = 3..40
        )
    }
)

object RepositoryExercise1Slide : Slide(
    header = "Exercise: Product Repository",
    summary = {
        +"Implement an in-memory product repository with CRUD operations and search functionality."
    },
    content = {
        p {
            +"Create a "; inlineCode("ProductRepository"); +" for managing products in an e-commerce system."
        }

        p { strong { +"Requirements:" } }
        ol {
            li { +"Create "; inlineCode("data class Product(id: Int, name: String, price: Double, category: String)") }
            li { +"Create "; inlineCode("interface ProductRepository"); +" with:" }
            ul {
                li { inlineCode("fun findById(id: Int): Product?") }
                li { inlineCode("fun findAll(): List<Product>") }
                li { inlineCode("fun save(product: Product): Product"); +" - returns saved product with ID" }
                li { inlineCode("fun delete(id: Int): Boolean"); +" - returns true if deleted" }
                li { inlineCode("fun findByCategory(category: String): List<Product>") }
                li { inlineCode("fun findByPriceRange(min: Double, max: Double): List<Product>") }
            }
            li { +"Implement "; inlineCode("class InMemoryProductRepository") }
            li { +"Auto-generate IDs starting from 1" }
        }
        p {
            em { +"Playground is on the next slide $ARROW_DOWN" }
        }
    }
)

object RepositoryExercise2Slide : Slide(
    header = "Exercise: Product Repository",
//    summary = {
//        +"Implement an in-memory product repository with CRUD operations and search functionality."
//    },
    content = {
        kotlinPlayground(
            code = """
                // Your implementation here
                data class Product(
                    val id: Int,
                    val name: String,
                    val price: Double,
                    val category: String
                )
                
                interface ProductRepository {
                    // TODO: Add method signatures
                }
                
                class InMemoryProductRepository : ProductRepository {
                    // TODO: Implement repository
                }
                
                fun main() {
                    val repo: ProductRepository = InMemoryProductRepository()
                
                    // Add products
                    repo.save(Product(0, "Laptop", 999.99, "Electronics"))
                    repo.save(Product(0, "Mouse", 29.99, "Electronics"))
                    repo.save(Product(0, "Desk", 299.99, "Furniture"))
                    repo.save(Product(0, "Chair", 199.99, "Furniture"))
                
                    println("=== All Products ===")
                    repo.findAll().forEach { println(it) }
                
                    println("\n=== Electronics ===")
                    repo.findByCategory("Electronics").forEach { println(it) }
                
                    println("\n=== Products $50-$300 ===")
                    repo.findByPriceRange(50.0, 300.0).forEach { println(it) }
                
                    println("\n=== After deleting product 2 ===")
                    repo.delete(2)
                    repo.findAll().forEach { println(it) }
                }
            """.trimMargin(),
            executable = true,
            selectLines = 9..38
        )
    }
)

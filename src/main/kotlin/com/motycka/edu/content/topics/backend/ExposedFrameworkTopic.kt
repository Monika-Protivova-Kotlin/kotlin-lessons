package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object ExposedFrameworkTopic : Topic(
    title = "Exposed Framework",
    slides = listOf(
        ExposedIntroSlide,
        ExposedTableSchemaSlide,
        ExposedDAOSlide,
        ExposedRepositoryImplementationSlide
    )
)

object ExposedIntroSlide : Slide(
    header = "Exposed Framework",
    summary = {
        +"Exposed is a Kotlin SQL framework that provides a DSL for building SQL queries and mapping results to Kotlin objects."
    },
    content = {
        p {
            +"Key features of Exposed:"
        }
        ul {
            li {
                +"It allows you to define your database schema using Kotlin code and provides a fluent API for querying the database."
            }
            li {
                +"It is developed by JetBrains, the same company that created Kotlin, and is designed to be used with Kotlin applications."
            }
            li {
                +"The Exposed uses a DSL (Domain Specific Language) to build SQL queries and map results to Kotlin objects."
            }
            li {
                +"It also provides a DAO (Data Access Object) layer that allows you to define your database schema and perform CRUD operations "
                +"in an object-oriented way, similar to how you would work with an ORM (Object-Relational Mapping) framework."
            }
            li {
                +"Optionally, you can also write raw SQL queries using Exposed, which allows you to have full control over the SQL queries while still benefiting from the Kotlin type system and IDE support."
            }
            li {
                +"It uses JDBC (Java Database Connectivity) under the hood to interact with the database."
            }
        }
    }
)

object ExposedTableSchemaSlide : Slide(
    header = "Defining Schema in Exposed",
    summary = {
        +"Defining a schema and tables in Exposed using Kotlin objects."
    },
    content = {
        kotlinPlayground(
            code = """
                object MenuItemTable : LongIdTable("menu_item") {
                    val name = text("name")
                    val description = text("description")
                    val price = double("price")
                    val isDeleted = bool("is_deleted").default(false)
                }

                object OrderTable : LongIdTable("order") {
                    val customerName = text("customer_name")
                    val orderDate = datetime("order_date")
                    val totalAmount = double("total_amount")
                }

                object OrderItemTable : Table("order_item") {
                    val menuItemId = long("menu_item_id").references(MenuItemTable.id)
                    val orderId = long("order_id").references(OrderTable.id)
                }
            """.trimIndent(),
            executable = false
        )
        p {
            +"Key concepts:"
        }
        ul {
            li {
                inlineCode("LongIdTable")
                +" - Provides auto-incrementing ID column"
            }
            li {
                inlineCode("Table")
                +" - Basic table without auto-generated ID"
            }
            li {
                inlineCode("references()")
                +" - Creates foreign key relationships"
            }
            li {
                inlineCode("default()")
                +" - Sets default values for columns"
            }
        }
    }
)

object ExposedDAOSlide : Slide(
    header = "Exposed DAO (Data Access Object)",
    summary = {
        +"Defining a DAO class in Exposed for object-relational mapping."
    },
    content = {
        kotlinPlayground(
            code = """
                class MenuItemDAO(id: EntityID<Long>) : LongEntity(id) {
                    var name by MenuItemTable.name
                    var description by MenuItemTable.description
                    var price by MenuItemTable.price
                    var isDeleted by MenuItemTable.isDeleted

                    companion object : LongEntityClass<MenuItemDAO>(MenuItemTable)

                    fun toDTO(): MenuItemDTO {
                        return MenuItemDTO(
                            id = id.value,
                            name = name,
                            description = description,
                            price = price,
                            isDeleted = isDeleted
                        )
                    }
                }
            """.trimIndent(),
            executable = false
        )
        p {
            +"DAO pattern benefits:"
        }
        ul {
            li {
                strong { +"Property Delegation" }
                +" - Uses Kotlin's "
                inlineCode("by")
                +" keyword for property mapping"
            }
            li {
                strong { +"Type Safety" }
                +" - Compile-time checking of property types"
            }
            li {
                strong { +"Convenient Mapping" }
                +" - Easy conversion between database entities and DTOs"
            }
            li {
                strong { +"Lazy Loading" }
                +" - Automatic lazy loading of related entities"
            }
        }
    }
)

object ExposedRepositoryImplementationSlide : Slide(
    header = "Repository Implementation with Exposed",
    summary = {
        +"Implementing a repository layer class using Exposed framework."
    },
    content = {
        p {
            +"The actual repository to be used in the application will implement the methods for accessing the data, "
            +"but it is actually agnostic to the underlying data access technology."
        }
        p {
            +"This is why it is preferable to define an interface for the repository."
        }
        kotlinPlayground(
            code = """
                interface MenuRepository {
                    suspend fun addMenuItem(item: MenuItemDTO): MenuItemDTO
                    suspend fun getMenuItemById(id: MenuItemId): MenuItemDTO?
                    suspend fun getAllMenuItems(filter: String? = null): Set<MenuItemDTO>
                    suspend fun updateMenuItem(updatedItem: MenuItemDTO): Int
                    suspend fun deleteMenuItem(id: MenuItemId): Int
                }
            """.trimIndent(),
            executable = false
        )
        kotlinPlayground(
            code = """
                class MenuRepositoryImpl: MenuRepository {

                    override suspend fun addMenuItem(item: MenuItemDTO): MenuItemDTO = suspendTransaction {
                        MenuItemDAO.new {
                            name = item.name
                            description = item.description
                            price = item.price
                            isDeleted = false
                        }.toDTO()
                    }

                    override suspend fun getMenuItemById(id: MenuItemId): MenuItemDTO? = suspendTransaction {
                        MenuItemDAO.find { (MenuItemTable.id eq id) and (MenuItemTable.isDeleted eq false) }
                            .firstOrNull()
                            ?.toDTO()
                    }
                }
            """.trimIndent(),
            executable = false
        )
    }
)

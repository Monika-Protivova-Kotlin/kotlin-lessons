package com.motycka.edu.content.topics.testing

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.highlight
import kotlinx.html.*
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import com.motycka.edu.model.highlight

object TestingLayersTopic : Topic(
    title = "Testing Application Layers",
    slides = listOf(
        TestingLayersIntroSlide,
        TestingUnitsInIsolationSlide,
        TestingRoutingLayerSlide,
        TestingServiceLayerSlide,
        TestingRepositoryLayerSlide,
        ApplicationIntegrationTestingSlide
    )
)

object TestingLayersIntroSlide : Slide(
    header = "Testing Application Layers - General Idea",
    summary = {
        ul {
            li { +"Testing units in isolation" }
            li { +"Testing layers in isolation" }
            li { +"Testing integrations" }
        }
    },
    content = {
        p {
            +"This approach allows us in detail test each part of the application, verifying it's core functionality, "
            +"without the overhead of testing the entire application at once."
        }
        p {
            +"Interaction between layers and components can be tested using mocks and stubs."
        }
        p {
            +"Integration tests can be used to verify real interaction between different layers and components of the application, "
            +"but because we have tested each part in isolation, we don't need to cover all functionality so deeply."
        }
        p {
            +"The aim is to achieve maximum coverage with minimum resources (and time) "
            +"but still give us a high level of confidence that the application is working correctly."
        }
    }
)

object TestingUnitsInIsolationSlide : Slide(
    header = "Testing Units in Isolation",
    summary = {
        +"Unit tests are used to test individual units of code in isolation, "
        +"without any dependencies on other parts of the application."
    },
    content = {
        p {
            +"This allows for fast and efficient testing of the application logic, "
            +"ensuring that each unit of code is functioning correctly."
        }
        twoColumns(
            left = {
                kotlinPlayground(
                    code = """
                        data class Customer(
                            val id: Long,
                            val discountPercent: Double
                        )

                        data class OrderItem(
                            val menuItemId: Long,
                            val quantity: Int,
                            val price: Double
                        )

                        data class Order(
                            val id: Long,
                            val customer: Customer,
                            val items: List<OrderItem>,
                        ) {

                            fun getPrice(): Double {
                                val fullPrice = items.sumOf { it.price * it.quantity }
                                val discountedPrice = fullPrice * ((100.0 - customer.discountPercent) / 100.0)
                                return discountedPrice
                            }
                        }
                    """.trimIndent(),
                    executable = false
                )
            },
            right = {
                kotlinPlayground(
                    code = """
                        class SampleUnitTest: FunSpec({

                            test("Order price calculation") {
                                val order = Order(
                                    id = 1,
                                    customer = Customer(
                                        id = 1,
                                        discountPercent = 5.0
                                    ),
                                    items = listOf(
                                        OrderItem(
                                            menuItemId = 1,
                                            quantity = 2,
                                            price = 100.0
                                        ),
                                        OrderItem(
                                            menuItemId = 2,
                                            quantity = 1,
                                            price = 200.0
                                        )
                                    )
                                )

                                order.getPrice() shouldBe 380.0
                            }
                        })
                    """.trimIndent(),
                    executable = false
                )
            }
        )
    }
)

object TestingRoutingLayerSlide : Slide(
    header = "Testing Routing Layer",
    summary = {
        +"The purpose of testing the routing layer is to ensure that the application routes are correctly defined and that the routing logic is functioning as expected, "
        +"including the correct handling of HTTP methods, parameters, and responses."
    },
    content = {
        p {
            +"To test the routing layer, we can mock the services that it depends on "
            +"and verify that the services are called with the expected parameters."
        }
        kotlinPlayground(
            code = """
                class MenuEndpointsTest : FunSpec({

                    val menuService = mockk<MenuService>()
                    val jwtToken = "sample.jwt.token"

                    beforeTest {
                        clearAllMocks()
                    }

                    test("GET /api/menuitems should return all menu items") {
                        val menuItems = setOf(
                            MenuItemResponse(id = 1, name = "Item 1", description = "Description 1", price = 10.99),
                            MenuItemResponse(id = 2, name = "Item 2", description = "Description 2", price = 12.99)
                        )

                        coEvery { menuService.getMenuItems(any()) } returns menuItems

                        testApplication {
                            application {
                                install(ContentNegotiation) {
                                    json()
                                }
                                routing {
                                    menuRoutes(menuService, "/api")
                                }
                            }

                            val response = client.get("/api/menuitems") {
                                header(HttpHeaders.Authorization, "Bearer ${DOLLAR}jwtToken")
                            }

                            response.status shouldBe HttpStatusCode.OK
                            val responseBody = response.bodyAsText()
                            val expectedJson = Json.encodeToString(menuItems)
                            responseBody shouldBe expectedJson

                            coVerify(exactly = 1) { menuService.getMenuItems(any()) }
                        }
                    }
                })
            """.trimIndent(),
            executable = false
        )
    }
)

object TestingServiceLayerSlide : Slide(
    header = "Testing Service Layer",
    summary = {
        +"Similarly, we want to test the service layer in isolation to have the most control over "
        +"the test conditions. Again, we can verify the data layer is called with the expected parameters."
    },
    content = {
        kotlinPlayground(
            code = """
                @ExtendWith(MockKExtension::class)
                class MenuServiceTest : FunSpec({

                    val menuRepository = mockk<MenuRepositoryImpl>()
                    val menuService = MenuService(menuRepository)

                    val adminIdentity = IdentityDTO(principal = "admin", role = UserRole.STAFF)
                    val customerIdentity = IdentityDTO(principal = "user", role = UserRole.CUSTOMER)
                    val menuItem1 = MenuItemDTO(id = 1, name = "Espresso", description = "Strong coffee", price = 2.50, isDeleted = false)
                    val menuItem2 = MenuItemDTO(id = 2, name = "Cappuccino", description = "Espresso with milk", price = 3.50, isDeleted = false)

                    beforeTest {
                        clearAllMocks()
                    }

                    test("getMenuItems should return all menu items from repository") {

                        val menuItems = setOf(menuItem1, menuItem2)
                        coEvery { menuRepository.getAllMenuItems(null) } returns menuItems

                        val result = menuService.getMenuItems(null)

                        // Assertions ...

                        coVerify(exactly = 1) { menuRepository.getAllMenuItems(null) }
                    }
                })
            """.trimIndent(),
            executable = false
        )
    }
)

object TestingRepositoryLayerSlide : Slide(
    header = "Testing Repository Layer",
    summary = {
        +"To test the repository layer, we need a database to test with."
    },
    content = {
        p {
            +"We should not mock the database, "
            +"but we can use an in-memory database or containerized database to test the repository layer in isolation."
        }
        p {
            +"We can also use a containerized database, using for example testcontainers library."
        }
        p {
            +"We usually need to seed the database with some initial data to test the repository layer."
        }
        kotlinPlayground(
            code = """
                class MenuRepositoryTest : FunSpec({

                    beforeTest {
                        Database.connect(
                            url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
                            driver = "org.h2.Driver",
                            user = "root",
                            password = ""
                        )

                        transaction {
                            SchemaUtils.create(MenuItemTable)
                        }
                    }

                    afterTest {
                        transaction {
                            SchemaUtils.drop(MenuItemTable)
                        }
                    }

                    test("addMenuItem should add a menu item to the database") {
                        val repository = MenuRepositoryImpl()
                        val menuItem = MenuItemDTO(id = null, name = "Test Item", description = "Test Description", price = 9.99, isDeleted = false)

                        val result = repository.addMenuItem(menuItem)

                        // Assertions ...
                    }
                })
            """.trimIndent(),
            executable = false
        )
    }
)

object ApplicationIntegrationTestingSlide : Slide(
    header = "Integration Testing",
    summary = {
        +"The purpose of integration testing is to verify that the different layers of the application work together as expected."
    },
    content = {
        p {
            +"For integration testing, the application is started either in a test instance or in a container, "
            +"and a real database is used."
        }
        p {
            +"This is the only way to test certain aspects of the application, such as dependency injection."
        }
        p {
            +"The tests are executed as client-like requests to the application, so in our case calls to the REST API."
        }
        p {
            highlight("Integration test benefits:")
        }
        ul {
            li { +"Tests real interactions between all layers" }
            li { +"Verifies dependency injection configuration" }
            li { +"Validates end-to-end functionality" }
            li { +"Catches issues that unit tests might miss" }
        }
        p {
            highlight("Integration test considerations:")
        }
        ul {
            li { +"Slower execution than unit tests" }
            li { +"Requires more setup (database, test data)" }
            li { +"More complex to debug when failures occur" }
            li { +"Should complement, not replace, unit tests" }
        }
    }
)

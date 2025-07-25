package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object SpringBootControllerTestingTopic : Topic(
    title = "Testing Controllers",
    slides = listOf(
        MockingAndSpyingSlide,
        ControllerTestingSlide
    )
)

object MockingAndSpyingSlide : Slide(
    header = "Mocking and spying",
    summary = {
        +"Mocking and spying are techniques used in testing to simulate the behavior of the objects that the class under test depends on."
    },
    content = {
        p {
            +"In other words, we can control the internal behavior of the dependencies form the tests."
        }
        p {
            +"The \"poor man's\" version of mocking can be when we crate an alternative implementation of the class that is a dependency of the class under test."
        }
        p {
            +"In reality, we use mocking frameworks, such as Mockito or MockK, to create mock objects and spy objects."
        }
    }
)

object ControllerTestingSlide : Slide(
    header = "Testing Controllers",
    content = {
        p {
            +"Testing controllers is relatively simple, because they are just classes that handle the HTTP "
            +"requests and return the HTTP responses. However, we need some tools to simulate the HTTP "
            +"requests and responses."
        }
        kotlinPlayground(
            """
            |package com.motycka.edu.game.character
            |
            |import com.motycka.edu.game.account.AccountService
            |import com.motycka.edu.game.character.model.CharacterLevel
            |import com.motycka.edu.game.character.model.Warrior
            |import com.motycka.edu.game.config.TestSecurityConfiguration
            |import io.mockk.every
            |import io.mockk.mockk
            |import io.mockk.verify
            |import org.junit.jupiter.api.BeforeEach
            |import org.junit.jupiter.api.Test
            |import org.springframework.beans.factory.annotation.Autowired
            |import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
            |import org.springframework.boot.test.mock.mockito.MockBean
            |import org.springframework.context.annotation.Import
            |import org.springframework.test.web.servlet.MockMvc
            |import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
            |import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
            |import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
            |import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
            |import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic
            |
            |@WebMvcTest(CharacterController::class)
            |@Import(TestSecurityConfiguration::class)
            |class CharacterControllerTest {
            |
            |    @Autowired
            |    private lateinit var mockMvc: MockMvc
            |
            |    @MockBean
            |    private lateinit var characterService: CharacterService
            |
            |    @MockBean
            |    private lateinit var accountService: AccountService
            |
            |    private val accountId = 1L
            |
            |    @BeforeEach
            |    fun setUp() {
            |        characterService = mockk()
            |        accountService = mockk()
            |    }
            |
            |    @Test
            |    fun `postCharacter should return created character`() {
            |        val character = Warrior(
            |            id = 1,
            |            accountId = accountId,
            |            name = "Kotlin Warrior",
            |            health = 140,
            |            attackPower = 20,
            |            experience = 0,
            |            stamina = 20,
            |            defensePower = 20,
            |            level = CharacterLevel.LEVEL_1
            |        )
            |
            |        every { accountService.getCurrentAccountId() } returns accountId
            |        every { characterService.createCharacter(any()) } returns character
            |
            |        mockMvc.perform(post("/api/characters")
            |            .contentType("application/json")
            |            .content(${"\"\"\""}
            |            {
            |              "name": "${'$'}{character.name}",
            |              "health": ${'$'}{character.health},
            |              "attackPower": ${'$'}{character.attackPower},
            |              "stamina": ${'$'}{character.stamina},
            |              "defensePower": ${'$'}{character.defensePower},
            |              "characterClass": "WARRIOR"
            |            }
            |            ${"\"\"\""}.trimIndent())
            |            .with(csrf())
            |            .with(httpBasic("username", "password")))
            |            .andExpect(status().isOk)
            |            .andExpect(content().json(
            |                ${"\"\"\""}
            |                {
            |                  "id": ${'$'}{character.characterId},
            |                  "name": "${'$'}{character.name}",
            |                  "health": ${'$'}{character.health},
            |                  "attackPower": ${'$'}{character.attackPower},
            |                  "stamina": ${'$'}{character.stamina},
            |                  "defensePower": ${'$'}{character.defensePower},
            |                  "characterClass": "WARRIOR",
            |                  "level": "LEVEL_1",
            |                  "experience": 0,
            |                  "shouldLevelUp": false,
            |                  "isOwner": true,
            |                  "mana": null,
            |                  "healingPower": null
            |                }
            |            ${"\"\"\""}.trimIndent()
            |            ))
            |
            |        verify { characterService.createCharacter(character = character) }
            |    }
            |}
            """,
            executable = false,
            showLines = false
        )
        ul {
            li {
                +"Spring Boot provides the "
                inlineCode("MockMvc")
                +" class to simulate the HTTP requests and responses."
            }
            li {
                inlineCode("@WebMvcTest")
                +" annotation with the controller class to be tested will establish a Spring context."
            }
            li {
                inlineCode("@Autowired")
                +" annotation is used to inject the "
                inlineCode("MockMvc")
                +" bean into the test class."
            }
            li {
                +"Ideally, we want to test just the controller, but not the service and repository layers, "
                +"so we can use the "
                inlineCode("@MockBean")
                +" annotation to mock the service and repository beans."
            }
            li {
                +"We can mock the service layer using any mocking library, such as Mockito. "
                +"In this case we use "
                inlineCode("mockk.every")
                +" to mock the service method and "
                inlineCode("mockk.verify")
                +" to verify the calls."
            }
        }
    },
    fontSize = "70%"
)

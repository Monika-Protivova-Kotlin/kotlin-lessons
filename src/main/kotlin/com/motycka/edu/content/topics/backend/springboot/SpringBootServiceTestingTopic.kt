package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.*

object SpringBootServiceTestingTopic : Topic(
    title = "Testing Services",
    slides = listOf(
        ServiceTestingSlide
    )
)

object ServiceTestingSlide : Slide(
    header = "Testing Services",
    content = {
        p {
            +"Testing the service layer is perhaps the easiest, because we only need to mock the repository layer. "
            +"There are multiple approaches you can take."
        }
        twoColumns(
            left = {
                +"Either you can test the service layer using Spring Boot's dependency injection,"
                kotlinPlayground(
                    """
                    |package com.motycka.edu.game.character
                    |
                    |import com.motycka.edu.game.account.AccountService
                    |import com.motycka.edu.game.character.model.CharacterLevel
                    |import com.motycka.edu.game.character.model.Warrior
                    |import com.motycka.edu.game.character.rest.CharactersFilter
                    |import org.junit.jupiter.api.Assertions.assertEquals
                    |import org.junit.jupiter.api.BeforeEach
                    |import org.junit.jupiter.api.Test
                    |import org.springframework.beans.factory.annotation.Autowired
                    |import org.springframework.boot.test.context.SpringBootTest
                    |import org.springframework.transaction.annotation.Transactional
                    |
                    |@SpringBootTest
                    |@Transactional
                    |class CharacterServiceIT {
                    |
                    |    @Autowired
                    |    private lateinit var characterRepository: CharacterRepository
                    |
                    |    @Autowired
                    |    private lateinit var accountService: AccountService
                    |
                    |    @Autowired
                    |    private lateinit var characterService: CharacterService
                    |
                    |    private val accountId = 1L
                    |
                    |    @Test
                    |    fun `createCharacter should return created character`() {
                    |        val character = Warrior(
                    |            id = 1,
                    |            accountId = accountId,
                    |            name = "Warrior",
                    |            health = 140,
                    |            attackPower = 20,
                    |            experience = 0,
                    |            stamina = 20,
                    |            defensePower = 20,
                    |            level = CharacterLevel.LEVEL_1
                    |        )
                    |
                    |        val result = characterService.createCharacter(character)
                    |
                    |        assertEquals(character, result)
                    |        val savedCharacter = characterRepository.selectWithFilter(
                    |            accountId = accountId,
                    |            filter = CharactersFilter.DEFAULT.copy(ids = setOf(character.characterId))
                    |        )
                    |        assertEquals(character, savedCharacter)
                    |    }
                    |}
                    """,
                    executable = false,
                    showLines = false
                )
                +"The advantage of this is you are testing the service can be injected correctly. "
                +"The disadvantage is that the test may be slower and more resource consuming."
            },
            right = {
                br()
                +"or you can just inject the dependency yourself."
                kotlinPlayground(
                    """
                    |package com.motycka.edu.game.character
                    |
                    |import com.motycka.edu.game.account.AccountService
                    |import com.motycka.edu.game.character.model.CharacterLevel
                    |import com.motycka.edu.game.character.model.Warrior
                    |import io.mockk.every
                    |import io.mockk.mockk
                    |import io.mockk.verify
                    |import org.junit.jupiter.api.Assertions.assertEquals
                    |import org.junit.jupiter.api.Test
                    |
                    |class CharacterServiceTest {
                    |
                    |    private val characterRepository: CharacterRepository = mockk()
                    |    private val accountService: AccountService  = mockk()
                    |    private val characterService: CharacterService = CharacterService(
                    |        characterRepository = characterRepository,
                    |        accountService = accountService
                    |    )
                    |
                    |    private val accountId = 1L
                    |
                    |    @Test
                    |    fun `createCharacter should return created character`() {
                    |        val character = Warrior(
                    |            id = 1,
                    |            accountId = accountId,
                    |            name = "Warrior",
                    |            health = 140,
                    |            attackPower = 20,
                    |            experience = 0,
                    |            stamina = 20,
                    |            defensePower = 20,
                    |            level = CharacterLevel.LEVEL_1
                    |        )
                    |
                    |        every { accountService.getCurrentAccountId() } returns accountId
                    |        every { characterRepository.insertCharacters(accountId = accountId, character = character) } returns character
                    |
                    |        val result = characterService.createCharacter(character)
                    |
                    |        assertEquals(character, result)
                    |        verify { characterRepository.insertCharacters(accountId = accountId, character = character) }
                    |    }
                    |
                    |}
                    """,
                    executable = false,
                    showLines = false
                )
                +"This test is going to be faster, but you are not testing the DI, "
                +"which is not a big deal if you also have integration tests."
            }
        )
    },
    fontSize = "80%"
)

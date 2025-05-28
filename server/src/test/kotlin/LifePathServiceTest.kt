
import za.co.markxh.backpacklifesim.LifePathService
import za.co.markxh.backpacklifesim.domain.model.Choice
import za.co.markxh.backpacklifesim.domain.model.Decision
import java.time.LocalDate
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LifePathServiceTest {

    @BeforeTest
    fun setup() {
        LifePathService.clearEvents()
    }

    @Test
    fun `applyChoices adds events to life path`() {
        // Given
        val choices = listOf(
            Choice(itemId = "1", name = "Old Compass", decision = Decision.KEEP),
            Choice(itemId = "2", name = "Cup of Coffee", decision = Decision.USE),
        )

        // When
        LifePathService.applyChoices(choices)

        // Then
        val lifePath = LifePathService.getLifePath()
        assertEquals(2, lifePath.events.size)
        assertEquals("Old Compass", lifePath.events[0].title)
        assertEquals("You chose to hold onto the item.", lifePath.events[0].description)
        assertEquals(LocalDate.now().toString(), lifePath.events[0].date)
    }

    @Test
    fun `clearEvents removes all life events`() {
        LifePathService.applyChoices(
            listOf(Choice(itemId = "1", name = "Test Item", decision = Decision.TOSS))
        )
        LifePathService.clearEvents()

        val lifePath = LifePathService.getLifePath()
        assertTrue(lifePath.events.isEmpty())
    }
}
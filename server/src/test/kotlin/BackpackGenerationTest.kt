import za.co.markxh.backpacklifesim.generateBackpack
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BackpackGenerationTest {
    @Test
    fun `generateBackpack returns 3 shuffled items`() {
        val backpack = generateBackpack()
        assertEquals(3, backpack.items.size)
        assertTrue(backpack.items.all { it.name.isNotBlank() })
    }
}
import za.co.markxh.backpacklifesim.domain.model.Backpack
import za.co.markxh.backpacklifesim.domain.model.Choice
import za.co.markxh.backpacklifesim.domain.model.Item
import za.co.markxh.backpacklifesim.domain.model.ItemType
import za.co.markxh.backpacklifesim.domain.model.LifeEvent
import za.co.markxh.backpacklifesim.domain.model.LifePath
import za.co.markxh.backpacklifesim.domain.model.Rarity
import za.co.markxh.backpacklifesim.domain.repository.BackpackRepository

class FakeBackpackRepository : BackpackRepository {
    override suspend fun loadBackPack(): Backpack {
        return generateBackpack()
    }

    private fun generateBackpack(): Backpack {
        val items = listOf(
            Item(
                name = "Old Compass",
                description = "Still points somewhere.",
                type = ItemType.TOOL,
                rarity = Rarity.UNCOMMON,
                effect = "Changes your career path to explorer.",
                imageUrl = "http://10.0.2.2:8080/static/images/compass.webp"
            ),
            Item(
                name = "Stale Bread",
                description = "Not fresh, but still edible.",
                type = ItemType.CONSUMABLE,
                rarity = Rarity.COMMON,
                effect = "Restores minimal energy.",
                imageUrl = "http://10.0.2.2:8080/static/images/bread.webp"
            ),
            Item(
                name = "Lucky Coin",
                description = "You never know when luck strikes.",
                type = ItemType.SENTIMENTAL,
                rarity = Rarity.RARE,
                effect = "Improves success chance in future encounters.",
                imageUrl = "http://10.0.2.2:8080/static/images/coin.webp"
            )
        )

        return Backpack("2025-01-01", items)
    }

    override suspend fun submitChoices(choices: List<Choice>) {
        // Record or no-op
    }

    override suspend fun fetchLifePath(): LifePath {
        return LifePath(
            listOf(
                LifeEvent(
                    "2025-01-01",
                    "You became an explorer.",
                    "compass",
                    "Explorer"
                )
            )
        )
    }
}
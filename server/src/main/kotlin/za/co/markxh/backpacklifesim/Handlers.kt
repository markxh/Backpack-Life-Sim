package za.co.markxh.backpacklifesim

import za.co.markxh.backpacklifesim.domain.model.Backpack
import za.co.markxh.backpacklifesim.domain.model.Item
import za.co.markxh.backpacklifesim.domain.model.ItemType
import za.co.markxh.backpacklifesim.domain.model.Rarity
import java.time.LocalDate

fun generateBackpack(): Backpack {
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
            name = "Cup of Coffee",
            description = "Smells energizing.",
            type = ItemType.CONSUMABLE,
            rarity = Rarity.COMMON,
            effect = "Gives short-term productivity boost.",
            imageUrl = "http://10.0.2.2:8080/static/images/coffee.webp"
        ),
        Item(
            name = "Broken Umbrella",
            description = "It's seen better days.",
            type = ItemType.JUNK,
            rarity = Rarity.COMMON,
            effect = "Avoids sickness if tossed.",
            imageUrl = "http://10.0.2.2:8080/static/images/ugly_umbrella.webp"
        )
    ).shuffled()

    return Backpack(
        date = LocalDate.now().toString(),
        items = items
    )
}

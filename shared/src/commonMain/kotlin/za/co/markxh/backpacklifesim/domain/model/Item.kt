package za.co.markxh.backpacklifesim.domain.model

import kotlinx.serialization.Serializable
import za.co.markxh.backpacklifesim.utils.generateUUID

@Serializable
data class Item(
    val id: String = generateUUID(),
    val name: String,
    val description: String,
    val type: ItemType,
    val rarity: Rarity,
    val effect: String,
    val imageUrl: String
)
package za.co.markxh.backpacklifesim.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Backpack(
    val date: String,
    val items: List<Item>
)
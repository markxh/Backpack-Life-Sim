package za.co.markxh.backpacklifesim.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LifeEvent(
    val date: String,
    val description: String,
    val iconName: String,
    val title: String
)
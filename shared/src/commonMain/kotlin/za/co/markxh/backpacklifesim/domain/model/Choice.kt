package za.co.markxh.backpacklifesim.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Choice(
    val itemId: String,
    val decision: Decision
)
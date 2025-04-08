package za.co.markxh.backpacklifesim.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LifePath(
    val events: List<LifeEvent>
)
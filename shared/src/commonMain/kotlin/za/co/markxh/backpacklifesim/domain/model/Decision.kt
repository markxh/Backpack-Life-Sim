package za.co.markxh.backpacklifesim.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class Decision {
    KEEP,
    USE,
    TOSS;

    fun displayName(): String {
        return name.lowercase().replaceFirstChar { it.uppercase() }
    }

    companion object {
        val entries: List<Decision>
            get() = values().toList()
    }
}
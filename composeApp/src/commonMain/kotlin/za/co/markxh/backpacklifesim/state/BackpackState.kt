package za.co.markxh.backpacklifesim.state

import za.co.markxh.backpacklifesim.domain.model.Backpack

sealed class BackpackState {
    object Loading : BackpackState()
    data class Loaded(val backpack: Backpack) : BackpackState()
    data class Error(val message: String) : BackpackState()
    object Submitted : BackpackState()
}
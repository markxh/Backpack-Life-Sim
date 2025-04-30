package za.co.markxh.backpacklifesim.state

import za.co.markxh.backpacklifesim.domain.model.LifePath

sealed class LifePathState {
    object Loading : LifePathState()
    object Idle : LifePathState()
    data class Loaded(val lifePath: LifePath) : LifePathState()
    data class Error(val message: String) : LifePathState()
}
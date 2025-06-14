package za.co.markxh.backpacklifesim.domain.repository

import za.co.markxh.backpacklifesim.domain.model.Backpack
import za.co.markxh.backpacklifesim.domain.model.Choice
import za.co.markxh.backpacklifesim.domain.model.LifePath

interface BackpackRepository {
    suspend fun loadBackPack(): Backpack
    suspend fun submitChoices(choices: List<Choice>): Result<Unit>
    suspend fun fetchLifePath(): LifePath
}
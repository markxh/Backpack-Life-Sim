package za.co.markxh.backpacklifesim.ui

import za.co.markxh.backpacklifesim.domain.model.Backpack
import za.co.markxh.backpacklifesim.domain.repository.BackpackRepository

class LoadBackpackUseCase(
    private val backpackRepository: BackpackRepository
) {
    suspend operator fun invoke(): Backpack {
        return backpackRepository.loadBackPack()
    }
}
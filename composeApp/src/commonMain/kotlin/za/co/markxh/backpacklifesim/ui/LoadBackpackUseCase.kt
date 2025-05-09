package za.co.markxh.backpacklifesim.ui

import za.co.markxh.backpacklifesim.domain.model.Backpack
import za.co.markxh.backpacklifesim.domain.repository.BackpackRepository

open class LoadBackpackUseCase(
    private val backpackRepository: BackpackRepository
) {
    open suspend operator fun invoke(): Backpack {
        return backpackRepository.loadBackPack()
    }
}
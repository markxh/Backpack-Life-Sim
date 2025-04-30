package za.co.markxh.backpacklifesim.ui

import za.co.markxh.backpacklifesim.domain.model.LifePath
import za.co.markxh.backpacklifesim.domain.repository.BackpackRepository

class LoadLifePathUseCase(
    private val backpackRepository: BackpackRepository
) {
    suspend operator fun invoke(): LifePath {
        return backpackRepository.getLifePath()
    }
}
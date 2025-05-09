package za.co.markxh.backpacklifesim.ui

import za.co.markxh.backpacklifesim.domain.model.LifePath
import za.co.markxh.backpacklifesim.domain.repository.BackpackRepository

open class LoadLifePathUseCase(
    private val backpackRepository: BackpackRepository
) {
    open suspend operator fun invoke(): LifePath {
        return backpackRepository.getLifePath()
    }
}
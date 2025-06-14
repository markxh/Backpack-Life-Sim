package za.co.markxh.backpacklifesim.ui

import za.co.markxh.backpacklifesim.domain.model.Choice
import za.co.markxh.backpacklifesim.domain.repository.BackpackRepository

open class SubmitChoicesUseCase(
    private val backpackRepository: BackpackRepository
) {
    open suspend operator fun invoke(choices: List<Choice>): Result<Unit> {
        return backpackRepository.submitChoices(choices)
    }
}
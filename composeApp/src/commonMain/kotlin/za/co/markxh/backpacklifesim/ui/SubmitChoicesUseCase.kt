package za.co.markxh.backpacklifesim.ui

import za.co.markxh.backpacklifesim.domain.model.Choice
import za.co.markxh.backpacklifesim.domain.repository.BackpackRepository

class SubmitChoicesUseCase(
    private val backpackRepository: BackpackRepository
) {
    suspend operator fun invoke(choices: List<Choice>) {
        backpackRepository.submitChoices(choices)
    }
}
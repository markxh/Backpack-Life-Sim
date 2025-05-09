import za.co.markxh.backpacklifesim.domain.model.Choice
import za.co.markxh.backpacklifesim.ui.SubmitChoicesUseCase

class FakeSubmitChoicesUseCase(private val repository: FakeBackpackRepository) : SubmitChoicesUseCase(repository) {
    override suspend fun invoke(choices: List<Choice>) = repository.submitChoices(choices)
}
import za.co.markxh.backpacklifesim.domain.model.LifePath
import za.co.markxh.backpacklifesim.ui.LoadLifePathUseCase

class FakeLoadLifePathUseCase(private val repository: FakeBackpackRepository) : LoadLifePathUseCase(repository) {
    override suspend fun invoke(): LifePath = repository.fetchLifePath()
}
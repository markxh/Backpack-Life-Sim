import za.co.markxh.backpacklifesim.domain.model.Backpack
import za.co.markxh.backpacklifesim.ui.LoadBackpackUseCase

class FakeLoadBackpackUseCase(private val repository: FakeBackpackRepository) : LoadBackpackUseCase(repository) {
    override suspend fun invoke(): Backpack {
        return repository.loadBackPack()
    }
}
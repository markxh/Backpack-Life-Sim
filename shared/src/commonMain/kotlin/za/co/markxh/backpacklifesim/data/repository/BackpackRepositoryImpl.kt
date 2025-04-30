package za.co.markxh.backpacklifesim.data.repository

import za.co.markxh.backpacklifesim.domain.model.Backpack
import za.co.markxh.backpacklifesim.domain.model.Choice
import za.co.markxh.backpacklifesim.domain.model.LifePath
import za.co.markxh.backpacklifesim.domain.repository.BackpackRepository
import za.co.markxh.backpacklifesim.network.ApiClient

class BackpackRepositoryImpl(
    private val apiClient: ApiClient
) : BackpackRepository {

    override suspend fun loadBackPack(): Backpack {
        return apiClient.fetchBackpack()
    }

    override suspend fun submitChoices(choices: List<Choice>) {
        apiClient.submitChoices(choices)
    }

    override suspend fun getLifePath(): LifePath {
        return apiClient.fetchLifePath()
    }
}
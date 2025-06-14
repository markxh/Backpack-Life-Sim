package za.co.markxh.backpacklifesim.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module
import za.co.markxh.backpacklifesim.data.repository.BackpackRepositoryImpl
import za.co.markxh.backpacklifesim.domain.repository.BackpackRepository
import za.co.markxh.backpacklifesim.network.ApiClient
import za.co.markxh.backpacklifesim.ui.BackpackViewModel
import za.co.markxh.backpacklifesim.ui.LoadBackpackUseCase
import za.co.markxh.backpacklifesim.ui.LoadLifePathUseCase
import za.co.markxh.backpacklifesim.ui.SubmitChoicesUseCase
import za.co.markxh.backpacklifesim.util.Clock
import za.co.markxh.backpacklifesim.util.platformClock

val androidModule = module {
    single<HttpClient> {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json()
            }
        }
    }

    single { ApiClient() }
    single<BackpackRepository> { BackpackRepositoryImpl(get()) }

    single<Clock> { platformClock() }
    single { LoadBackpackUseCase(get()) }
    single { SubmitChoicesUseCase(get()) }
    single { LoadLifePathUseCase(get()) }

    single { BackpackViewModel(get(), get(), get(), get()) }
}
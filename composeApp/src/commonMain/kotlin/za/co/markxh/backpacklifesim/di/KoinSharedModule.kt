package za.co.markxh.backpacklifesim.di

import za.co.markxh.backpacklifesim.data.repository.BackpackRepositoryImpl
import za.co.markxh.backpacklifesim.domain.repository.BackpackRepository
import org.koin.dsl.module

val sharedModule = module {
    single<BackpackRepository> { BackpackRepositoryImpl(get()) }
    // More shared logic
}
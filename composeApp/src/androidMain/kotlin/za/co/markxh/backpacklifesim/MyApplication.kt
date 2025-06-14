package za.co.markxh.backpacklifesim

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import za.co.markxh.backpacklifesim.di.androidModule
import za.co.markxh.backpacklifesim.di.sharedModule

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(sharedModule, androidModule)
        }
    }
}
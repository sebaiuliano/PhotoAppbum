package com.siuliano.photoappbum.application

import android.app.Application
import com.siuliano.photoappbum.application.modules.apiRepositoryModule
import com.siuliano.photoappbum.application.modules.networkModule
import com.siuliano.photoappbum.application.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PhotoAppbum : Application() {
    override fun onCreate() {
        super.onCreate()
        initiateKoin()
    }

    private fun initiateKoin() {
        startKoin {
            androidContext(this@PhotoAppbum)
            modules(listOf(
                networkModule,
                viewModelModule,
                apiRepositoryModule
            ))
        }
    }
}
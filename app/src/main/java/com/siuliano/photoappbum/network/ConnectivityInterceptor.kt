package com.siuliano.photoappbum.network

import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.IOException

@KoinApiExtension
class ConnectivityInterceptor: Interceptor, KoinComponent {
    private val networkService : NetworkService by inject()

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkService.isOnline()) {
            throw IOException("No internet connection")
        } else {
            return chain.proceed(chain.request())
        }
    }
}
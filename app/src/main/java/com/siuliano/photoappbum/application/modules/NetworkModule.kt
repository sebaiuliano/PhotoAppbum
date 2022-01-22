package com.siuliano.photoappbum.application.modules

import android.content.Context
import com.siuliano.photoappbum.application.modules.Properties.BASE_URL
import com.siuliano.photoappbum.network.ConnectivityInterceptor
import com.siuliano.photoappbum.network.WifiService
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinApiExtension
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Properties {
    var BASE_URL = "https://jsonplaceholder.typicode.com"
}

val networkModule = module {

    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ConnectivityInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build()
    }

    fun provideDataSourceApi(client: OkHttpClient, moshi: Moshi, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }

    fun provideMoshi() = Moshi.Builder()
        .build()

    fun provideWifiService(context: Context) : WifiService {
        return WifiService(context)
    }

    single {
        provideHttpClient()
    }

    single {
        provideDataSourceApi(
            get(named("http")),
            get(),
            BASE_URL
        )
    }

    single { provideMoshi() }

    single { provideWifiService(androidContext()) }
}
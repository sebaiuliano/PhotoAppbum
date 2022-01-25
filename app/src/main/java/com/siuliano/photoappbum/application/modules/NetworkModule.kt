package com.siuliano.photoappbum.application.modules

import android.content.Context
import com.siuliano.photoappbum.application.modules.Properties.BASE_URL
import com.siuliano.photoappbum.network.ConnectivityInterceptor
import com.siuliano.photoappbum.network.NetworkService
import com.siuliano.photoappbum.network.endpoints.DataSourceApi
import com.siuliano.photoappbum.network.repositories.DataSourceRepositoryImpl
import com.siuliano.photoappbum.repositories.DataSourceRepository
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
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

    fun provideMoshi() = Moshi.Builder()
        .build()

    fun provideRetrofit(httpClient: OkHttpClient, moshi: Moshi, baseUrl: String) : Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpClient)
            .build()

    fun provideDataSourceApi(retrofit: Retrofit): DataSourceApi =
        retrofit.create(DataSourceApi::class.java)

    fun provideDataSourceApiRepository(dataSourceApiRepositoryImpl: DataSourceRepositoryImpl): DataSourceRepository = dataSourceApiRepositoryImpl

    fun provideWifiService(context: Context) : NetworkService {
        return NetworkService(context)
    }

    single { provideHttpClient() }

    single { provideMoshi() }

    single { provideRetrofit(get(), get(), BASE_URL) }

    single { provideDataSourceApi(get()) }

    single { provideDataSourceApiRepository(get()) }

    single { provideWifiService(androidContext()) }
}
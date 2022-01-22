package com.siuliano.photoappbum.application.modules

import com.siuliano.photoappbum.network.repositories.DataSourceRepositoryImpl
import org.koin.dsl.module

val apiRepositoryModule = module {
    single { DataSourceRepositoryImpl(get()) }
}
package com.siuliano.photoappbum.application.modules

import com.siuliano.photoappbum.network.repositories.DataSourceRepositoryImpl
import com.siuliano.photoappbum.repositories.DataSourceRepository
import org.koin.dsl.module

val apiRepositoryModule = module {
    single { DataSourceRepositoryImpl(get()) }
}
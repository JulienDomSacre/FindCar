package com.juliensacre.findcar.internal

import com.juliensacre.findcar.data.Repository
import com.juliensacre.findcar.data.RepositoryImpl
import com.juliensacre.findcar.data.local.CarDatabase
import com.juliensacre.findcar.data.remote.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val appModule = module {
    single { CarDatabase(androidContext()) }
    single { get<CarDatabase>().carDao() }
    single { CarAPIService(get()) }

    single<Repository> { RepositoryImpl(get(), get())}
    single<ConnectivityInterceptor> { ConnectivityInterceptorImpl(androidContext()) }
    single<CarDataSource> { CarDataSourceImpl(get()) }
}
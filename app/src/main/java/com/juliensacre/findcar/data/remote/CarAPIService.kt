package com.juliensacre.findcar.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.juliensacre.findcar.BuildConfig
import com.juliensacre.findcar.data.local.entity.Car
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface CarAPIService {
    @GET("tc-test-ios.json")
    fun getCars() : Deferred<List<Car>>

    //Singleton
    companion object {
        //operator fun invoke() <- override the operator () for allows you to initialize the class just with: RickAndMortyApiService()
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor //for di
        ) : CarAPIService{
            val okHttpClientBuilder = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(connectivityInterceptor) //<- use di for don't have dependency with the context

            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                okHttpClientBuilder.addInterceptor(logging)
            }

            return Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com/ncltg/6a74a0143a8202a5597ef3451bde0d5a/raw/8fa93591ad4c3415c9e666f888e549fb8f945eb7/")
                .addConverterFactory(GsonConverterFactory.create()) // use GSon
                .addCallAdapterFactory(CoroutineCallAdapterFactory()) //use coroutine
                .client(okHttpClientBuilder.build()) //add timeout
                .build()
                .create(CarAPIService::class.java)
        }
    }
}
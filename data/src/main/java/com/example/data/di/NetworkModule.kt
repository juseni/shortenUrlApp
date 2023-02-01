package com.example.data.di

import android.content.Context
import com.example.data.BuildConfig
import com.example.data.network.ShortenApiClient
import com.example.data.network.ShortenService
import com.example.data.platform.NetworkHandler
import com.example.data.platform.NetworkInterceptor
import com.example.data.utils.TIME_OUT_CONNECTION
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClientBuilder: OkHttpClient.Builder, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BuildConfig.SERVER_URL)
            .client(okHttpClientBuilder.build())
            .build()
    }

    @Provides
    fun provideOkHttpClientBuilder(networkInterceptor: NetworkInterceptor): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
            .readTimeout(TIME_OUT_CONNECTION, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT_CONNECTION, TimeUnit.SECONDS)
        builder.addInterceptor(networkInterceptor)
        return builder
    }

    @Singleton
    @Provides
    fun provideShortenApiClient(retrofit: Retrofit): ShortenApiClient =
        retrofit.create(ShortenApiClient::class.java)

    @Singleton
    @Provides
    fun provideNetworkHandler(context: Context) = NetworkHandler(context)

    @Singleton
    @Provides
    fun provideShortenService(apiClient: ShortenApiClient) = ShortenService(apiClient)
}
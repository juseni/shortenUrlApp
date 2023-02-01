package com.example.data.di

import com.example.data.db.dao.ShortenDao
import com.example.data.network.ShortenService
import com.example.data.platform.NetworkHandler
import com.example.data.repository.ShortenRepositoryImpl
import com.example.domain.repository.ShortenRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideShortenRepository(
        api: ShortenService,
        gson: Gson,
        dao: ShortenDao,
        networkChecker: NetworkHandler
    ) : ShortenRepository =
        ShortenRepositoryImpl(
            api,
            gson,
            dao,
            networkChecker
        )
}
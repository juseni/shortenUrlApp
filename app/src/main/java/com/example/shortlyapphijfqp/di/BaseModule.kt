package com.example.shortlyapphijfqp.di

import android.app.Application
import android.content.Context
import androidx.navigation.NavController
import com.example.domain.repository.ShortenRepository
import com.example.domain.usecases.DeleteShortenUseCase
import com.example.domain.usecases.GetShortenUrlsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseModule {

    @Provides
    fun provideContext(app: Application): Context = app

    @Singleton
    @Provides
    fun provideNavigatorController(context: Context): NavController {
        return NavController(context)
    }

    @Singleton
    @Provides
    fun provideGetShortenUrlsUseCase(
        repository: ShortenRepository
    ): GetShortenUrlsUseCase {
        return GetShortenUrlsUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideDeleteShortenUseCase(
        repository: ShortenRepository
    ): DeleteShortenUseCase {
        return DeleteShortenUseCase(repository)
    }
}
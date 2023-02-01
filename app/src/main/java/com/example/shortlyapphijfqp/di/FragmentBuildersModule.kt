package com.example.shortlyapphijfqp.di

import com.example.shortlyapphijfqp.view.fragments.FirstScreenFragment
import com.example.shortlyapphijfqp.view.fragments.ShortenUrlsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun providesFirstScreenFragment(): FirstScreenFragment

    @ContributesAndroidInjector
    abstract fun providesShortenUrlsFragment(): ShortenUrlsFragment
}
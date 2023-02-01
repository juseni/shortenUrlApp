package com.example.shortlyapphijfqp.di

import com.example.shortlyapphijfqp.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}
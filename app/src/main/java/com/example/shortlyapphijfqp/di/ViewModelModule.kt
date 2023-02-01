package com.example.shortlyapphijfqp.di

import androidx.lifecycle.ViewModel
import com.example.shortlyapphijfqp.viewmodel.ShortenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ShortenViewModel::class)
    abstract fun bindShortenViewModel(viewModel: ShortenViewModel): ViewModel
}
package com.example.shortlyapphijfqp.di

import android.app.Application
import com.example.data.di.DataBaseModule
import com.example.data.di.NetworkModule
import com.example.data.di.RepositoryModule
import com.example.shortlyapphijfqp.ShortlyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        BaseModule::class,
        ActivityBuildersModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        DataBaseModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<ShortlyApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}
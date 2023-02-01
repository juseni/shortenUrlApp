package com.example.shortlyapphijfqp

import com.example.shortlyapphijfqp.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ShortlyApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent
            .builder()
            .application(this)
            .build() }
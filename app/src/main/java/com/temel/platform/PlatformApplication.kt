package com.temel.platform

import com.temel.platform.app.di.DaggerMainComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class PlatformApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>
            = DaggerMainComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()
    }
}
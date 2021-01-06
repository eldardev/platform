package com.temel.platform.app.di

import com.temel.platform.app.coordinator.Router
import dagger.Module
import dagger.Provides

@Module
class NavModule {

    @AppScope
    @Provides
    fun router(): Router {
        return Router()
    }
}
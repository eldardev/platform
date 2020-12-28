package com.temel.platform.app.di

import com.temel.platform.AppState
import com.temel.platform.MainState
import dagger.Module
import dagger.Provides

@Module
class StatesModule {

    @AppScope
    @Provides
    fun mainState(): MainState{
        return MainState("Initial State", false)
    }

    @AppScope
    @Provides
    fun appState(mainState: MainState): AppState{
        return AppState(mainState)
    }
}
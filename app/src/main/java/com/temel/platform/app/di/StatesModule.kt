package com.temel.platform.app.di

import com.temel.platform.app.state.AppState
import com.temel.platform.app.state.MainState
import dagger.Module
import dagger.Provides

@Module
class StatesModule {

    @AppScope
    @Provides
    fun appState(mainState: MainState): AppState {
        return AppState(mainState)
    }

    @AppScope
    @Provides
    fun mainState(): MainState {
        return MainState("Initial State", false)
    }
}
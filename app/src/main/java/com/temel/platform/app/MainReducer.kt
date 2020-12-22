package com.temel.platform.app

import com.temel.mvi.viewmodel.Reducer
import javax.inject.Inject

class MainReducer @Inject constructor() : Reducer<MainIntent, MainState> {
    override fun reduce(intent: MainIntent, state: MainState): MainState {
        return when (intent) {
            is MainIntent.ChangeText -> {
                 state.copy(text = intent.textNumber)
            }

            is MainIntent.SetIsLoading -> {
                 state.copy(isLoading = intent.isLoading)
            }
        }
    }
}
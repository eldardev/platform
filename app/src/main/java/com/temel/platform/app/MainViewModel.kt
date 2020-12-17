package com.temel.platform.app

import com.temel.mvi.viewmodel.CommonViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : CommonViewModel<MainState, MainIntent>() {

    override fun initViewState(): MainState {
        return MainState("0", false)
    }

    override fun handleIntents(intent: MainIntent) {
        when (intent) {
            is MainIntent.ChangeText -> {
                updateState(viewState?.copy(text = intent.textNumber))
            }

            is MainIntent.SetIsLoading -> {
                updateState(viewState?.copy(isLoading = intent.isLoading))
            }
        }
    }
}
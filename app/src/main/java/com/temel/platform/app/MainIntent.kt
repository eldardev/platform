package com.temel.platform.app

import com.temel.mvi.viewstate.Intents

sealed class MainIntent : Intents {
    class ChangeText (val textNumber: String) : MainIntent()
    class SetIsLoading (val isLoading: Boolean) : MainIntent()
}
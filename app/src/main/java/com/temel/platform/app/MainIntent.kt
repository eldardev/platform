package com.temel.platform.app

import com.temel.mvi.viewstate.Intents

sealed class MainIntent : com.temel.mvi.viewstate.Intents {
    class ChangeText (val textIntent: String) : MainIntent()
}
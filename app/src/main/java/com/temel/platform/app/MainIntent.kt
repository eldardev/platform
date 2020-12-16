package com.temel.platform.app

import com.temel.platform.viewstate.Intents

sealed class MainIntent : Intents {
    class ChangeText (val textIntent: String) : MainIntent()
}
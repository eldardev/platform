package com.temel.platform.app

sealed class MainIntent {
    class ChangeText (val textIntent: String) : MainIntent()
}
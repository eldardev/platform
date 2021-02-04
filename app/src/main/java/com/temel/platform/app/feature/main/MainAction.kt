package com.temel.platform.app.feature.main

import com.temel.mvi.viewstate.Action

sealed class MainAction : Action {
    class ChangeText(val text: String) : MainAction()
    object Loading : MainAction()
    object Loaded : MainAction()
    object FetchFacts : MainAction()
    object ToList : MainAction()
}
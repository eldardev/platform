package com.temel.platform.app

import com.temel.mvi.viewstate.Action

sealed class MainAction : Action {
    class ChangeText(val text: String) : MainAction()
    class SetLoading(val isLoading: Boolean) : MainAction()
    object FetchFacts : MainAction()
}
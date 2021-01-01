package com.temel.platform.app

import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.Command

sealed class MainAction : Action {
    class ChangeText(val text: String) : MainAction()
    class SetLoading(val isLoading: Boolean) : MainAction()
}

sealed class MainCommand : Command {
    object FetchFacts : MainCommand()
}
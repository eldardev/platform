package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.ViewState

abstract class StoreViewModel< A : Action,  VS : ViewState> : ActionViewModel<A, VS>() {

    abstract fun reduce(action: A, state: VS): VS

    init {
        action.subscribe { intent ->
            viewState?.let { state->
                updateState(reduce(intent, state))
            }
        }.disposeLater()
    }
}
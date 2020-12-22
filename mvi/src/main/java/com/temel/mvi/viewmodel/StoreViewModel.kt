package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.Intents
import com.temel.mvi.viewstate.ViewState

abstract class StoreViewModel< I : Intents,  VS : ViewState,> constructor(private val reducer: Reducer<I, VS>) : IntentsViewModel<I, VS>() {
    init {
        intents.subscribe { intent ->
            viewState?.let { state->
                updateState(reducer.reduce(intent, state))
            }
        }.disposeLater()
    }
}
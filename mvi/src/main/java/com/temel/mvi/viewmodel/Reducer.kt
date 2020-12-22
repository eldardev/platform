package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.Intents
import com.temel.mvi.viewstate.ViewState

interface Reducer<Intent:  Intents, State: ViewState> {
    fun reduce(intent: Intent, state: State): State
}
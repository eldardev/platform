package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.ViewState

interface Reducer<A : Action, VS : ViewState> {

    fun reduce(state: VS, action: A): VS
}
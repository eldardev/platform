package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.Command
import com.temel.mvi.viewstate.ViewState

interface StateMachine<A : Action, C : Command, VS : ViewState>
    : Reducer<A, VS>, Commander<A, C, VS> {}
package com.temel.mvi.viewmodel

import com.temel.mvi.viewstate.Action
import com.temel.mvi.viewstate.Command
import com.temel.mvi.viewstate.ViewState
import io.reactivex.Observable

interface StateMachine<A : Action, C : Command, VS : ViewState> {

    fun reduce(action: A, state: VS): VS

    fun call(command: C): (VS) -> Observable<A>
}